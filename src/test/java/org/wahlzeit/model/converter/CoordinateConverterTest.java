package org.wahlzeit.model.converter;

import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import org.wahlzeit.model.CartesianCoordinate;
import org.wahlzeit.model.SphericCoordinate;

public class CoordinateConverterTest {

  /**
   * Tests the conversion from spheric to cartesian and backwards
   */
  @Test
  public void test_conversionSphericCartesian() throws Exception {
    /*
      We test our conversion by converting a spheric coordiate to a cartesian coordinate and then
      back to a spheric coordiate. If the original and the back converted are equal we have a
      bijection which should be a fine conversion.
     */

    // test the conversion with some random coordinates
    Random random = new Random();
    for (int i = 0; i < 100; i++) {
      convertSphericCartesian(random.nextDouble() * 180 - 90, random.nextDouble() * 360 - 180,
          random.nextDouble() * 10000.0);
    }

  }

  private void convertSphericCartesian(double lat1, double lon, double rad) {
    SphericCoordinate sphericCoordinate = SphericCoordinate.create(lat1, lon, rad);
    CartesianCoordinate cartesianCoordinate = CoordinateConverter
        .convertToCartesian(sphericCoordinate);
    SphericCoordinate sphericCoordinate2 = CoordinateConverter
        .convertToSpheric(cartesianCoordinate);

    Assert.assertEquals(sphericCoordinate.getLatitude(), sphericCoordinate2.getLatitude(), 0.0001);
    Assert
        .assertEquals(sphericCoordinate.getLongitude(), sphericCoordinate2.getLongitude(), 0.0001);
    Assert.assertEquals(sphericCoordinate.getRadius(), sphericCoordinate2.getRadius(), 0.0001);
  }


  /**
   * Tests if our generic convertTo misbehaves
   */
  @Test
  public void test_typeSphericCartesian() throws Exception {
    CartesianCoordinate c = CoordinateConverter
        .convertTo(SphericCoordinate.create(0,0,0), CartesianCoordinate.class);
    Assert.assertNotNull(c);
  }

  /**
   * Tests if our generic convertTo misbehaves
   */
  @Test
  public void test_typeCartesianSpheric() throws Exception {
    SphericCoordinate s = CoordinateConverter
        .convertTo(CartesianCoordinate.create(0, 0, 0), SphericCoordinate.class);
    Assert.assertNotNull(s);
  }

  /**
   * Tests if our generic convertTo detects if that the object already has the correct type and only
   * return the reference
   */
  @Test
  public void test_conversionSameType() throws Exception {

    SphericCoordinate s = SphericCoordinate.create(0,0,0);
    Assert.assertEquals(s, CoordinateConverter.convertTo(s, SphericCoordinate.class));

    CartesianCoordinate c = CartesianCoordinate.create(0, 0, 0);
    Assert.assertEquals(c, CoordinateConverter.convertTo(c, CartesianCoordinate.class));
  }
}