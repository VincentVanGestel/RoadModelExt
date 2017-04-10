package com.github.christofluyten.data;

/**
 * 
 * @author Vincent Van Gestel
 *
 */
public class RoutingTables {
	
	public static RoutingTable createDefaultTable() {
		return new RoutingTableImpl();
	}
	
    public static RoutingTable immutableTable(RoutingTable table) {
    	return new ImmutableRoutingTable(table);
    }
}
