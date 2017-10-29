package org.wahlzeit.model;

/**
 * A coordinate which is nowhere and can never be reached
 */
public class NoWhereCoordinate extends Coordinate {

    private static final int NOWHERE_HASH_CODE = 948234134;

    /**
     * Creates a new no where coordinate
     */
    public NoWhereCoordinate() {

    }


    @Override
    public double getX() {
        throw new UnsupportedOperationException("cannot get x of nowhere");
    }

    @Override
    public void setX(double x) {
        throw new UnsupportedOperationException("cannot set x of nowhere");
    }

    @Override
    public double getY() {
        throw new UnsupportedOperationException("cannot get y of nowhere");
    }

    @Override
    public void setY(double y) {
        throw new UnsupportedOperationException("cannot set y of nowhere");
    }

    @Override
    public double getZ() {
        throw new UnsupportedOperationException("cannot get z of nowhere");
    }

    @Override
    public void setZ(double z) {
        throw new UnsupportedOperationException("cannot set z of nowhere");
    }

    @Override
    public double getDistance(Coordinate target) {
        // nowhere is infinite far away we could use the implementation
        // of the base class but this gives more performance
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public boolean equals(Object o) {
        // we can always return false cause nowhere is nowhere
        return false;
    }

    @Override
    public int hashCode() {
        // we return a hash code so we don't break a hashmap or something else
        return NOWHERE_HASH_CODE;
    }

    @Override
    public String toString() {
        return "NoWhereCoordinate{}";
    }
}
