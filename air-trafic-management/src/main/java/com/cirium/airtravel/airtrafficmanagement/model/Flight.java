// Copyright (C) 2022 Cirium. All rights reserved.
package com.cirium.airtravel.airtrafficmanagement.model;

import java.time.Duration;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class representing a Flight.
 */
public class Flight {
	
	private static final Logger logger = LoggerFactory.getLogger(Airspace.class);
	
	private final Aerodrome arrivalAerodrome;

	private final Instant arrivalTime;

	private final Aerodrome departureAerodrome;

	private final Instant departureTime;

	public Flight(Aerodrome arrivalAerodrome, final Instant arrivalTime, Aerodrome departureAerodrome,
			final Instant departureTime) {
		this.arrivalAerodrome = arrivalAerodrome;
		this.departureAerodrome = departureAerodrome;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
	}

	public Aerodrome getArrivalAerodrome() {
		return arrivalAerodrome;
	}

	public Aerodrome getDepartureAerodrome() {
		return departureAerodrome;
	}

	public Instant getArrivalTime() {
		return arrivalTime;
	}

	public Instant getDepartureTime() {
		return departureTime;
	}

	/**
	 * Method to calculate the current location based on time, departure, and
	 * arrival coordinates
	 *
	 * @return the current Coordinate.
	 */
	public Coordinate getCurrentLocation() {
		// Get the current time
		Instant now = Instant.now();

		// Calculate the total duration of the flight
		long totalDuration = Duration.between(departureTime, arrivalTime).toMillis();

		// Calculate the time elapsed since departure
		long timeElapsed = Duration.between(departureTime, now).toMillis();

		// Calculate the proportion of time elapsed to total duration
		double proportion = (double) timeElapsed / totalDuration;

		// Get the coordinates of departure and arrival
		Coordinate departureCoord = departureAerodrome.getCoordinates();
		Coordinate arrivalCoord = arrivalAerodrome.getCoordinates();

		// Calculate the current coordinates based on departure and arrival coordinates
		double currentX = departureCoord.getX() + proportion * (arrivalCoord.getX() - departureCoord.getX());

		double currentY = departureCoord.getY() + proportion * (arrivalCoord.getY() - departureCoord.getY());
		
		logger.info("At time {} coordinates are <{}, {}> ",now, currentX, currentY);
		
		return new Coordinate(currentX, currentY);
	}

	/**
	 * Checks whether the flight's current location is within the given airspace.
	 *
	 * @param airspace The airspace to check against.
	 * @return true if the flight is within the airspace false otherwise
	 * 
	 */
	public boolean isWithinAirspace(AirtrafficController airspace) {
		// Get the current location of the flight
		Coordinate currentLocation = getCurrentLocation();
		

		// Check if the current location is within the provided airspace
		return  airspace.contains(currentLocation);


	}
}
