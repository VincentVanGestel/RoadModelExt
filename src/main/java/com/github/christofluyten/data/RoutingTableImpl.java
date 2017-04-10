package com.github.christofluyten.data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.github.rinde.rinsim.core.model.road.RoadPath;
import com.github.rinde.rinsim.geom.Point;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;

/**
 * Created by Christof on 1/04/2017.
 * Edited by Vincent.
 * Extracted Interface.
 * Renamed Compilation Unit to RoutingTableImpl from RoutingTable.
 */
public class RoutingTableImpl implements Serializable, RoutingTable {

	private static final long serialVersionUID = -8931773465389518115L;

	private Table<Point, Point, NextHop> table;

    public RoutingTableImpl() {
        table = HashBasedTable.create(1800000,1800000);
    }

    @Override
	public void addHop(Point from, Point to, NextHop hop) {
        table.put(from, to, hop);
    }

    @Override
	public NextHop getNextHop(Point from, Point to) {
        return table.get(from, to);
    }

    @Override
	public boolean containsRoute(Point from, Point to) {
        return from.equals(to) || table.contains(from, to);
    }

    @Override
	public int size() {
        return table.size();
    }

    public String toString() {
        return table.toString();
    }

    @Override
	public RoadPath getPathTo(Point from, Point to) {
        List<Point> path = new LinkedList<>();
        path.add(from);

        double travelTime = 0;
        
    	if(from.equals(to)) {
    		return RoadPath.create(path, travelTime, travelTime);
    	}
        
        NextHop nextHop = getNextHop(from, to);
        while (!nextHop.getPoint().equals(to)) {
            path.add(nextHop.getPoint());
            travelTime += nextHop.getTravelTime();
            nextHop = getNextHop(nextHop.getPoint(), to);
        }
        path.add(to);
        
        return RoadPath.create(path, travelTime, travelTime);
    }
    
}