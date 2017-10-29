package org.wahlzeit.model;


/**
 * Class that provides a cartesian coordinates.
 */
public class Coordinate {

    /**
     * Stores the x-component of the coordinate
     */
    private double x;

    /**
     * Stores the y-component of the coordinate
     */
    private double y;

    /**
     * Stores the z-component of the coordinate
     */
    private double z;


    /**
     * Creates a new coordinate with x, y and z set to 0
     */
    public Coordinate() {
        // we can not use setters here because it would break our nowhere implementation
        x = 0;
        y = 0;
        z = 0;
    }


    /**
     * Creates a new Coordinate with given x, y and z
     *
     * @param x z-Component of the coordinate
     * @param y y-Component of the coordinate
     * @param z z-Component of the coordinate
     * @throws IllegalArgumentException throws an exception if the provided components are non numeric
     */
    public Coordinate(double x, double y, double z) {
        // we use setters to check the given values
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }

    /**
     * Gets the x-component of the coordinate
     *
     * @return x-component
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the x-component of the coordinate
     *
     * @param x x-component
     * @throws IllegalArgumentException throws an exception if the provided value is non numeric
     */
    public void setX(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            throw new IllegalArgumentException("A coordinate can not be set to non numeric values");
        }
        this.x = x;
    }

    /**
     * Gets the y-component of the coordinate
     *
     * @return y-component
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the y-component of the coordinate
     *
     * @param y y-component
     */
    public void setY(double y) {
        if (Double.isNaN(y) || Double.isInfinite(y)) {
            throw new IllegalArgumentException("A coordinate can not be set to non numeric values");
        }
        this.y = y;
    }

    /**
     * Gets the z-component of the coordinate
     *
     * @return z-component
     */
    public double getZ() {
        return z;
    }

    /**
     * Sets the z-component of the coordinate
     *
     * @param z z-component
     */
    public void setZ(double z) {
        if (Double.isNaN(z) || Double.isInfinite(z)) {
            throw new IllegalArgumentException("A coordinate can not be set to non numeric values");
        }
        this.z = z;
    }


    /**
     * Calculates the euclidean distance between the current instance and target
     *
     * @param target The target the distance to is calculated
     * @return the distance
     */
    public double getDistance(Coordinate target) {
        return distance(this, target);
    }


    /**
     * Calculates the euclidean distance between two {@link Coordinate}s
     *
     * @param start Start coordinate
     * @param end   End coordinate
     * @return the distance
     */
    public static double distance(Coordinate start, Coordinate end) {

        /*
         * This method calculates the euclidean distance between two points
         * https://en.wikipedia.org/wiki/Euclidean_distance
         */

        if (start == null || end == null) {
            throw new IllegalArgumentException("Cannot calculate distance to null");
        }

        if (start instanceof NoWhereCoordinate || end instanceof NoWhereCoordinate) {
            return Double.POSITIVE_INFINITY;
        }

        double distanceX = start.getX() - end.getX();
        double distanceY = start.getY() - end.getY();
        double distanceZ = start.getZ() - end.getZ();

        double distanceX_2 = distanceX * distanceX;
        double distanceY_2 = distanceY * distanceY;
        double distanceZ_2 = distanceZ * distanceZ;

        return Math.sqrt(distanceX_2 + distanceY_2 + distanceZ_2);
    }

    /**
     * Returns true if compared to an other coordinate and all components are equal
     *
     * @param o an object ot compare with
     * @return true if o is an coordinate and all components are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;

        Coordinate that = (Coordinate) o;

        if (Double.compare(that.x, x) != 0) return false;
        if (Double.compare(that.y, y) != 0) return false;
        return Double.compare(that.z, z) == 0;
    }

    /**
     * Calculates an hash for every coordinate.
     * The hash is equal if all components are equal.
     *
     * @return hashed value of the coordinate
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Converts the coordinate to an string
     *
     * @return coordinate as string
     */
    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
