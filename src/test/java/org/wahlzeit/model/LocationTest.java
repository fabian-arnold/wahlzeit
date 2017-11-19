package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

public class LocationTest {

    @Test
    public void testLocation() {
        Location location = new Location();
        Coordinate zeroLocationCoord = new CartesianCoordinate();

        // test the empty construtor
        Assert.assertTrue(location.getCoordinate() instanceof NoWhereCoordinate);

        // test setter and getter
        location.setCoordinate(zeroLocationCoord);
        Assert.assertEquals(location.getCoordinate(), zeroLocationCoord);

        // test the other constructor
        location = new Location(zeroLocationCoord);
        Assert.assertEquals(location.getCoordinate(), zeroLocationCoord);
    }
}
