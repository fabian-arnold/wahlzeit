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

import java.util.logging.Logger;
import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.utils.ParameterUtil;
import org.wahlzeit.utils.doc.DesignPattern;
import org.wahlzeit.utils.doc.pattern.PatternType;

@DesignPattern(value = {PatternType.FACTORY,
    PatternType.SINGLETON}, participants = CheesefoodPhoto.class)
public class CheesefoodPhotoFactory extends PhotoFactory {

  private static final Logger log = Logger.getLogger(CheesefoodPhotoFactory.class.getName());

  /**
   * Hidden singleton instance; needs to be initialized from the outside.
   */
  private static CheesefoodPhotoFactory instance = null;

  /**
   *
   */
  protected CheesefoodPhotoFactory() {
    // do nothing
  }

  /**
   * Hidden singleton instance; needs to be initialized from the outside.
   */
  public static void initialize() {
    getInstance(); // drops result due to getInstance() side-effects
  }

  /**
   * Public singleton access method.
   */
  public static synchronized CheesefoodPhotoFactory getInstance() {
    if (instance == null) {
      log.config(
          LogBuilder.createSystemMessage().addAction("setting CheesefoodPhotoFactory").toString());
      setInstance(new CheesefoodPhotoFactory());
    }

    return instance;
  }

  /**
   * Method to set the singleton instance of PhotoFactory.
   */
  protected static synchronized void setInstance(CheesefoodPhotoFactory photoFactory) {
    if (instance != null) {
      throw new IllegalStateException("attempt to initalize CheesefoodPhotoFactory twice");
    }

    if (photoFactory == null) {
      throw new IllegalArgumentException("Attempt to call setInstance with null value. This is "
          + "probably not what you wanted.");
    }

    instance = photoFactory;
  }

  /**
   * @methodtype factory
   */
  public CheesefoodPhoto createPhoto() {
    return new CheesefoodPhoto();
  }

  /**
   * Creates a new photo with the specified id
   */
  public CheesefoodPhoto createPhoto(PhotoId photoId) {
    ParameterUtil.assertNotNull(photoId, "photoId");
    return new CheesefoodPhoto(photoId);
  }

}
