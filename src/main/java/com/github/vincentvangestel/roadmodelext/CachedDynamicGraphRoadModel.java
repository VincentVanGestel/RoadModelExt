package com.github.vincentvangestel.roadmodelext;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.quantity.Velocity;
import javax.measure.unit.Unit;

import com.github.christofluyten.data.NextHop;
import com.github.christofluyten.data.RoutingTable;
import com.github.christofluyten.data.RoutingTables;
import com.github.rinde.rinsim.core.model.road.DynamicGraphRoadModelImpl;
import com.github.rinde.rinsim.core.model.road.GraphRoadModelImpl;
import com.github.rinde.rinsim.core.model.road.RoadPath;
import com.github.rinde.rinsim.core.model.road.RoadUser;
import com.github.rinde.rinsim.geom.GeomHeuristic;
import com.github.rinde.rinsim.geom.Graphs;
import com.github.rinde.rinsim.geom.ImmutableGraph;
import com.github.rinde.rinsim.geom.ListenableGraph;
import com.github.rinde.rinsim.geom.Point;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

/**
 * Special {@link GraphRoadModelImpl} that caches all
 * {@link #getShortestPathTo(Point, Point)} invocations. Further, it keeps track
 * of all {@link RoadUser}s and their types, such that
 * {@link #getObjectsOfType(Class)} is now O(1).
 *
 * @author Rinde van Lon
 * @author Vincent Van Gestel
 */
public class CachedDynamicGraphRoadModel extends DynamicGraphRoadModelImpl {

	protected Optional<CachedDynamicGraphRoadModelSnapshot> snapshot;

	private RoutingTable pathTable;
	private final Multimap<Class<?>, RoadUser> classObjectMap; 

	CachedDynamicGraphRoadModel(ListenableGraph<?> g, CachedDynamicGraphRMB b) {
		super(g, b);

		if(b.getCacheSupplier() != null) {
			pathTable = b.getCacheSupplier().get();
		} else {
			pathTable = RoutingTables.createDefaultTable();
		}
		classObjectMap = LinkedHashMultimap.create();
	}

	/**
	 * Sets the path cache.
	 * @param pPathTable The new path cache to use.
	 */
	public void setPathCache(RoutingTable pPathTable) {
		pathTable = pPathTable;
	}

	/**
	 * @return A reference to the cache. Should be immutable, but not feasible due to memory constraints.
	 */
	public RoutingTable getPathCache() {
		return pathTable;
		//return RoutingTables.immutableTable(pathTable);
	}
	
	private void addPath(Point from, Point to, Unit<Duration> timeUnit, Measure<Double, Velocity> speed,
			GeomHeuristic heuristic) {
		List<Point> path = Graphs.shortestPath(graph, from, to, heuristic);
		Point movingFrom = from;
		checkArgument(path.size() > 1, "A path between two points must at least be of length two");
		for(Point hop : path.subList(1, path.size() - 1)) {
			pathTable.addHop(movingFrom, to, new NextHop(hop,
					heuristic.calculateTravelTime(graph, movingFrom, hop, getDistanceUnit(), speed, timeUnit)));
			movingFrom = hop;
		}
	}

	@Override
	public RoadPath getPathTo(Point from, Point to, Unit<Duration> timeUnit,
			Measure<Double, Velocity> speed, GeomHeuristic heuristic) {
		if (!pathTable.containsRoute(from, to)) {
			addPath(from, to, timeUnit, speed, heuristic);
			updateSnapshot();
		}
		if (!snapshot.isPresent()) {
			updateSnapshot();
		}
		return snapshot.get().getPathTo(from, to, timeUnit, speed, heuristic);
		
	}

	// overrides internal func to add caching
	@Override
	protected List<Point> doGetShortestPathTo(Point from, Point to) {
		if (pathTable.containsRoute(from, to)) {
			return pathTable.getPathTo(from, to).getPath();
		}
		final List<Point> path = super.doGetShortestPathTo(from, to);
		return path;
	}

	@Override
	public void addObjectAt(RoadUser newObj, Point pos) {
		super.addObjectAt(newObj, pos);
		classObjectMap.put(newObj.getClass(), newObj);
	}

	@Override
	public void addObjectAtSamePosition(RoadUser newObj, RoadUser existingObj) {
		super.addObjectAtSamePosition(newObj, existingObj);
		classObjectMap.put(newObj.getClass(), newObj);
	}

	@Override
	public void clear() {
		super.clear();
		classObjectMap.clear();
	}

	/**
	 * O(1) using a direct lookup. {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <Y extends RoadUser> Set<Y> getObjectsOfType(final Class<Y> type) {
		checkArgument(type != null, "type can not be null");
		final Set<Y> set = new LinkedHashSet<>();
		set.addAll((Set<Y>) classObjectMap.get(type));
		return set;
	}

	@Override
	public void removeObject(RoadUser o) {
		super.removeObject(o);
		classObjectMap.remove(o.getClass(), o);
	}

	private void updateSnapshot() {
		snapshot = Optional.of(CachedDynamicGraphRoadModelSnapshot.create(
				ImmutableGraph.copyOf(getGraph()), getPathCache(), getDistanceUnit()));
	}

	public static CachedDynamicGraphRMB builder(Supplier<? extends ListenableGraph<?>> graphSupplier,
			Supplier<RoutingTable> cacheSupplier) {
		return CachedDynamicGraphRMB.create(graphSupplier, cacheSupplier);	  
	}
}
