
package com.github.vincentvangestel.roadmodelext;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_ShortestPathCache_SPCacheSup extends ShortestPathCache.SPCacheSup {

  private final String path;

  AutoValue_ShortestPathCache_SPCacheSup(
      String path) {
    if (path == null) {
      throw new NullPointerException("Null path");
    }
    this.path = path;
  }

  @Override
  String path() {
    return path;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof ShortestPathCache.SPCacheSup) {
      ShortestPathCache.SPCacheSup that = (ShortestPathCache.SPCacheSup) o;
      return (this.path.equals(that.path()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.path.hashCode();
    return h;
  }

}
