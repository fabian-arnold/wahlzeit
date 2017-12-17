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
import org.wahlzeit.utils.ParameterUtil;

/**
 * Class that provides a cartesian coordinates.
 */
public class CartesianCoordinate extends AbstractCoordinate {

  /**
   * Stores the x-component of the coordinate
   */
  private final double x;

  /**
   * Stores the y-component of the coordinate
   */
  private final double y;

  /**
   * Stores the z-component of the coordinate
   */
  private final double z;

  /**
   * This map stores all instances of cartesian coordinates
   */
  private static final HashMap<Integer, List<WeakReference<CartesianCoordinate>>> INSTANCES = new HashMap<>();

  /**
   * Creates a new CartesianCoordinate with given x, y and z
   *
   * @param x z-Component of the coordinate
   * @param y y-Component of the coordinate
   * @param z z-Component of the coordinate
   * @throws IllegalArgumentException throws an exception if the provided components are non
   * numeric
   */
  public synchronized static CartesianCoordinate create(double x, double y, double z) {
    // ensure a object get not persist twice
    synchronized (INSTANCES) {
      ParameterUtil.assertValidNumber(x, "x");
      ParameterUtil.assertValidNumber(y, "y");
      ParameterUtil.assertValidNumber(z, "z");

      // look if we already have a instance with the provided coordinates
      int hash = hash3(x, y, z);
      CartesianCoordinate coord;
      if (INSTANCES.containsKey(hash)) {
        // there is already a list of objects with this hash
        List<WeakReference<CartesianCoordinate>> coords = INSTANCES.get(hash);
        for (WeakReference<CartesianCoordinate> refCoord : coords) {
          coord = refCoord.get();
          if (coord == null) {
            // the finalizer will clean it up probably
            continue;
          }
          if (coord.getX() == x && coord.getY() == y && coord.getZ() == z) {
            return coord;
          }
        }
      /*
       The reference was deleted by the gc or we have no matching
       => Delete the invalid reference and create a new reference
       */
        coord = new CartesianCoordinate(x, y, z);
        coords.add(new WeakReference<>(coord));
        // return the created reference
        return coord;
      } else {
        // the hash is not existent => create a list of objects with this hash
        List<WeakReference<CartesianCoordinate>> coords = new ArrayList<>();
        // create our new coordinate
        coord = new CartesianCoordinate(x, y, z);
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

    int hash = hash3(x, y, z);

    synchronized (INSTANCES) {
    List<WeakReference<CartesianCoordinate>> coords = INSTANCES.get(hash);

      for (WeakReference<CartesianCoordinate> refCoord : coords) {
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
   * Creates a new CartesianCoordinate with given x, y and z
   *
   * @param x z-Component of the coordinate
   * @param y y-Component of the coordinate
   * @param z z-Component of the coordinate
   * @throws IllegalArgumentException throws an exception if the provided components are non
   * numeric
   */
  private CartesianCoordinate(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Gets the x-component of the coordinate
   *
   * @return x-component in km
   */
  public double getX() {
    return x;
  }

  /**
   * Returns a Coordinate with updated x
   *
   * @param x of the coordinate in km
   * @return Coordinate with same y, z and updated x
   * @throws IllegalArgumentException throws an exception if the provided value is non numeric
   */
  public CartesianCoordinate withX(double x) {
    ParameterUtil.assertValidNumber(x, "x");
    return CartesianCoordinate.create(x, this.y, this.z);
  }

  /**
   * Gets the y-component of the coordinate
   *
   * @return y-component in km
   */
  public double getY() {
    return y;
  }

  /**
   * Returns a Coordinate with updated y
   *
   * @param y of the coordinate in km
   * @return Coordinate with same x, z and updated y
   * @throws IllegalArgumentException throws an exception if the provided value is non numeric
   */
  public CartesianCoordinate withY(double y) {
    ParameterUtil.assertValidNumber(y, "y");
    return CartesianCoordinate.create(this.x, y, this.z);
  }

  /**
   * Gets the z-component of the coordinate
   *
   * @return z-component in km
   */
  public double getZ() {
    return z;
  }


  /**
   * Returns a Coordinate with updated z
   *
   * @param z of the coordinate in km
   * @return Coordinate with same x, z and updated z
   * @throws IllegalArgumentException throws an exception if the provided value is non numeric
   */
  public CartesianCoordinate withZ(double z) {
    ParameterUtil.assertValidNumber(z, "z");
    return CartesianCoordinate.create(this.x, this.y, z);
  }


  /**
   * Converts the coordinate to an string
   *
   * @return coordinate as string
   */
  @Override
  public String toString() {
    return "CartesianCoordinate{" +
        "x=" + x +
        ", y=" + y +
        ", z=" + z +
        '}';
  }

}
