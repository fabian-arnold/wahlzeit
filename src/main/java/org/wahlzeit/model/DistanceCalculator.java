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
