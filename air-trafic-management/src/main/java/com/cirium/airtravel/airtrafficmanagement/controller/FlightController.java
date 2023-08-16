package com.cirium.airtravel.airtrafficmanagement.controller;

import java.time.Duration;
import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cirium.airtravel.airtrafficmanagement.model.Aerodrome;
import com.cirium.airtravel.airtrafficmanagement.model.Airspace;
import com.cirium.airtravel.airtrafficmanagement.model.Coordinate;
import com.cirium.airtravel.airtrafficmanagement.model.Flight;
import com.cirium.airtravel.airtrafficmanagement.model.FlightStatusResponse;

@RestController
public class FlightController {

	private Flight createSampleFlight() {
		Aerodrome arrivalAerodrome = new Aerodrome("Arrival", new Coordinate(30, 40.0));
		Aerodrome departureAerodrome = new Aerodrome("Departure", new Coordinate(10, 20.0));
		return new Flight(arrivalAerodrome, Instant.now().plus(Duration.ofHours(1)), departureAerodrome,
				Instant.now().minus(Duration.ofHours(1)));
	}

	/**
	 * Get the current flight location
	 * 
	 * 
	 * @return currentLocation
	 */
	@GetMapping("/flight-location")
	public ResponseEntity<Coordinate> getFlightLocation() {
		Flight flight = createSampleFlight();
		Coordinate currentLocation = flight.getCurrentLocation();
		return ResponseEntity.ok(currentLocation);
	}

	/**
	 * Checks whether the flight's current location is within the given airspace
	 * 
	 *
	 * @return true if the flight is within the airspace false
	 *         otherwise.
	 */
	@GetMapping("/is-in-airspace")
	public ResponseEntity<FlightStatusResponse> isFlightInAirspace() {
		Flight flight = createSampleFlight();
		Airspace airspace = new Airspace(new Coordinate(-180, -90), new Coordinate(180, 90.0));
		boolean isInAirspace = flight.isWithinAirspace(airspace);
		return ResponseEntity.ok(new FlightStatusResponse(isInAirspace));
	}
}
