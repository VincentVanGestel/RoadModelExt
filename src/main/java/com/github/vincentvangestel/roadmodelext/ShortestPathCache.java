package com.github.vincentvangestel.roadmodelext;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;

import com.github.christofluyten.data.RoutingTable;
import com.google.auto.value.AutoValue;
import com.google.common.base.Supplier;

public class ShortestPathCache {

	static final String R_BRACE = ")";

	public static RoutingTable read(String path) throws IOException {
		RoutingTable cache;
		try {
			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			cache = (RoutingTable) in.readObject();
			in.close();
			fileIn.close();
		} catch(ClassNotFoundException e) {
			// Should not happen
			throw new IllegalStateException("Table class not found!");
		}
		return cache;
	}

	public static Supplier<RoutingTable> getShortestPathCacheSupplier(
			String path) {
		return SPCacheSup.create(path);
	}

	public static Supplier<RoutingTable> getShortestPathCacheSupplier(
			Path path) {
		return SPCacheSup.create(path.toString());
	}
	
	public static class StaticSPCacheSup {
		
		static RoutingTable cache;
		
		public static RoutingTable get(String path) {
			synchronized (StaticSPCacheSup.class) {
				if(cache == null) {
					try {
						System.out.println("Reading cache from disk...");
						cache = read(path);
					} catch (final IOException e) {
						throw new IllegalArgumentException(e);
					}
				}
			}
			System.out.println("Returning Cache");
			return cache;
		}
		
	}

	@AutoValue
	abstract static class SPCacheSup
	implements Supplier<RoutingTable> {

		abstract String path();

		@Override
		public RoutingTable get() {
			try {
				System.out.println("Reading cache from disk...");
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
