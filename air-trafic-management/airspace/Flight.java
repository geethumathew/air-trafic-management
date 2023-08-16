// Copyright (C) 2022 Cirium. All rights reserved.
package com.cirium.airtravel.airtrafficmanagement.airspace;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import com.cirium.airtravel.airtrafficmanagement.airspace.Airspace;


/**
 * The class representing a Flight.
 */
public class Flight {

	/** The aerodrome the flight is arriving at. */
	private final Areodrome arrivalAerodrome;

	/** The date/time the flight is arriving. */
	private final Instant arrivalTime;

	/** The aerodrome the flight is departing from. */
	private final Areodrome departureAerodrome;

	/** The date/time the flight is departing. */
	private final Instant departureTime;
	
	//Given an instance of a Flight, and an instance of an Airspace, enhance the code to allow users to determine if the flight is within the airspace. 



	/**
	 * Construct a flight.
	 *
	 * @param arrivalAerodrome   The aerodrome the flight is arriving at.
	 * @param arrivalTime        The date/time the flight is arriving.
	 * @param departureAerodrome The aerodrome the flight is departing from.
	 * @param departureTime      The date/time the flight is departing.
	 */
	public Flight(final String arrivalAerodrome, final Instant arrivalTime, final String departureAerodrome,
			final Instant departureTime) {
		this.arrivalAerodrome = arrivalAerodrome;
		this.arrivalTime = arrivalTime;
		this.departureAerodrome = departureAerodrome;
		this.departureTime = departureTime;
	}

	/**
	 * Gets the aerodrome the flight is arriving at.
	 *
	 * @return the arrival aerodrome.
	 */
	public String getArrivalAerodrome() {
		return arrivalAerodrome;
	}

	/**
	 * Gets the date/time the flight is arriving.
	 *
	 * @return the arrival time.
	 */
	public Instant getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Gets the aerodrome the flight is departing from.
	 *
	 * @return the departure aerodrome.
	 */
	public String getDepartureAerodrome() {
		return departureAerodrome;
	}

	/**
	 * Gets the date/time the flight is departing.
	 *
	 * @return the departure time.
	 */
	public Instant getDepartureTime() {
		return departureTime;
	}

	// Method to add aerodrome coordinates to the map
	public void addAerodromeCoordinate(String aerodrome, Coordinate coordinate) {
		aerodromeCoordinates.put(aerodrome, coordinate); // add cordinates validation -180 to 180, +90 -90
	}

	// Method to calculate the coordinate of a specific aerodrome
	public Coordinate calculateAerodromeCoordinate(String aerodrome) {
		return aerodromeCoordinates.get(aerodrome); // add cordinates validation
	}

	// Method to calculate the current location based on time, departure, and
	// arrival coordinates
	public Coordinate getCurrentLocation() {
		// Get the current time
		Instant now = Instant.now(); 

		// Calculate the total duration of the flight in milliseconds
		long totalDuration = Duration.between(departureTime, arrivalTime).toMillis();

		// Calculate the time elapsed since departure in milliseconds
		long timeElapsed = Duration.between(departureTime, now).toMillis();

		// Calculate the proportion of time elapsed to total duration
		double proportion = (double) timeElapsed / totalDuration;

		// Get the coordinates of departure and arrival aerodromes
		Coordinate departureCoord = calculateAerodromeCoordinate(departureAerodrome);
		Coordinate arrivalCoord = calculateAerodromeCoordinate(arrivalAerodrome);

		// Calculate the current location based on departure and arrival coordinates
		double currentX = departureCoord.getX() + proportion * (arrivalCoord.getX() - departureCoord.getX());
		//arrival (30,40)  depature(10,20)
		double currentY = departureCoord.getY() + proportion * (arrivalCoord.getY() - departureCoord.getY());

		// Create and return a new Coordinate object representing the current location
		return new Coordinate(currentX, currentY);
	}

	/**
	 * Checks whether the flight's current location is within the given airspace.
	 *
	 * @param airspace The airspace to check against.
	 * @return {@code true} if the flight is within the airspace, {@code false}
	 *         otherwise.
	 */
	public boolean isWithinAirspace(Airspace airspace) {
		// Get the current location of the flight
		Coordinate currentLocation = getCurrentLocation();

		// Check if the current location is within the provided airspace
		boolean withinAirspace = airspace.contains(currentLocation);

		// Return the result of the containment check
		return withinAirspace;
	}
}
