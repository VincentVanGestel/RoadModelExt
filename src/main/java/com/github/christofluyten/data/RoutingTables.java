package com.github.christofluyten.data;

import com.github.rinde.rinsim.geom.Point;
import com.google.common.collect.Table;

/**
 * 
 * @author Vincent Van Gestel
 *
 */
public class RoutingTables {
	
	public static RoutingTable createDefaultTable() {
		return new RoutingTableImpl();
	}
	
    public static RoutingTable immutableTable(Table<Point,Point,NextHop>  table) {
    	return new ImmutableRoutingTable(table);
    }
}
