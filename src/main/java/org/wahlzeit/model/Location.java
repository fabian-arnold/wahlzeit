package org.wahlzeit.model;

/**
 * Stores a location of a {@link Photo}
 */
public class Location {

    /**
     * Stores the coordinate of the location
     */
    protected Coordinate coordinate;

    /**
     * Creates a location placed in nowhere
     *
     * @see NoWhereCoordinate
     */
    public Location() {
        this.coordinate = NoWhereCoordinate.create();
    }

    /**
     * Create a location by given {@link CartesianCoordinate}
     *
     * @param coordinate {@link CartesianCoordinate} of the location
     */
    public Location(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * Gets the {@link CartesianCoordinate} of the location
     *
     * @return CartesianCoordinate of the Location
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }


    /**
     * Sets the coordinate of the location.
     * If you want to place the coordinate nowhere please use {@link NoWhereCoordinate}
     *
     * @param coordinate New coordinate
     * @throws IllegalArgumentException if null is provided as coordinate
     */
    public void setCoordinate(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Cannot set null as coordinate please see NoWhereCoordinate");
        }
        this.coordinate = coordinate;
    }


}
