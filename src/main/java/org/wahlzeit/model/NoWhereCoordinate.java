package org.wahlzeit.model;

/**
 * A coordinate which is nowhere and can never be reached
 */
public class NoWhereCoordinate extends AbstractCoordinate {

    private static final int NOWHERE_HASH_CODE = 948234134;

    /**
     * Creates a new no where coordinate
     */
    public NoWhereCoordinate() {

    }

    /**
     * Calculates the distance to target.
     * Returns always the positive infinity
     * @param target The target the distance to is calculated
     */
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
