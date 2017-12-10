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

/**
 * Provides a interface for generic coordinate types
 */
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
  CartesianCoordinate asCartesianCoordinate();

  /**
   * Calculates the cartesian distance from instance to target.
   *
   * @param target coordinate
   * @return distance to target
   */
  double getCartesianDistance(Coordinate target);

  /**
   * Uses the {@link CoordinateConverter} to convert the current instance to an {@link
   * SphericCoordinate} For supported conversions please refer to the JavaDoc of the
   * {@link CoordinateConverter}
   *
   * @return instance converted to a {@link SphericCoordinate}
   * @see CoordinateConverter#convertTo(Coordinate, Class)
   * @see SphericCoordinate
   */
  SphericCoordinate asSphericCoordinate();

  /**
   * Calculates the spherical distance from instance to target
   *
   * @param target coordinate
   * @return distance to target
   */
  double getSphericalDistance(Coordinate target);

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
  boolean isEqual(Coordinate other);

}
