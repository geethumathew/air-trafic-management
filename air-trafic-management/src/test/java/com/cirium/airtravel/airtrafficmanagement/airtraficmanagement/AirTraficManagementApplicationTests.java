package com.cirium.airtravel.airtrafficmanagement.airtraficmanagement;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cirium.airtravel.airtrafficmanagement.exception.InvalidCoordinateException;
import com.cirium.airtravel.airtrafficmanagement.model.Aerodrome;
import com.cirium.airtravel.airtrafficmanagement.model.Airspace;
import com.cirium.airtravel.airtrafficmanagement.model.AirtrafficController;
import com.cirium.airtravel.airtrafficmanagement.model.Coordinate;
import com.cirium.airtravel.airtrafficmanagement.model.Flight;

@SpringBootTest
public class AirTraficManagementApplicationTests {

    @Mock
    private Aerodrome arrivalAerodromeMock;

    @Mock
    private Aerodrome departureAerodromeMock;
   
    @Mock
    private Airspace airspaceMock;

    @Test
    public void testGetCurrentLocation() {
        when(departureAerodromeMock.getCoordinates()).thenReturn(new Coordinate(10.0, 20.0));
        when(arrivalAerodromeMock.getCoordinates()).thenReturn(new Coordinate(30.0, 40.0));

        Instant departureTime = Instant.now().minus(Duration.ofHours(1));
        Instant arrivalTime = Instant.now().plus(Duration.ofHours(1));
        Flight flight = new Flight(arrivalAerodromeMock, arrivalTime, departureAerodromeMock, departureTime);

        Coordinate currentLocation = flight.getCurrentLocation();

        double expectedCurrentX = 10.0 + 0.5 * (30.0 - 10.0);
        double expectedCurrentY = 20.0 + 0.5 * (40.0 - 20.0);

        assertEquals(expectedCurrentX, currentLocation.getX(), 0.001);
        assertEquals(expectedCurrentY, currentLocation.getY(), 0.001);
    }

    
    @Test
    public void testInvalidCoordinates() {
        double invalidX = 200.0; // outside range
        double validY = 40.0;
        assertThrows(InvalidCoordinateException.class, () -> new Coordinate(invalidX, validY));

    }
    
    @Test
    public void testContainsWithinAirspace() {
        // Create an airspace with bottom-left and top-right coordinates
        Coordinate bottomLeft = new Coordinate(-180.0, -90.0);
        Coordinate topRight = new Coordinate(180.0, 90.0);
        AirtrafficController airspace = new Airspace(bottomLeft, topRight);

        // Create a location within the airspace
        Coordinate location = new Coordinate(0.0, 0.0);

        // Verify that the airspace contains the location
        assertTrue(airspace.contains(location));
    }

    // TODO testNotContainsWithinAirspace()
    
}
    
    

