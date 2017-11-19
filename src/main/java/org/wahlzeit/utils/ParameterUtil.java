package org.wahlzeit.utils;

/**
 * This class provides checks for parameters. With nice error output.
 */
public class ParameterUtil {

  /**
   * Private constructor to prevent instantiation of this class
   */
  private ParameterUtil() {

  }

  public static void assertNumberInRange(Double number, Double lowerBound, Double upperBound,
      String parameterName) {
    assertValidNumber(number, parameterName);
    if (number > upperBound) {
      throw new IllegalArgumentException(
          "The passed value for '" + parameterName + "' exceeded the upper limit of '" + upperBound
              + "'");
    }
    if (number < lowerBound) {
      throw new IllegalArgumentException(
          "The passed value for '" + parameterName + "' exceeded the lower limit of '" + lowerBound
              + "'");
    }
  }

  /**
   * Asserts that the given number is numeric.
   *
   * @param number to check
   * @param parameterName for nice output
   * @throws IllegalArgumentException if the given number is not numeric
   */
  public static void assertValidNumber(Double number, String parameterName) {
    assertNotNull(number, parameterName);
    if (Double.isNaN(number) || Double.isInfinite(number)) {
      throw new IllegalArgumentException(
          "You passed a non numeric value for the parameter '" + parameterName + "' ('" + String
              .valueOf(number) + "'). Please ensure this does not happen.");
    }
  }

  /**
   * Asserts that a given object is not null
   *
   * @param object to check
   * @param parameterName for nice output
   * @param <T> type of the object
   */
  public static <T> void assertNotNull(T object, String parameterName) {
    if (object == null) {
      throw new IllegalArgumentException(
          "You passed null as value for the parameter: '" + parameterName
              + "'. Please ensure this does no happen.");
    }
  }

}
