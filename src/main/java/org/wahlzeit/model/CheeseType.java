package org.wahlzeit.model;

import java.util.HashSet;
import java.util.Set;
import org.wahlzeit.utils.ParameterUtil;

public class CheeseType {

  private String cheeseOrigin;

  private String cheeseName;

  private CheeseType supertype;

  private Set<CheeseType> subtypes = new HashSet<>();

  public CheeseType(String cheeseOrigin, String cheeseName) {
    this.cheeseOrigin = cheeseOrigin;
    this.cheeseName = cheeseName;
  }

  /**
   * Returns the origin of the Cheese
   */
  public String getCheeseOrigin() {
    return cheeseOrigin;
  }

  /**
   * Returns the name of the cheese
   */
  public String getCheeseName() {
    return cheeseName;
  }

  /**
   * Creates a new cheesefood of the current type
   *
   * @param amount of cheese in [g]
   * @param age of chese in months
   * @return the new cheesefood
   */
  public Cheese newCheesefood(Integer amount, Integer age) {
    Cheese cheesefood = new Cheese(this);
    cheesefood.setAge(age);
    cheesefood.setAmount(amount);
    return cheesefood;
  }

  public CheeseType getSupertype() {
    return supertype;
  }

  public void setSupertype(CheeseType supertype) {
    this.supertype = supertype;
  }

  public Set<CheeseType> getSubtypes() {
    return subtypes;
  }

  /**
   * Searches the hierarchy of types if type exists or not.
   *
   * @param type to find in the tree
   * @return true if the type is in the hierarchy
   */
  public boolean isSubtype(CheeseType type) {
    ParameterUtil.assertNotNull(type, "type");

    // base case
    if (this == type) {
      return true;
    }

    for (CheeseType t : subtypes) {
      // recursion call
      if (t.isSubtype(type)) {
        return true;
      }
    }

    // not found in our tree
    return false;
  }
}
