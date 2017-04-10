package com.github.christofluyten.data;

import java.io.Serializable;

import com.github.rinde.rinsim.core.model.road.RoadPath;
import com.github.rinde.rinsim.geom.Point;

/**
 * 
 * @author Vincent Van Gestel
 *
 */
public class ImmutableRoutingTable implements Serializable, RoutingTable {

	private static final long serialVersionUID = 8799716522458614593L;

	private RoutingTable table;

	ImmutableRoutingTable(RoutingTable rTable) {
		this.table = rTable;
	}
	
	@Override
	@Deprecated
	public void addHop(Point from, Point to, NextHop hop) {
		throw new UnsupportedOperationException("This Routing Table is Immutable");
	}

	@Override
	public NextHop getNextHop(Point from, Point to) {
        return table.getNextHop(from, to);
    }

    @Override
	public boolean containsRoute(Point from, Point to) {
        return table.containsRoute(from, to);
    }

    @Override
	public int size() {
        return table.size();
    }

    @Override
	public RoadPath getPathTo(Point from, Point to) {
        return table.getPathTo(from, to);
    }

}