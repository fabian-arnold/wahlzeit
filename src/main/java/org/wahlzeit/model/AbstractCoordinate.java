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
import org.wahlzeit.utils.doc.DesignPattern;
import org.wahlzeit.utils.doc.pattern.PatternType;

/**
 * Provides the base implementation of a coordinate. The implementation itself should follow
 * the value object pattern
 *
 * @see SphericCoordinate
 * @see CartesianCoordinate
 */
@DesignPattern(value = PatternType.PROTOTYPE,
    participants = {CartesianCoordinate.class, SphericCoordinate.class, NoWhereCoordinate.class})
public abstract class AbstractCoordinate implements Coordinate {

  /**
   * Uses the {@link CoordinateConverter} to convert the current instance to an {@link
   * CartesianCoordinate} For supported conversions please refer to the JavaDoc of the {@link
   * CoordinateConverter}
   *
   * @return instance converted to a {@link CartesianCoordinate}
   * @throws UnsupportedConversionException if the conversion was not possible
   * @see CoordinateConverter#convertTo(Coordinate, Class)
   * @see CartesianCoordinate
   */
  @Override
  public CartesianCoordinate asCartesianCoordinate() throws UnsupportedConversionException {
    return CoordinateConverter.convertTo(this, CartesianCoordinate.class);
  }

  /**
   * Calculates the cartesian distance from instance to target.
   *
   * @param target coordinate
   * @return distance to target in km or {@code Double.NaN} if conversion was not possible
   * @see DistanceCalculator#cartesianDistance(CartesianCoordinate, CartesianCoordinate)
   */
  @Override
  public double getCartesianDistance(Coordinate target) {

    ParameterUtil.assertNotNull(target, "target");

    // handle the special case when target is a NoWhereCoordinate

    if (target instanceof NoWhereCoordinate || this instanceof NoWhereCoordinate) {
      return Double.POSITIVE_INFINITY;
    }

    try {
      return DistanceCalculator
          .cartesianDistance(this.asCartesianCoordinate(), target.asCartesianCoordinate());
    } catch (UnsupportedConversionException ex) {
      return Double.NaN;
    }
  }

  /**
   * Uses the {@link CoordinateConverter} to convert the current instance to an {@link
   * SphericCoordinate} For supported conversions please refer to the JavaDoc of the {@link
   * CoordinateConverter}
   *
   * @return instance converted to a {@link SphericCoordinate}
   * @throws UnsupportedConversionException if the conversion was not possible
   * @see CoordinateConverter#convertTo(Coordinate, Class)
   * @see SphericCoordinate
   */
  @Override
  public SphericCoordinate asSphericCoordinate() throws UnsupportedConversionException {
    return CoordinateConverter.convertTo(this, SphericCoordinate.class);
  }

  /**
   * Calculates the spherical distance from instance to target
   *
   * @param target coordinate
   * @return distance to target in km or {@code Double.NaN} if conversion was not possible
   * @see DistanceCalculator#sphericDistance(SphericCoordinate, SphericCoordinate)
   */
  @Override
  public double getSphericalDistance(Coordinate target) {

    ParameterUtil.assertNotNull(target, "target");

    // handle the special case when target is a NoWhereCoordinate

    if (target instanceof NoWhereCoordinate || this instanceof NoWhereCoordinate) {
      return Double.POSITIVE_INFINITY;
    }

    try {
      return DistanceCalculator
          .sphericDistance(this.asSphericCoordinate(), target.asSphericCoordinate());
    } catch (UnsupportedConversionException e) {
      return Double.NaN;
    }

  }

  /**
   * Calculates the cartesian distance from instance to target
   *
   * @param target to calculate distance to in km
   * @return distance as double or {@code Double.NaN} if conversion was not possible
   * @see DistanceCalculator#cartesianDistance(CartesianCoordinate, CartesianCoordinate)
   * @see #getCartesianDistance(Coordinate)
   */
  @Override
  public double getDistance(Coordinate target) {

    /*
      the implementation refers to cartesianDistance to get consistent results
     */
    return this.getCartesianDistance(target);
  }

  /**
   * Forwards the call to java default equals function
   *
   * @param other to compare
   * @return true if given objects are equal
   * @see #equals(Object)
   */
  @Override
  public boolean isEqual(Coordinate other) {
    return this.equals(other);
  }
}
