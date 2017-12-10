/*
 * Copyright (c) 2006-2017 by Fabian Arnold
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 *
 */

package org.wahlzeit.model;

import org.wahlzeit.model.converter.CoordinateConverter;
import org.wahlzeit.model.converter.UnsupportedConversionException;
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
   * @throws UnsupportedConversionException if the conversion was not possible
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
   * @throws UnsupportedConversionException if the conversion for distance calculation was not possible
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
   * @throws UnsupportedConversionException if the conversion was not possible
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
   * @throws UnsupportedConversionException if the conversion for distance calculation was not possible
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
   * @throws UnsupportedConversionException if the conversion for distance calculation was not possible
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
