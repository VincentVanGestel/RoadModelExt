package com.github.christofluyten.data;

import java.io.Serializable;

import com.github.rinde.rinsim.geom.Point;

/**
 * Created by Christof on 1/04/2017.
 * Edited by Vincent.
 * Originally named Route, renamed to NextHop.
 */
public class NextHop implements Serializable {

	private static final long serialVersionUID = -3666216608287136198L;

	private Point nextHop;
    private double travelTime;

    public NextHop(Point nextHop, double travelTime) {
        this.nextHop = nextHop;
        this.travelTime = travelTime;
    }

    public Point getPoint() {
        return nextHop;
    }

    public double getTravelTime() {
        return travelTime;
    }

    @Override
    public String toString() {
        return nextHop + ", " + travelTime;
    }
}