package org.wahlzeit.model.converter;

import org.wahlzeit.model.CartesianCoordinate;
import org.wahlzeit.model.Coordinate;
import org.wahlzeit.model.SphericCoordinate;
import org.wahlzeit.utils.ParameterUtil;

/**
 * This class provides conversion between different coordinate implementations
 */
public class CoordinateConverter {


  /**
   * Private constructor to prevent instantiation of this class
   */
  private CoordinateConverter() {

  }

  /**
   * Converts the spheric coordinate to a cartesian coordinate
   *
   * @param sphericCoordinate to convert
   * @return the converted cartesian coordinate
   */
  public static CartesianCoordinate convertToCartesian(SphericCoordinate sphericCoordinate) {
    ParameterUtil.assertNotNull(sphericCoordinate, "sphericCoordinate");

    CartesianCoordinate cartesianCoordinate;
    cartesianCoordinate = impl_convertToCartesian(sphericCoordinate);
    return cartesianCoordinate;
  }

  /**
   * Does the actual conversion from a spheric coordinate to a cartesian coordinate
   *
   * @param sphericCoordinate to convert
   * @return converted cartesian coordinate
   */
  private static CartesianCoordinate impl_convertToCartesian(SphericCoordinate sphericCoordinate) {
     /*
     conversion logic from: https://vvvv.org/blog/polar-spherical-and-geographic-coordinates.
     We use the conversion for geographic coordinates because of the inconsistent naming in the
     SperhicCoordinate.
     We should fix this in further releases and change the member names of spheric coordiates
     from latitude to polar and from longitude to azimuthal. And introduce a new
     GeographicalCoordinate which represents locations on earth.
     */
    CartesianCoordinate cartesianCoordinate;
    double x =
        sphericCoordinate.getRadius() * Math.cos(Math.toRadians(sphericCoordinate.getLatitude()))
            * Math.sin(Math.toRadians(sphericCoordinate.getLongitude()));

    double y =
        sphericCoordinate.getRadius() * Math.sin(Math.toRadians(sphericCoordinate.getLatitude()));

    double z =
        -sphericCoordinate.getRadius() * Math.cos(Math.toRadians(sphericCoordinate.getLatitude()))
            * Math.cos(Math.toRadians(sphericCoordinate.getLongitude()));

    cartesianCoordinate = new CartesianCoordinate(x, y, z);

    return cartesianCoordinate;
  }

  /**
   * Converts the cartesian coordinate to a spheric coordinate
   *
   * @param cartesianCoordinate to convert
   * @return the converted spheric coordinate
   */
  public static SphericCoordinate convertToSpheric(CartesianCoordinate cartesianCoordinate) {

    ParameterUtil.assertNotNull(cartesianCoordinate, "cartesianCoordinate");

    SphericCoordinate sphericCoordinate;
    sphericCoordinate = impl_convertToSpheric(cartesianCoordinate);
    return sphericCoordinate;
  }

  /**
   * Does the actual conversion from a cartesian coordinate to a spherical coordinate
   *
   * @param cartesianCoordinate to convert
   * @return converted spherical coordinate
   */
  private static SphericCoordinate impl_convertToSpheric(CartesianCoordinate cartesianCoordinate) {

    /*
     conversion logic from: https://vvvv.org/blog/polar-spherical-and-geographic-coordinates.
     We use the conversion for geographic coordinates because of the inconsistent naming in the
     SperhicCoordinate.
     We should fix this in further releases and change the member names of spheric coordiates
     from latitude to polar and from longitude to azimuthal. And introduce a new
     GeographicalCoordinate which represents locations on earth.
     */

    SphericCoordinate sphericCoordinate;
    double radius = Math.sqrt(
        Math.pow(cartesianCoordinate.getX(), 2) + Math.pow(cartesianCoordinate.getY(), 2) + Math
            .pow(cartesianCoordinate.getZ(), 2));
    double latitude = Math.toDegrees(Math.asin(cartesianCoordinate.getY() / radius));
    double longitude = Math
        .toDegrees(Math.atan2(cartesianCoordinate.getX(), -cartesianCoordinate.getZ()));
    sphericCoordinate = new SphericCoordinate(latitude, longitude, radius);
    return sphericCoordinate;
  }

  /**
   * Selects a fitting converter for the coordinate and converts it. It checks if the conversion is
   * possible otherwise an {@link UnsupportedOperationException} is thrown <p> Supported conversions
   * are: <ul> <li>CartesianCoordinate => SphericCoordinate</li> <li>SphericCoordinate =>
   * CartesianCoordinate</li> </ul>
   *
   * @param coordinate to convert
   * @param targetClass to convert to
   * @param <T> Type of the target class
   * @return coordinate in targetClass
   * @throws UnsupportedOperationException if conversion failed
   */
  @SuppressWarnings("unchecked")
  public static <T extends Coordinate> T convertTo(Coordinate coordinate, Class<T> targetClass) {

    /*
      This method provides a generic way to convert coordinates.
     */

    // check of parameters to prevent nullpointer
    ParameterUtil.assertNotNull(coordinate, "coordinate");
    ParameterUtil.assertNotNull(targetClass, "targetClass");

    // check if we already have the target class
    if (targetClass.isInstance(coordinate)) {
      // coordinate is already an instance of targetClass
      return (T) coordinate;
    }

    T x = impl_convertTo(coordinate, targetClass);

    if (x != null) {
      return x;
    }

    // we found no conversion => throw an exception
    throw new UnsupportedOperationException("Cannot cast from " + coordinate.getClass() + " to " +
        targetClass + ".\nCheck JavaDoc of CoordinateConverter for supported casts.");
  }

  /**
   * This method does the actual conversion of two coordinates
   *
   * @param coordinate to convert
   * @param targetClass to convert to
   * @param <T> tpye of the target class
   * @return converted coordinate or null if not possible
   */
  @SuppressWarnings("unchecked")
  private static <T extends Coordinate> T impl_convertTo(Coordinate coordinate,
      Class<T> targetClass) {
    // actual conversion with known converters.
    // These should register by itself but this is to much for now.
    if (targetClass.equals(SphericCoordinate.class) && coordinate instanceof CartesianCoordinate) {
      // we have a cartesian coordinate and want to convert it to a spheric
      return (T) impl_convertToSpheric((CartesianCoordinate) coordinate);
    }
    if (targetClass.equals(CartesianCoordinate.class) && coordinate instanceof SphericCoordinate) {
      // we have a cartesian coordinate and want to convert it to a spheric
      return (T) impl_convertToCartesian((SphericCoordinate) coordinate);
    }
    return null;
  }

}
