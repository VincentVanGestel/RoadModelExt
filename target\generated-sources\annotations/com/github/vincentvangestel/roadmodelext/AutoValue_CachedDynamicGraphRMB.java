
package com.github.vincentvangestel.roadmodelext;

import com.github.christofluyten.data.RoutingTable;
import com.github.rinde.rinsim.geom.ListenableGraph;
import com.google.common.base.Supplier;
import javax.annotation.Generated;
import javax.annotation.Nullable;
import javax.measure.quantity.Length;
import javax.measure.quantity.Velocity;
import javax.measure.unit.Unit;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_CachedDynamicGraphRMB extends CachedDynamicGraphRMB {

  private final Unit<Length> distanceUnit;
  private final Unit<Velocity> speedUnit;
  private final boolean modCheckEnabled;
  private final Supplier<ListenableGraph<?>> graphSupplier;
  private final Supplier<RoutingTable> cacheSupplier;
  private final String cachePath;

  AutoValue_CachedDynamicGraphRMB(
      Unit<Length> distanceUnit,
      Unit<Velocity> speedUnit,
      boolean modCheckEnabled,
      Supplier<ListenableGraph<?>> graphSupplier,
      @Nullable Supplier<RoutingTable> cacheSupplier,
      @Nullable String cachePath) {
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
    this.cacheSupplier = cacheSupplier;
    this.cachePath = cachePath;
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

  @Nullable
  @Override
  protected Supplier<RoutingTable> getCacheSupplier() {
    return cacheSupplier;
  }

  @Nullable
  @Override
  protected String getCachePath() {
    return cachePath;
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
           && ((this.cacheSupplier == null) ? (that.getCacheSupplier() == null) : this.cacheSupplier.equals(that.getCacheSupplier()))
           && ((this.cachePath == null) ? (that.getCachePath() == null) : this.cachePath.equals(that.getCachePath()));
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
    h ^= (cacheSupplier == null) ? 0 : this.cacheSupplier.hashCode();
    h *= 1000003;
    h ^= (cachePath == null) ? 0 : this.cachePath.hashCode();
    return h;
  }

  private static final long serialVersionUID = -7837221650923727573L;

}
