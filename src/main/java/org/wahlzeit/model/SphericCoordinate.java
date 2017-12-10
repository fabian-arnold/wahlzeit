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
import org.wahlzeit.utils.ParameterUtil;

/**
 * Represents a generic spheric coordinate
 */
public class SphericCoordinate extends AbstractCoordinate {

  /**
   * Stores the latitude of the coordinate
   */
  private double latitude;

  /**
   * Stores the longitude of the coordinate
   */
  private double longitude;

  /**
   * Stores the radius of the described point
   */
  private double radius;


  /**
   * Creates a spheric coordinate at the location 0, 0 with a radius of 0
   */
  public SphericCoordinate() {
    this(0, 0, 0);
  }

  /**
   * Creates a spheric coordinate at given location with the radius 0
   *
   * @param latitude of the coordinate
   * @param longitude of the coordinate
   */
  public SphericCoordinate(double latitude, double longitude) {
    this(latitude, longitude, 0.0);
  }

  /**
   * Creates a spheric coordinate with given location and radius
   *
   * @param latitude of the coordinate [-90, 90]
   * @param longitude of the coordinate [-180, 180]
   * @param radius of the coordinate [0, inf)
   */
  public SphericCoordinate(double latitude, double longitude, double radius) {
    this.latitude = latitude;
    this.longitude = longitude;
    this.radius = radius;
  }

  /**
   * Returns the latitude of the coordinate
   *
   * @return latitude of the coordinate
   */
  public double getLatitude() {
    return latitude;
  }

  /**
   * Sets the latitude of the coordinate
   *
   * @param latitude of the coordinate in degrees [-90, 90]
   */
  public void setLatitude(double latitude) {
    ParameterUtil.assertNumberInRange(latitude, -90.0, 90.0, "latitude");
    this.latitude = latitude;
  }

  /**
   * Returns the longitude of the coordinate
   *
   * @return longitude of the coordinate
   */
  public double getLongitude() {
    return longitude;
  }

  /**
   * Sets the longitude of the coordinate
   *
   * @param longitude of the coordinate in degrees [-180, 180]
   */
  public void setLongitude(double longitude) {
    ParameterUtil.assertNumberInRange(longitude, -180.0, 180.0, "longitude");
    this.longitude = longitude;
  }

  /**
   * Returns the radius of the coordinate
   *
   * @return radius of the coordinate in km
   */
  public double getRadius() {
    return radius;
  }

  /**
   * Sets the radius of the coordinate
   *
   * @param radius of the coordinate in km [0, inf)
   */
  public void setRadius(double radius) {
    ParameterUtil.assertNumberInRange(radius, 0.0, Double.POSITIVE_INFINITY, "radius");
    this.radius = radius;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SphericCoordinate)) {
      return false;
    }

    SphericCoordinate that = (SphericCoordinate) o;

    if (Double.compare(that.latitude, latitude) != 0) {
      return false;
    }
    if (Double.compare(that.longitude, longitude) != 0) {
      return false;
    }
    return Double.compare(that.radius, radius) == 0;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    temp = Double.doubleToLongBits(latitude);
    result = (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(longitude);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(radius);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "SphericCoordinate{" +
        "latitude=" + latitude +
        ", longitude=" + longitude +
        ", radius=" + radius +
        '}';
  }
}
