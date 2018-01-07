package org.wahlzeit.utils;

public class HashUtil {

  /**
   * Calculates a hash for 3 double values
   *
   * @return hashed value of the doubles
   */
  public static int hash3(double d1, double d2, double d3) {
    int result;
    long temp;
    temp = Double.doubleToLongBits(d1);
    result = (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(d2);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(d3);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }
}
