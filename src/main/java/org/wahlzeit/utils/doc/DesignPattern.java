package org.wahlzeit.utils.doc;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.wahlzeit.utils.doc.pattern.PatternType;

/**
 * This Annotation provides a centralized way to document design patterns for easy understanding of
 * the code.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface DesignPattern {

  /**
   * The Type of pattern which is used
   *
   * @see PatternType
   */
  PatternType[] value();

  /**
   * A short description what is the intend of the pattern
   */
  String intend() default "";

  /**
   * A list of all participated classes
   */
  Class<?>[] participants() default {};

}
