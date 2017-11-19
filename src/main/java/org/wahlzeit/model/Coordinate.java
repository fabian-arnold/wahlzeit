package org.wahlzeit.model;

import org.wahlzeit.model.converter.CoordinateConverter;

/**
 * Provides a interface for generic coordinate types
 */
// For the next exercise i will call it AbstractCoordinate and move the default implementations :P
public interface Coordinate {


  /**
   * Uses the {@link CoordinateConverter} to convert the current instance to an {@link
   * CartesianCoordinate} For supported conversions please refer to the JavaDoc of the
   * {@link CoordinateConverter}
   *
   * @return instance converted to a {@link CartesianCoordinate}
   * @see CoordinateConverter#convertTo(Coordinate, Class)
   * @see CartesianCoordinate
   */
  default CartesianCoordinate asCartesianCoordinate() {
    return CoordinateConverter.convertTo(this, CartesianCoordinate.class);
  }

  /**
   * Calculates the cartesian distance from instance to target.
   *
   * @param target coordinate
   * @return distance to target
   */
  default double getCartesianDistance(Coordinate target) {

    // handle the special case when target is a NoWhereCoordinate
    if (target instanceof NoWhereCoordinate) {
      return Double.POSITIVE_INFINITY;
    }

    return asCartesianCoordinate().getDistance(target);
  }

  /**
   * Uses the {@link CoordinateConverter} to convert the current instance to an {@link
   * SphericCoordinate} For supported conversions please refer to the JavaDoc of the
   * {@link CoordinateConverter}
   *
   * @return instance converted to a {@link SphericCoordinate}
   * @see CoordinateConverter#convertTo(Coordinate, Class)
   * @see SphericCoordinate
   */
  default SphericCoordinate asSphericCoordinate() {
    return CoordinateConverter.convertTo(this, SphericCoordinate.class);
  }

  /**
   * Calculates the spherical distance from instance to target
   *
   * @param target coordinate
   * @return distance to target
   */
  default double getSphericalDistance(Coordinate target) {

    // handle the special case when target is a NoWhereCoordinate
    if (target instanceof NoWhereCoordinate) {
      return Double.POSITIVE_INFINITY;
    }

    return asSphericCoordinate().getDistance(target);
  }

  /**
   * Calculates the distance from instance to target
   * @param target to calculate distance to
   * @return distance as double
   */
  double getDistance(Coordinate target);

  /**
   * Forwards the call to java default equals function
   *
   * @param other to compare
   * @return true if given objects are equal
   */
  default boolean isEqual(Coordinate other) {
    return this.equals(other);
  }

}
