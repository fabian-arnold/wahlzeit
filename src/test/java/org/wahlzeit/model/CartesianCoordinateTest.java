package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * All test cases of the class {@link CartesianCoordinate}.
 */
public class CartesianCoordinateTest {

    private static double EPSILON = 0.000001;

    /**
     * Tests if the constructors work as expected
     */
    @Test
    public void testSimpleConstruction() {

        CartesianCoordinate coordinate1_1 = new CartesianCoordinate();
        CartesianCoordinate coordinate1_2 = new CartesianCoordinate(11, 22, 33);
        CartesianCoordinate coordinate1_3 = new CartesianCoordinate(-11, -22, -33);

        // Test if the default constructor works as expected
        Assert.assertEquals(coordinate1_1.getX(), 0, 0);
        Assert.assertEquals(coordinate1_1.getY(), 0, 0);
        Assert.assertEquals(coordinate1_1.getZ(), 0, 0);

        // Test the constructor with positive values
        Assert.assertEquals(coordinate1_2.getX(), 11, 0);
        Assert.assertEquals(coordinate1_2.getY(), 22, 0);
        Assert.assertEquals(coordinate1_2.getZ(), 33, 0);

        // Test the constructor with negative values
        Assert.assertEquals(coordinate1_3.getX(), -11, 0);
        Assert.assertEquals(coordinate1_3.getY(), -22, 0);
        Assert.assertEquals(coordinate1_3.getZ(), -33, 0);

    }

    /**
     * Test if an IllegalArgumentException is thrown
     */
    @Test
    public void testInvalidConstructor() {

        try {
            new CartesianCoordinate(Double.NaN, 0, 0);
            Assert.fail();
        } catch (IllegalArgumentException ignored) {
        }

        try {
            new CartesianCoordinate(0, Double.NaN, 0);
            Assert.fail();
        } catch (IllegalArgumentException ignored) {
        }

        try {
            new CartesianCoordinate(0, 0, Double.NaN);
            Assert.fail();
        } catch (IllegalArgumentException ignored) {
        }

        try {
            new CartesianCoordinate(Double.POSITIVE_INFINITY, 0, 0);
            Assert.fail();
        } catch (IllegalArgumentException ignored) {
        }

        try {
            new CartesianCoordinate(0, Double.POSITIVE_INFINITY, 0);
            Assert.fail();
        } catch (IllegalArgumentException ignored) {
        }

        try {
            new CartesianCoordinate(0, 0, Double.POSITIVE_INFINITY);
            Assert.fail();
        } catch (IllegalArgumentException ignored) {
        }

        try {
            new CartesianCoordinate(Double.NEGATIVE_INFINITY, 0, 0);
            Assert.fail();
        } catch (IllegalArgumentException ignored) {
        }

        try {
            new CartesianCoordinate(0, Double.NEGATIVE_INFINITY, 0);
            Assert.fail();
        } catch (IllegalArgumentException ignored) {
        }

        try {
            new CartesianCoordinate(0, 0, Double.NEGATIVE_INFINITY);
            Assert.fail();
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void testAssignment(){
        CartesianCoordinate coordinate = new CartesianCoordinate();

        coordinate.setX(123);
        coordinate.setY(124);
        coordinate.setZ(125);

        Assert.assertEquals(coordinate.getX(), 123, EPSILON);
        Assert.assertEquals(coordinate.getY(), 124, EPSILON);
        Assert.assertEquals(coordinate.getZ(), 125, EPSILON);
    }


    @Test
    public void testNoWhere(){
        // test the constructor
        new NoWhereCoordinate();

        // test the method to behave as expected
        Assert.assertEquals(new CartesianCoordinate().getDistance(new NoWhereCoordinate()), Double.POSITIVE_INFINITY, EPSILON);
        Assert.assertFalse(new NoWhereCoordinate().equals(new NoWhereCoordinate()));
        Assert.assertFalse(new NoWhereCoordinate().equals(new CartesianCoordinate()));
    }

    /**
     * tests the getter and the setters
     */
    @Test
    public void testInvalidAssignment() {

        // test all possible invalid assignments
        CartesianCoordinate coordinate = new CartesianCoordinate();
        try {
            coordinate.setX(Double.NEGATIVE_INFINITY);
            Assert.fail();
        } catch (IllegalArgumentException ignored) {
        }
        try {
            coordinate.setX(Double.POSITIVE_INFINITY);
            Assert.fail();
        } catch (IllegalArgumentException ignored) {
        }
        try {
            coordinate.setX(Double.NaN);
            Assert.fail();
        } catch (IllegalArgumentException ignored) {
        }

        try {
            coordinate.setY(Double.NEGATIVE_INFINITY);
            Assert.fail();
        } catch (IllegalArgumentException ignored) {
        }
        try {
            coordinate.setY(Double.POSITIVE_INFINITY);
            Assert.fail();
        } catch (IllegalArgumentException ignored) {
        }
        try {
            coordinate.setY(Double.NaN);
            Assert.fail();
        } catch (IllegalArgumentException ignored) {
        }

        try {
            coordinate.setZ(Double.NEGATIVE_INFINITY);
            Assert.fail();
        } catch (IllegalArgumentException ignored) {
        }
        try {
            coordinate.setZ(Double.POSITIVE_INFINITY);
            Assert.fail();
        } catch (IllegalArgumentException ignored) {
        }
        try {
            coordinate.setZ(Double.NaN);
            Assert.fail();
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Tests the distance calculation
     */
    @Test
    public void testCalculateDistance() {

        // We use points 10 around the [1, 1, 1]
        CartesianCoordinate reference = new CartesianCoordinate(1, 1, 1);

        CartesianCoordinate coordinate1 = new CartesianCoordinate(10, 10, 10);
        CartesianCoordinate coordinate2 = new CartesianCoordinate(-8, -8, 10);
        CartesianCoordinate coordinate3 = new CartesianCoordinate(-8, 8, 10);
        CartesianCoordinate coordinate4 = new CartesianCoordinate(10, 10, -8);

        // distance from reference to point
        Assert.assertEquals(reference.getDistance(coordinate1), 15.588457268119896, EPSILON);
        Assert.assertEquals(reference.getDistance(coordinate2), 15.588457268119896, EPSILON);
        Assert.assertEquals(reference.getDistance(coordinate3), 14.52583904633395, EPSILON);
        Assert.assertEquals(reference.getDistance(coordinate4), 15.588457268119896, EPSILON);

        // distance from point to reference
        Assert.assertEquals(reference.getDistance(coordinate1), coordinate1.getDistance(reference), EPSILON);
        Assert.assertEquals(reference.getDistance(coordinate2), coordinate2.getDistance(reference), EPSILON);
        Assert.assertEquals(reference.getDistance(coordinate3), coordinate3.getDistance(reference), EPSILON);
        Assert.assertEquals(reference.getDistance(coordinate4), coordinate4.getDistance(reference), EPSILON);

        // test the static implementation
        Assert.assertEquals(CartesianCoordinate.distance(reference, coordinate1), reference.getDistance(coordinate1), EPSILON);
        Assert.assertEquals(CartesianCoordinate.distance(reference, coordinate2), reference.getDistance(coordinate2), EPSILON);
        Assert.assertEquals(CartesianCoordinate.distance(reference, coordinate3), reference.getDistance(coordinate3), EPSILON);
        Assert.assertEquals(CartesianCoordinate.distance(reference, coordinate4), reference.getDistance(coordinate4), EPSILON);

        // check the distance to the point it self
        Assert.assertEquals(reference.getDistance(reference), 0, EPSILON);

        // check the distance to null
        try {
            reference.getDistance(null);
            Assert.fail();
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Test the equal and hash methods
     */
    @Test
    public void testEqualAndHash() {
        Coordinate coordinate1 = new CartesianCoordinate(34, 54, 37);
        Coordinate coordinate2 = new CartesianCoordinate(34, 54, 37);
        Coordinate coordinate3 = new CartesianCoordinate(-34, -54, -37);

        Assert.assertEquals(coordinate1.equals(coordinate1), true);
        Assert.assertEquals(coordinate1.equals(coordinate2), true);
        Assert.assertEquals(coordinate2.equals(coordinate1), true);
        Assert.assertEquals(coordinate1.equals(coordinate3), false);
        Assert.assertEquals(coordinate3.equals(coordinate1), false);

        Assert.assertEquals(coordinate1.hashCode(), coordinate2.hashCode());


        // this may fail with an weak hash implementation
        Assert.assertNotEquals(coordinate1.hashCode(), coordinate3.hashCode());
    }

}
