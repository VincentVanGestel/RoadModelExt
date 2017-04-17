package com.github.vincentvangestel.roadmodelext;

import javax.annotation.Nullable;
import javax.measure.quantity.Length;
import javax.measure.quantity.Velocity;
import javax.measure.unit.Unit;

import com.github.christofluyten.data.RoutingTable;
import com.github.rinde.rinsim.core.model.DependencyProvider;
import com.github.rinde.rinsim.core.model.road.RoadModelBuilders;
import com.github.rinde.rinsim.core.model.road.RoadModelBuilders.AbstractDynamicGraphRMB;
import com.github.rinde.rinsim.geom.ListenableGraph;
import com.google.auto.value.AutoValue;
import com.google.common.base.Supplier;

/**
 * Builder for {@link CachedDynamicGraphRoadModel} instances.
 * @author Vincent Van Gestel
 */
@AutoValue
public abstract class CachedDynamicGraphRMB
    extends AbstractDynamicGraphRMB<CachedDynamicGraphRoadModel, CachedDynamicGraphRMB> {

  private static final long serialVersionUID = -7837221650923727573L;

  @Override
  protected abstract Supplier<ListenableGraph<?>> getGraphSupplier();
  
  @Nullable
  protected abstract Supplier<RoutingTable> getCacheSupplier();
  
  @Nullable
  protected abstract String getCachePath();

  @Override
  public CachedDynamicGraphRoadModel build(DependencyProvider dependencyProvider) {
    return new CachedDynamicGraphRoadModel(getGraph(), this);
  }
  
  @Override
  public CachedDynamicGraphRMB withModificationCheck(boolean enabled) {
    return create(getDistanceUnit(), getSpeedUnit(), getGraphSupplier(),
      enabled, getCacheSupplier(), getCachePath());
  }
  
  @Override
  public CachedDynamicGraphRMB withDistanceUnit(Unit<Length> unit) {
    return create(unit, getSpeedUnit(), getGraphSupplier(),
      isModCheckEnabled(), getCacheSupplier(), getCachePath());
  }

  @Override
  public CachedDynamicGraphRMB withSpeedUnit(Unit<Velocity> unit) {
    return create(getDistanceUnit(), unit, getGraphSupplier(),
      isModCheckEnabled(), getCacheSupplier(), getCachePath());
  }
  @Override
  public String toString() {
    return RoadModelBuilders.class.getSimpleName()
      + ".dynamicGraph().withCache()";
  }

  static CachedDynamicGraphRMB create(
		  Supplier<? extends ListenableGraph<?>> graphSupplier,
				  Supplier<RoutingTable> cacheSupplier, String cachePath) {
	  return create(DEFAULT_DISTANCE_UNIT, DEFAULT_SPEED_UNIT, graphSupplier,
			  true, cacheSupplier, cachePath);
  }

  @SuppressWarnings("unchecked")
  static CachedDynamicGraphRMB create(Unit<Length> distanceUnit,
		  Unit<Velocity> speedUnit,
		  Supplier<? extends ListenableGraph<?>> graphSupplier,
				  boolean isGmcEnabled,
				  Supplier<RoutingTable> cacheSupplier, String cachePath) {
	  return new AutoValue_CachedDynamicGraphRMB(distanceUnit,
			  speedUnit, isGmcEnabled, (Supplier<ListenableGraph<?>>) graphSupplier, cacheSupplier, cachePath);
  }

}
