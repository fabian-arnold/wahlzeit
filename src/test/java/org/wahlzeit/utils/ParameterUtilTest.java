package org.wahlzeit.utils;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class ParameterUtilTest {

  @Test()
  public void assertNumberInRange() throws Exception {
    try{
      ParameterUtil.assertNumberInRange(34.0, 1.0, 32.0, "testParameter");
      Assert.fail("upperBound ignored");
    } catch (IllegalArgumentException ignored){    }
    try{
      ParameterUtil.assertNumberInRange(0.0, 1.0, 32.0, "testParameter");
      Assert.fail("lowerBound ignored");
    } catch (IllegalArgumentException ignored){    }
  }

  @Test
  public void assertValidNumber() throws Exception {
    try{
      ParameterUtil.assertValidNumber(Double.POSITIVE_INFINITY, "testParameter");
      Assert.fail("not valid number passed test");
    } catch (IllegalArgumentException ignored){    }
    try{
      ParameterUtil.assertValidNumber(Double.NEGATIVE_INFINITY, "testParameter");
      Assert.fail("not valid number passed test");
    } catch (IllegalArgumentException ignored){    }
    try{
      ParameterUtil.assertValidNumber(Double.NaN, "testParameter");
      Assert.fail("not valid number passed test");
    } catch (IllegalArgumentException ignored){    }
  }

  @Test
  public void assertNotNull() throws Exception {
    try{
      ParameterUtil.assertNotNull(null, "testParameter");
      Assert.fail("null value passed null check");
    } catch (IllegalArgumentException ignored){    }
  }

}