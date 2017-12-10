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

import org.wahlzeit.utils.ParameterUtil;

/**
 * This class provides distance calculation between coordinates
 */
public class DistanceCalculator {


  /**
   * Prevent instantiation of class
   */
  private DistanceCalculator() {

  }

  /**
   * Calculates the euclidean distance between two {@link CartesianCoordinate}s
   *
   * @param start Start coordinate
   * @param end End coordinate
   * @return the distance in km
   * @see DistanceCalculator#sphericDistance(SphericCoordinate, SphericCoordinate)
   * @see CartesianCoordinate#getDistance(Coordinate)
   * @see SphericCoordinate#getDistance(Coordinate)
   */
  public static double cartesianDistance(CartesianCoordinate start, CartesianCoordinate end) {

        /*
         * This method calculates the euclidean distance between two points
         * https://en.wikipedia.org/wiki/Euclidean_distance
         */

    ParameterUtil.assertNotNull("start", "start");
    ParameterUtil.assertNotNull("end", "end");

    double distanceX = start.getX() - end.getX();
    double distanceY = start.getY() - end.getY();
    double distanceZ = start.getZ() - end.getZ();

    double distanceX_2 = distanceX * distanceX;
    double distanceY_2 = distanceY * distanceY;
    double distanceZ_2 = distanceZ * distanceZ;

    double distance = Math.sqrt(distanceX_2 + distanceY_2 + distanceZ_2);

    // check if we were able to calculate a valid distance
    if(distance == Double.NaN){
      throw new IllegalStateException("There happened an error during distance calculation.");
    }

    return distance;
  }

  /**
   * Calculates the distance in km from the center of start to the center of end
   *
   * @param start coordinate
   * @param end coordinate
   * @return distance in km
   * @see DistanceCalculator#cartesianDistance(CartesianCoordinate, CartesianCoordinate)
   * @see SphericCoordinate#getDistance(Coordinate)
   * @see CartesianCoordinate#getDistance(Coordinate)
   */
  public static double sphericDistance(SphericCoordinate start, SphericCoordinate end) {
    ParameterUtil.assertNotNull(start, "from");
    ParameterUtil.assertNotNull(end, "end");

    // formula from: http://mathforum.org/kb/message.jspa?messageID=7318102

    double r1 = start.getRadius();
    double r2 = end.getRadius();
    double la1 = Math.toRadians(start.getLatitude());
    double la2 = Math.toRadians(end.getLatitude());
    double lo1 = Math.toRadians(start.getLongitude());
    double lo2 = Math.toRadians(end.getLongitude());

    double distance = Math.sqrt(
        r1 * r1 + r2 * r2 - 2 * r1 * r2 * Math.cos(la1) * Math.cos(la2) * Math.cos(lo1 - lo2) -
            2 * r1 * r2 * Math.sin(la1) * Math.sin(la2));

    // check if we were able to calculate a valid distance
    if(distance == Double.NaN){
      throw new IllegalStateException("There happened an error during distance calculation.");
    }

    return distance;
  }

}
