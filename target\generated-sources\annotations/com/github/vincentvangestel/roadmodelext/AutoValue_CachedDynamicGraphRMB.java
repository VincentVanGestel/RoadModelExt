
package com.github.vincentvangestel.roadmodelext;

import com.github.rinde.rinsim.geom.ListenableGraph;
import com.github.rinde.rinsim.geom.Point;
import com.google.common.base.Supplier;
import com.google.common.collect.Table;
import java.util.List;
import javax.annotation.Generated;
import javax.measure.quantity.Length;
import javax.measure.quantity.Velocity;
import javax.measure.unit.Unit;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_CachedDynamicGraphRMB extends CachedDynamicGraphRMB {

  private final Unit<Length> distanceUnit;
  private final Unit<Velocity> speedUnit;
  private final boolean modCheckEnabled;
  private final Supplier<ListenableGraph<?>> graphSupplier;
  private final Supplier<Table<Point, Point, List<Point>>> cacheSupplier;

  AutoValue_CachedDynamicGraphRMB(
      Unit<Length> distanceUnit,
      Unit<Velocity> speedUnit,
      boolean modCheckEnabled,
      Supplier<ListenableGraph<?>> graphSupplier,
      Supplier<Table<Point, Point, List<Point>>> cacheSupplier) {
    if (distanceUnit == null) {
      throw new NullPointerException("Null distanceUnit");
    }
    this.distanceUnit = distanceUnit;
    if (speedUnit == null) {
      throw new NullPointerException("Null speedUnit");
    }
    this.speedUnit = speedUnit;
    this.modCheckEnabled = modCheckEnabled;
    if (graphSupplier == null) {
      throw new NullPointerException("Null graphSupplier");
    }
    this.graphSupplier = graphSupplier;
    if (cacheSupplier == null) {
      throw new NullPointerException("Null cacheSupplier");
    }
    this.cacheSupplier = cacheSupplier;
  }

  @Override
  public Unit<Length> getDistanceUnit() {
    return distanceUnit;
  }

  @Override
  public Unit<Velocity> getSpeedUnit() {
    return speedUnit;
  }

  @Override
  public boolean isModCheckEnabled() {
    return modCheckEnabled;
  }

  @Override
  protected Supplier<ListenableGraph<?>> getGraphSupplier() {
    return graphSupplier;
  }

  @Override
  protected Supplier<Table<Point, Point, List<Point>>> getCacheSupplier() {
    return cacheSupplier;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof CachedDynamicGraphRMB) {
      CachedDynamicGraphRMB that = (CachedDynamicGraphRMB) o;
      return (this.distanceUnit.equals(that.getDistanceUnit()))
           && (this.speedUnit.equals(that.getSpeedUnit()))
           && (this.modCheckEnabled == that.isModCheckEnabled())
           && (this.graphSupplier.equals(that.getGraphSupplier()))
           && (this.cacheSupplier.equals(that.getCacheSupplier()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.distanceUnit.hashCode();
    h *= 1000003;
    h ^= this.speedUnit.hashCode();
    h *= 1000003;
    h ^= this.modCheckEnabled ? 1231 : 1237;
    h *= 1000003;
    h ^= this.graphSupplier.hashCode();
    h *= 1000003;
    h ^= this.cacheSupplier.hashCode();
    return h;
  }

  private static final long serialVersionUID = -7837221650923727573L;

}
