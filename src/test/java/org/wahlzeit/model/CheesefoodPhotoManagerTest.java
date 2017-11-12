package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

public class CheesefoodPhotoManagerTest {

  @ClassRule
  public static RuleChain ruleChain = RuleChain.
      outerRule(new LocalDatastoreServiceTestConfigProvider()).
      around(new RegisteredOfyEnvironmentProvider());

  @Test
  public void testInitialized() {
    CheesefoodPhotoManager cfpm = CheesefoodPhotoManager.getInstance();
    Assert.assertNotNull(cfpm);
  }
}