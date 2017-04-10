package com.github.christofluyten.data;

import com.github.rinde.rinsim.core.model.road.RoadPath;
import com.github.rinde.rinsim.geom.Point;

public interface RoutingTable {

	void addHop(Point from, Point to, NextHop hop);

	NextHop getNextHop(Point from, Point to);

	boolean containsRoute(Point from, Point to);

	int size();

	RoadPath getPathTo(Point from, Point to);

}