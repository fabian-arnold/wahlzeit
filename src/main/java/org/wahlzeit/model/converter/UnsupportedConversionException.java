package org.wahlzeit.model.converter;

/**
 * This exception indicates that a conversion from one coordinate to an other failed.
 *
 * This is a runtime time exception by design, because in some cases it is not necessary to handle
 * this exception because it won´t happen.
 */
public class UnsupportedConversionException extends RuntimeException {

  private final Class conversionTarget;
  private final Class conversionFrom;

  /**
   * Creates a new {@code UnsupportedConversionException}
   *
   * @param conversionTarget target class the conversion was tried to
   * @param conversionFrom class to conversion was tried from
   */
  public UnsupportedConversionException(Class conversionTarget, Class conversionFrom) {
    super();

    this.conversionTarget = conversionTarget;
    this.conversionFrom = conversionFrom;
  }

  /**
   * Creates a new {@code UnsupportedConversionException}
   *
   * @param detailMsg what went wrong and if possible a hint how to fix
   * @param conversionTarget target class the conversion was tried to
   * @param conversionFrom class to conversion was tried from
   */
  public UnsupportedConversionException(String detailMsg, Class conversionTarget,
      Class conversionFrom) {
    super(detailMsg);

    this.conversionTarget = conversionTarget;
    this.conversionFrom = conversionFrom;
  }

  /**
   * Creates a new {@code UnsupportedConversionException}
   *
   * @param detailMsg what went wrong and if possible a hint how to fix
   * @param conversionTarget target class the conversion was tried to
   * @param conversionFrom class to conversion was tried from
   * @param cause the cause the exception was thrown
   */
  public UnsupportedConversionException(String detailMsg, Class conversionTarget,
      Class conversionFrom, Throwable cause) {
    super(detailMsg, cause);

    this.conversionTarget = conversionTarget;
    this.conversionFrom = conversionFrom;
  }

  /**
   * Returns the class the conversion to was tried but failed
   */
  public Class getConversionTarget() {
    return conversionTarget;
  }

  /**
   * Returns the class the conversion was tried from but failed
   */
  public Class getConversionFrom() {
    return conversionFrom;
  }
}
