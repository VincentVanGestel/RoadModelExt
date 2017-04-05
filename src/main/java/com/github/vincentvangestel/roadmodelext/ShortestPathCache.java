package com.github.vincentvangestel.roadmodelext;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.util.List;

import com.github.rinde.rinsim.geom.Point;
import com.google.auto.value.AutoValue;
import com.google.common.base.Supplier;
import com.google.common.collect.Table;

public class ShortestPathCache {

	static final String R_BRACE = ")";

	public static Table<Point,Point,List<Point>> read(String path) throws IOException {
		Table<Point,Point,List<Point>> cache;
		try {
			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			cache = (Table<Point, Point, List<Point>>) in.readObject();
			in.close();
			fileIn.close();
		} catch(ClassNotFoundException e) {
			// Should not happen
			throw new IllegalStateException("Table class not found!");
		}
		return cache;
	}

	public static Supplier<Table<Point, Point, List<Point>>> getShortestPathCacheSupplier(
			String path) {
		return SPCacheSup.create(path);
	}

	public static Supplier<Table<Point, Point, List<Point>>> getShortestPathCacheSupplier(
			Path path) {
		return SPCacheSup.create(path.toString());
	}

	@AutoValue
	abstract static class SPCacheSup
	implements Supplier<Table<Point, Point, List<Point>>> {

		abstract String path();

		@Override
		public Table<Point, Point, List<Point>> get() {
			try {
				return read(path());
			} catch (final IOException e) {
				throw new IllegalArgumentException(e);
			}
		}

		@Override
		public String toString() {
			return ShortestPathCache.class.getName() + ".getShortestPathCacheSupplier("
					+ path() + R_BRACE;
		}

		static SPCacheSup create(String p) {
			return new AutoValue_ShortestPathCache_SPCacheSup(p);
		}
	}
}
