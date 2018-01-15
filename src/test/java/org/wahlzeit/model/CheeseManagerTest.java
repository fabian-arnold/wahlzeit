package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CheeseManagerTest {

  @Test
  public void testCheeseTypes() {
    CheeseType cheeseType = CheeseManager.getInstance().getCheeseType("Test");
    assertEquals(cheeseType.getCheeseName(), "Test");
    cheeseType = CheeseManager.getInstance().getCheeseType("Test1", "Test2");
    assertEquals(cheeseType.getCheeseName(), "Test2");
    assertEquals(cheeseType.getCheeseOrigin(), "Test1");
    CheeseType child = CheeseManager.getInstance().getCheeseType("asdf", cheeseType);
    assertEquals(cheeseType.isSubtype(child), true);

    Cheese cheese = child.newCheesefood(10, 20);
    assertEquals((long) cheese.getAge(), 20);
    assertEquals((long) cheese.getAmount(), 10);
    assertEquals(cheese.getCheeseType(), child);
  }

}