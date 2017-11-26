package org.wahlzeit.model;

import org.wahlzeit.model.converter.CoordinateConverter;
import org.wahlzeit.utils.ParameterUtil;

/**
 * Provides the base implementation of a coordinate
 *
 * @see SphericCoordinate
 * @see CartesianCoordinate
 */
public abstract class AbstractCoordinate implements Coordinate {

  /**
   * Uses the {@link CoordinateConverter} to convert the current instance to an {@link
   * CartesianCoordinate} For supported conversions please refer to the JavaDoc of the {@link
   * CoordinateConverter}
   *
   * @return instance converted to a {@link CartesianCoordinate}
   * @see CoordinateConverter#convertTo(Coordinate, Class)
   * @see CartesianCoordinate
   */
  @Override
  public CartesianCoordinate asCartesianCoordinate() {
    return CoordinateConverter.convertTo(this, CartesianCoordinate.class);
  }

  /**
   * Calculates the cartesian distance from instance to target.
   *
   * @param target coordinate
   * @return distance to target
   */
  @Override
  public double getCartesianDistance(Coordinate target) {
    // handle the special case when target is a NoWhereCoordinate
    if (target instanceof NoWhereCoordinate) {
      return Double.POSITIVE_INFINITY;
    }

    return DistanceCalculator
        .cartesianDistance(this.asCartesianCoordinate(), target.asCartesianCoordinate());
  }

  /**
   * Uses the {@link CoordinateConverter} to convert the current instance to an {@link
   * SphericCoordinate} For supported conversions please refer to the JavaDoc of the {@link
   * CoordinateConverter}
   *
   * @return instance converted to a {@link SphericCoordinate}
   * @see CoordinateConverter#convertTo(Coordinate, Class)
   * @see SphericCoordinate
   */
  @Override
  public SphericCoordinate asSphericCoordinate() {
    return CoordinateConverter.convertTo(this, SphericCoordinate.class);
  }

  /**
   * Calculates the spherical distance from instance to target
   *
   * @param target coordinate
   * @return distance to target
   */
  @Override
  public double getSphericalDistance(Coordinate target) {
    // handle the special case when target is a NoWhereCoordinate
    if (target instanceof NoWhereCoordinate) {
      return Double.POSITIVE_INFINITY;
    }

    return DistanceCalculator
        .sphericDistance(this.asSphericCoordinate(), target.asSphericCoordinate());

  }

  /**
   * Calculates the cartesian distance from instance to target
   *
   * @param target to calculate distance to
   * @return distance as double
   * @see DistanceCalculator#cartesianDistance(CartesianCoordinate, CartesianCoordinate)
   */
  @Override
  public double getDistance(Coordinate target) {

    /*
      the implementation refers to cartesianDistance to get consistent results
     */

    ParameterUtil.assertNotNull(target, "target");

    if (target instanceof NoWhereCoordinate || this instanceof NoWhereCoordinate) {
      return Double.POSITIVE_INFINITY;
    }

    return DistanceCalculator
        .cartesianDistance(this.asCartesianCoordinate(), target.asCartesianCoordinate());
  }

  /**
   * Forwards the call to java default equals function
   *
   * @param other to compare
   * @return true if given objects are equal
   */
  @Override
  public boolean isEqual(Coordinate other) {
    return this.equals(other);
  }
}
