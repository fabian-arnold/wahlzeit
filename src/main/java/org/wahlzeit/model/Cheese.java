package org.wahlzeit.model;

public class Cheese {

  private final CheeseType cheeseType;

  private Integer amount;

  private Integer age;

  /**
   * Creates a Cheesfood of given type
   */
  public Cheese(CheeseType cheeseType) {
    this.cheeseType = cheeseType;
  }

  /**
   * Returns the type of cheesefood
   */
  public CheeseType getCheeseType() {
    return cheeseType;
  }

  /**
   * Returns the amount of cheese in [g]
   *
   * @return amount in [g]
   */
  public Integer getAmount() {
    return amount;
  }

  /**
   * Sets the amount of cheese in [g]
   *
   * @param amount in [g]
   */
  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  /**
   * Returns the age in months
   *
   * @return age in months
   */
  public Integer getAge() {
    return age;
  }

  /**
   * Sets the age in months
   *
   * @param age in months
   */
  public void setAge(Integer age) {
    this.age = age;
  }
}
