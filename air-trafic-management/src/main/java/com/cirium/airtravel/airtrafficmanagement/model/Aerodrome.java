package com.cirium.airtravel.airtrafficmanagement.model;

public class Aerodrome {

	private final String name;
	private final Coordinate coordinates;

	public Aerodrome(String name, Coordinate coordinates) {
		this.name = name;
		this.coordinates = coordinates;
	}

	public String getName() {
		return name;
	}

	public Coordinate getCoordinates() {
		return coordinates;
	}
}
