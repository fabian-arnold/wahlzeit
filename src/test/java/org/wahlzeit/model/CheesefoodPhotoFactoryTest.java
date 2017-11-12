package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

public class CheesefoodPhotoFactoryTest {

  @Before
  public void setUp(){
    CheesefoodPhotoFactory.initialize();
  }

  @Test
  public void testInitalized(){
    Assert.assertNotNull(CheesefoodPhotoFactory.getInstance());
  }

  @Test(expected = IllegalStateException.class)
  public void testMultipleInitializations(){
    CheesefoodPhotoFactory.setInstance(new CheesefoodPhotoFactory());
  }
}