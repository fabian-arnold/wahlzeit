package org.wahlzeit.model;

import java.util.logging.Logger;
import org.wahlzeit.services.LogBuilder;

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
      log.config(LogBuilder.createSystemMessage().addAction("setting CheesefoodPhotoFactory").toString());
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
  public CheesefoodPhoto createPhoto(PhotoId id) {
    return new CheesefoodPhoto(id);
  }

}
