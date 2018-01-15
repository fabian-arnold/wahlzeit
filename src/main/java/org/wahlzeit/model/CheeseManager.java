package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Map;
import org.wahlzeit.utils.ParameterUtil;

public class CheeseManager {

  private static final CheeseManager instance = new CheeseManager();

  public static CheeseManager getInstance() {
    return instance;
  }


  private static final Map<String, CheeseType> types = new HashMap<>();


  /**
   * Creates a cheesefood type identified by its name
   *
   * @param origin of the type
   * @param name of the type
   * @return the new type
   */
  public CheeseType getCheeseType(String origin, String name) {
    ParameterUtil.assertNotNull(name, "Name");
    ParameterUtil.assertNotNull(origin, "Origin");
    CheeseType type = new CheeseType(origin, name);
    types.putIfAbsent(name, type);
    return type;
  }

  /**
   * Creates a cheesefood type identified by its name
   *
   * @param name of the type
   * @return the new type
   */
  public CheeseType getCheeseType(String name) {
    return getCheeseType("", name);
  }

  public CheeseType getCheeseType(String name, CheeseType supertype) {
    ParameterUtil.assertNotNull(supertype, "Supertype");
    CheeseType cheeseType = getCheeseType(name);
    cheeseType.setSupertype(supertype);
    supertype.getSubtypes().add(cheeseType);
    return cheeseType;
  }


}
