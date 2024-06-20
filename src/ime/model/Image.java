package ime.model;

/**
 * Interface for accessing an image file.
 */
public interface Image {

  /**
   * Method to return the red component of an IME.model.Image.
   *
   * @return An integer array of red component values.
   */
  int[][] getRedComponent();

  /**
   * Method to return the green component of an IME.model.Image.
   *
   * @return An integer array of green component values.
   */
  int[][] getGreenComponent();

  /**
   * Method to return the blue component of an IME.model.Image.
   *
   * @return
   */
  int[][] getBlueComponent();

  /**
   * Method to return the height of the IME.model.Image in pixels.
   *
   * @return Integer representing pixel height of the IME.model.Image.
   */
  int getHeight();

  /**
   * Method to return the width of the Image in pixels.
   *
   * @return Integer representing the pixel width of the IME.model.Image.
   */
  int getWidth();


  /**
   * Method to determine if two Image objects are equal.
   *
   * @param o An object to check if equal to {@code this} PPMImage.
   * @return True if equal, false if otherwise.
   */
  boolean equals(Object o);


  /**
   * Method to get a hashCode for an Image.
   *
   * @return An integer hashcode for the object.
   */
  int hashCode();

}
