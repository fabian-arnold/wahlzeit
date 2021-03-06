/*
 * Copyright (c) 2006-2017 by Fabian Arnold
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 *
 */

package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;
import java.util.List;

@Subclass()
public class CheesefoodPhoto extends Photo {

  // Stores if the photo represents a cookable food
  protected boolean cookable;

  // Stores cooking instructions for the food. Markdown should be supported
  protected String recipe;

  // Stores the time in minutes needed for cooking this food
  protected Integer cookingDuration;

  // Stores the difficult level 6: very difficult 1: very simple
  protected Integer difficulty;

  // Stores if a oven is needed or not
  protected Boolean ovenNeeded;

  // Stores if a stove is needed or not
  protected Boolean stoveNeeded;

  // Stores which type of cheese is used (Emmentaler, Gauda, usw) seperated by a comma
  @Deprecated
  protected String cheeseType;

  // Stores the cheeses which are used
  protected List<Cheese> cheeses;

  /**
   * Creates a new cheesefood photo
   */
  public CheesefoodPhoto() {
    super();
  }

  /**
   * Creates a new cheesefood photo with a provided id
   *
   * @methodtype constructor
   */
  public CheesefoodPhoto(PhotoId myId) {
    super(myId);
  }

  /**
   * Returns if the food is cookable or not
   *
   * @return true if the food is cookable
   * @methodtype get
   */
  public boolean isCookable() {
    return cookable;
  }

  /**
   * Sets the value if the food is cookable or not
   *
   * @param cookable true if the food is cookable
   * @methodtype set
   */
  public void setCookable(boolean cookable) {
    this.cookable = cookable;
  }

  /**
   * Returns the recipe of the food
   *
   * @return the recipe in markdown
   * @methodtype get
   */
  public String getRecipe() {
    return recipe;
  }

  /**
   * Sets the recipe of the food
   *
   * @param recipe the recipe in markdown
   * @methodtype set
   */
  public void setRecipe(String recipe) {
    this.recipe = recipe;
  }

  /**
   * Returns the cooking duration
   *
   * @return cooking duration in minutes
   * @methodtype get
   */
  public Integer getCookingDuration() {
    return cookingDuration;
  }

  /**
   * Sets the cooking duration
   *
   * @param cookingDuration the cooking duration in minutes
   * @throws IllegalArgumentException the duration is negative
   * @methodtype set
   */
  public void setCookingDuration(Integer cookingDuration) {
    if (cookingDuration < 0) {
      throw new IllegalArgumentException("Illegal cooking duration");
    }
    this.cookingDuration = cookingDuration;
  }

  /**
   * Returns the difficult level of the recipe. The returned value is in range from 1 to 6; 1:
   * Simple 6: Very difficult
   *
   * @return the difficult level between 1 and 6
   * @methodtype get
   */
  public Integer getDifficulty() {
    return difficulty;
  }

  /**
   * Sets the difficult level of the recipe. The value is in range from 1 to 6; 1: Simple 6: Very
   * difficult
   *
   * @param difficulty the difficult level between 1 and 6
   * @throws IllegalArgumentException the difficulty is out of range
   * @methodtype set
   */
  public void setDifficulty(Integer difficulty) {
    if (difficulty != null && (difficulty < 1 || difficulty > 6)) {
      throw new IllegalArgumentException("difficulty out of range");
    }
    this.difficulty = difficulty;
  }

  /**
   * Returns if a oven is needed or not. If the value is <code>null</code> there is no info if a
   * oven is needed.
   *
   * @return true if a oven is needed
   * @methodtype get
   */
  public Boolean getOvenNeeded() {
    return ovenNeeded;
  }

  /**
   * Sets if a oven is needed. If you pass <code>null</code> as value it means you have no info if a
   * oven is needed or not.
   *
   * @param ovenNeeded true if a oven is needed
   * @methodtype set
   */
  public void setOvenNeeded(Boolean ovenNeeded) {
    this.ovenNeeded = ovenNeeded;
  }

  /**
   * Returns if a stove is needed or not. If the value is <code>null</code> there is no info if a
   * stove is needed.
   *
   * @return true if a stove is needed
   * @methodtype get
   */
  public Boolean getStoveNeeded() {
    return stoveNeeded;
  }

  /**
   * Sets if a stove is needed. If you pass <code>null</code> as value it means you have no info if
   * a stove is needed or not.
   *
   * @param stoveNeeded true if a stove is needed
   * @methodtype set
   */
  public void setStoveNeeded(Boolean stoveNeeded) {
    this.stoveNeeded = stoveNeeded;
  }

  /**
   * Returns the type of cheese in the picture If the value is <code>null</code> there is no info
   * which type of cheese is used. Multiple types of cheese are separated by a comma.
   *
   * @return the type(s) of cheese
   * @methodtype get
   */
  @Deprecated
  public String getCheeseType() {
    return cheeseType;
  }

  /**
   * Sets the type of cheese in the picture. If you pass <code>null</code> as value it means you
   * have no info which type of cheese is used. Multiple types of cheese are separated by a comma
   *
   * @param cheeseType the type(s) of cheese
   * @methodtype set
   */
  @Deprecated
  public void setCheeseType(String cheeseType) {
    this.cheeseType = cheeseType;
  }

  /**
   * Returns a List of all used cheeses
   */
  public List<Cheese> getCheeses() {
    return cheeses;
  }

  /**
   * Sets the list of all used cheeses
   */
  public void setCheeses(List<Cheese> cheeses) {
    this.cheeses = cheeses;
  }
}
