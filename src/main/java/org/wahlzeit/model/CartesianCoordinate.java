package org.wahlzeit.model;


import org.wahlzeit.utils.ParameterUtil;

/**
 * Class that provides a cartesian coordinates.
 */
public class CartesianCoordinate extends AbstractCoordinate {

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
  public CartesianCoordinate() {
    // we can not use setters here because it would break our nowhere implementation
    x = 0;
    y = 0;
    z = 0;
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
  public CartesianCoordinate(double x, double y, double z) {
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
    ParameterUtil.assertValidNumber(x, "x");
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
    ParameterUtil.assertValidNumber(y, "y");
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
    ParameterUtil.assertValidNumber(z, "z");
    this.z = z;
  }

  /**
   * Returns true if compared to an other coordinate and all components are equal
   *
   * @param o an object ot compare with
   * @return true if o is an coordinate and all components are equal
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CartesianCoordinate)) {
      return false;
    }

    CartesianCoordinate that = (CartesianCoordinate) o;

    if (Double.compare(that.x, x) != 0) {
      return false;
    }
    if (Double.compare(that.y, y) != 0) {
      return false;
    }
    return Double.compare(that.z, z) == 0;
  }


  /**
   * Calculates an hash for every coordinate. The hash is equal if all components are equal.
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
    return "CartesianCoordinate{" +
        "x=" + x +
        ", y=" + y +
        ", z=" + z +
        '}';
  }

}
