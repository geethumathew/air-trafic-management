// Copyright (C) 2022 Cirium. All rights reserved.
package com.cirium.airtravel.airtrafficmanagement.airspace;



/**
 * The class representing an Airspace.
 */
public class Airspace {

	/** The bottom left coordinate of this airspace. */
	private final Coordinate bottomLeft;

	/** The top right coordinate of this airspace. */
	private final Coordinate topRight;

	/**
	 * Construct an Airspace.
	 *
	 * @param bottomLeft the bottom left coordinate of this airspace.
	 * @param topRight   the top right coordinate of this airspace.
	 */
	public Airspace(final Coordinate bottomLeft, final Coordinate topRight) {
		this.bottomLeft = bottomLeft;
		this.topRight = topRight;
	}

	/**
	 * Gets the bottom left coordinate of this airspace.
	 *
	 * @return the bottom left.
	 */
	public Coordinate getBottomLeft() {
		return bottomLeft;
	}

	/**
	 * Gets the top right coordinate of this airspace.
	 *
	 * @return the top right.
	 */
	public Coordinate getTopRight() {
		return topRight;
	}

	/**
	 * Checks whether the given location is within the bounds of this airspace.
	 *
	 * @param location The location coordinate to check.
	 * @return {@code true} if the location is within the airspace, {@code false}
	 *         otherwise.
	 */
	public boolean contains(Coordinate location) {
		// Extract the X and Y coordinates from the location
		double x = location.getX();
		double y = location.getY();

		// Check if the X and Y coordinates are within the bounds of the airspace
		boolean withinXBounds = x >= bottomLeft.getX() && x <= topRight.getX();
		boolean withinYBounds = y >= bottomLeft.getY() && y <= topRight.getY();

		// Return true only if both X and Y coordinates are within bounds
		return withinXBounds && withinYBounds;
	}

}
