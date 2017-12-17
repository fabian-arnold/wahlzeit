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

import static org.wahlzeit.utils.HashUtil.hash3;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.wahlzeit.model.converter.CoordinateConverter;
import org.wahlzeit.utils.ParameterUtil;

/**
 * Represents a generic spheric coordinate
 */
public class SphericCoordinate extends AbstractCoordinate {

  /**
   * Stores the latitude of the coordinate
   */
  private final double latitude;

  /**
   * Stores the longitude of the coordinate
   */
  private final double longitude;

  /**
   * Stores the radius of the described point
   */
  private final double radius;


  /**
   * This map stores all instances of cartesian coordinates
   */
  private static final HashMap<Integer, List<WeakReference<SphericCoordinate>>> INSTANCES = new HashMap<>();

  /**
   * Creates a spheric coordinate with given location and radius
   *
   * @param latitude of the coordinate [-90, 90]
   * @param longitude of the coordinate [-180, 180]
   * @param radius of the coordinate [0, inf)
   */
  public synchronized static SphericCoordinate create(double latitude, double longitude, double radius) {
    // ensure a object get not persist twice
    synchronized (INSTANCES) {
      ParameterUtil.assertValidNumber(latitude, "latitude");
      ParameterUtil.assertValidNumber(longitude, "longitude");
      ParameterUtil.assertValidNumber(radius, "radius");

      // look if we already have a instance with the provided coordinates
      int hash = hash3(latitude, longitude, radius);
      SphericCoordinate coord;
      if (INSTANCES.containsKey(hash)) {
        // there is already a list of objects with this hash
        List<WeakReference<SphericCoordinate>> coords = INSTANCES.get(hash);
        for (WeakReference<SphericCoordinate> refCoord : coords) {
          coord = refCoord.get();
          if (coord == null) {
            // the finalizer will clean it up probably
            continue;
          }
          if (coord.getLatitude() == latitude && coord.getLongitude() == longitude && coord.getRadius() == radius) {
            return coord;
          }
        }
      /*
       The reference was deleted by the gc or we have no matching
       => Delete the invalid reference and create a new reference
       */
        coord = new SphericCoordinate(latitude, longitude, radius);
        coords.add(new WeakReference<>(coord));
        // return the created reference
        return coord;
      } else {
        // the hash is not existent => create a list of objects with this hash
        List<WeakReference<SphericCoordinate>> coords = new ArrayList<>();
        // create our new coordinate
        coord = new SphericCoordinate(latitude, longitude, radius);
        // store a reference to our coordinate
        coords.add(new WeakReference<>(coord));
        INSTANCES.put(hash, coords);
        // return the created reference
        return coord;
      }
    }
  }

  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    // our object gets finalized => remove it from instances

    int hash = hash3(latitude, longitude, radius);

    synchronized (INSTANCES) {
      List<WeakReference<SphericCoordinate>> coords = INSTANCES.get(hash);

      for (WeakReference<SphericCoordinate> refCoord : coords) {
        // check if we are the entry in the list
        if (refCoord.get() == this) {
          // jaj we are the entry in the list lets remove us
          // acquire the lock and remove us

          coords.remove(refCoord);
          // work done
          return;
        }
      }
    }
  }


  /**
   * Creates a spheric coordinate with given location and radius
   *
   * @param latitude of the coordinate [-90, 90]
   * @param longitude of the coordinate [-180, 180]
   * @param radius of the coordinate [0, inf)
   */
  private SphericCoordinate(double latitude, double longitude, double radius) {
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
   * Create a SphericCoordinate with the updated latitude
   *
   * @param latitude of the coordinate in degrees [-90, 90]
   */
  public SphericCoordinate withLatitude(double latitude) {
    ParameterUtil.assertNumberInRange(latitude, -90.0, 90.0, "latitude");
    return create(latitude, this.longitude, this.radius);
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
   * Create a SphericCoordinate with the updated longitude
   *
   * @param longitude of the coordinate in degrees [-180, 180]
   */
  public SphericCoordinate withLongitude(double longitude) {
    ParameterUtil.assertNumberInRange(longitude, -180.0, 180.0, "longitude");
    return create(this.latitude, longitude, this.radius);
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
   * Create a SphericCoordinate with the updated radius
   *
   * @param radius of the coordinate in km [0, inf)
   */
  public SphericCoordinate withRadius(double radius) {
    ParameterUtil.assertNumberInRange(radius, 0.0, Double.POSITIVE_INFINITY, "radius");
    return create(this.latitude, this.longitude, radius);
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
