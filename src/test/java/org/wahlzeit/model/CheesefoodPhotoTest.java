package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

public class CheesefoodPhotoTest {

  @ClassRule
  public static RuleChain ruleChain = RuleChain.
      outerRule(new LocalDatastoreServiceTestConfigProvider()).
      around(new RegisteredOfyEnvironmentProvider());

  /**
   * Tests if invalid cooking durations throw an exception
   */
  @Test(expected = IllegalArgumentException.class)
  public void setInvalidCookingDuration() throws Exception {
    CheesefoodPhoto cheesefoodPhoto = new CheesefoodPhoto();
    cheesefoodPhoto.setCookingDuration(-1);
  }

  /**
   * Tests if invalid difficult levels throw an exception
   */
  @Test
  public void setInvalidDifficulty() throws Exception {
    CheesefoodPhoto cheesefoodPhoto = new CheesefoodPhoto();
    try {
      cheesefoodPhoto.setDifficulty(7);
      Assert.fail("Invalid difficults are possible");
    } catch (IllegalArgumentException ignored) {
    }

    try {
      cheesefoodPhoto.setDifficulty(0);
      Assert.fail("Invalid difficults are possible");
    } catch (IllegalArgumentException ignored) {
    }
  }

}