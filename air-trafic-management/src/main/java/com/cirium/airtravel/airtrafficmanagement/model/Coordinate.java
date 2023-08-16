// Copyright (C) 2022 Cirium. All rights reserved.
package com.cirium.airtravel.airtrafficmanagement.model;

import com.cirium.airtravel.airtrafficmanagement.exception.InvalidCoordinateException;

/**
 * The class representing a coordinate (x, y).
 */
public class Coordinate {

	/** The X component of this coordinate (can have values -180 to +180). */
	private final double x;

	/** The Y component of this coordinate (can have values -90 to +90). */
	private final double y;

	/**
	 * Construct a coordinate.
	 *
	 * @param x the X component of this coordinate (can have values -180 to +180).
	 * @param y the Y component of this coordinate (can have values -90 to +90).
	 */
	public Coordinate(final double x, final double y) {
		 if (!isValidCoordinate(x, y)) {
	            throw new InvalidCoordinateException("Invalid coordinate values: x=" + x + ", y=" + y);
	        }
	        this.x = x;
	        this.y = y;
	    }
	/**
	 * Gets the X component of this coordinate (can have values -180 to +180).
	 *
	 * @return the x.
	 */
	public double getX() {
		return x;
	}

	/**
	 * Gets the Y component of this coordinate (can have values -90 to +90).
	 *
	 * @return the y.
	 */
	public double getY() {
		return y;
	}
	
	public static boolean isValidCoordinate(double x, double y) {
		double minX = -180.0;
		double maxX = 180.0;
		double minY = -90.0;
		double maxY = 90.0;
		boolean isValid = x >= minX && x <= maxX && y >= minY && y <= maxY;
		return isValid;
	}
	
	@Override
	public String toString() {
	    return "Coordinate(x=" + x + ", y=" + y + ")";
	}

}
