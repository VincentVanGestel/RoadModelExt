
package com.github.vincentvangestel.roadmodelext;

import com.github.christofluyten.data.RoutingTable;
import com.github.rinde.rinsim.geom.ConnectionData;
import com.github.rinde.rinsim.geom.ImmutableGraph;
import javax.annotation.Generated;
import javax.measure.quantity.Length;
import javax.measure.unit.Unit;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_CachedDynamicGraphRoadModelSnapshot extends CachedDynamicGraphRoadModelSnapshot {

  private final ImmutableGraph<? extends ConnectionData> graph;
  private final RoutingTable routingTable;
  private final Unit<Length> modelDistanceUnit;

  AutoValue_CachedDynamicGraphRoadModelSnapshot(
      ImmutableGraph<? extends ConnectionData> graph,
      RoutingTable routingTable,
      Unit<Length> modelDistanceUnit) {
    if (graph == null) {
      throw new NullPointerException("Null graph");
    }
    this.graph = graph;
    if (routingTable == null) {
      throw new NullPointerException("Null routingTable");
    }
    this.routingTable = routingTable;
    if (modelDistanceUnit == null) {
      throw new NullPointerException("Null modelDistanceUnit");
    }
    this.modelDistanceUnit = modelDistanceUnit;
  }

  @Override
  public ImmutableGraph<? extends ConnectionData> getGraph() {
    return graph;
  }

  @Override
  public RoutingTable getRoutingTable() {
    return routingTable;
  }

  @Override
  public Unit<Length> getModelDistanceUnit() {
    return modelDistanceUnit;
  }

  @Override
  public String toString() {
    return "CachedDynamicGraphRoadModelSnapshot{"
        + "graph=" + graph + ", "
        + "routingTable=" + routingTable + ", "
        + "modelDistanceUnit=" + modelDistanceUnit
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof CachedDynamicGraphRoadModelSnapshot) {
      CachedDynamicGraphRoadModelSnapshot that = (CachedDynamicGraphRoadModelSnapshot) o;
      return (this.graph.equals(that.getGraph()))
           && (this.routingTable.equals(that.getRoutingTable()))
           && (this.modelDistanceUnit.equals(that.getModelDistanceUnit()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.graph.hashCode();
    h *= 1000003;
    h ^= this.routingTable.hashCode();
    h *= 1000003;
    h ^= this.modelDistanceUnit.hashCode();
    return h;
  }

}
