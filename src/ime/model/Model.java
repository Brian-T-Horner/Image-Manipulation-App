package ime.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * IME.model.Model interface for our MVC designed program.
 */
public interface Model {

  /**
   * Retrieves an image in the model given the image's name.
   *
   * @param imageName image to retrieve
   * @return Image stored in the model
   */
  Image getImage(String imageName);

  /**
   * Method to load an image - calls the image object method.
   *
   * @param imagePath    The path of the image to load.
   * @param newImageName The name to assign to the loaded image.
   */
  void loadImage(String imagePath, String newImageName)
          throws FileNotFoundException;

  /**
   * Method to save an image - calls the image object method.
   *
   * @param imagePath The path of where to save the image.
   * @param imageName The name of the image to save.
   */
  void saveImage(String imagePath, String imageName)
          throws IOException, NoSuchElementException;

  /**
   * Method to get the red component of an image - calls the image object method.
   *
   * @param currentImageName The name of the image to get the red component from.
   * @param newImageName     The name to save the resulting image as.
   */
  void getRedComponent(String currentImageName, String newImageName)
          throws FileNotFoundException;

  /**
   * Method to get the green component of an image - calls the image object method.
   *
   * @param currentImageName The name of the image to get the green component from.
   * @param newImageName     The name to save the resulting image as.
   */
  void getGreenComponent(String currentImageName, String newImageName)
          throws FileNotFoundException;

  /**
   * Method to get the blue component of an image - calls the image object method.
   *
   * @param currentImageName The name of the image to get the blue component from.
   * @param newImageName     The name to save the resulting image as.
   */
  void getBlueComponent(String currentImageName, String newImageName)
          throws FileNotFoundException;

  /**
   * Method to flip an image horizontally - calls the image object method.
   *
   * @param currentImageName The name of the image to flip horizontally.
   * @param newImageName     The name to save the resulting image as.
   */
  void flipHorizontal(String currentImageName, String newImageName)
          throws NoSuchElementException;

  /**
   * Method to flip an image vertically - calls the image object method.
   *
   * @param currentImageName The name of the image to flip vertically.
   * @param newImageName     The name to save the resulting image as.
   */
  void flipVertical(String currentImageName, String newImageName)
          throws NoSuchElementException;

  /**
   * Method to get the value image of an image - calls the image object method.
   *
   * @param currentImageName The name of the image to get the value image from.
   * @param newImageName     The name to save the resulting image as.
   */
  void getValueImage(String currentImageName, String newImageName)
          throws NoSuchElementException;

  /**
   * Method to get the intensity image of an image - calls the image object method.
   *
   * @param currentImageName The name of the image to get the intensity image from.
   * @param newImageName     The name to save the resulting image as.
   */
  void getIntensityImage(String currentImageName, String newImageName)
      throws NoSuchElementException;

  /**
   * Method to get the luma image of an image - calls the image object method.
   *
   * @param currentImageName The name of the image to get the luma image from.
   * @param newImageName     The name to save the resulting image as.
   */
  void getLumaImage(String currentImageName, String newImageName)
          throws NoSuchElementException;

  /**
   * Method to brighten or darken an image - calls the image object method.
   *
   * @param currentImageName The name of the image to brighten or darken.
   * @param newImageName     The name to save the resulting image as.
   * @param scale            The scale to brighten or darken the image.
   */
  void brighten(String currentImageName, String newImageName, int scale)
      throws NoSuchElementException;

  /**
   * Method to split an image into its rgb components - calls the image object method.
   *
   * @param currentImageName The name of the image to split into its rgb components.
   * @param redImageName     The name to save the resulting red component image as.
   * @param greenImageName   The name to save the resulting green component image as.
   * @param blueImageName    The name to save the resulting blue component image as.
   */
  void rgbSplit(String currentImageName, String redImageName, String greenImageName,
      String blueImageName) throws NoSuchElementException;

  /**
   * Method to combine rgb components into one image.
   *
   * @param newImageName The name to save the resulting image as.
   * @param rImageName   The name of the red component image.
   * @param gImageName   The name of the green component image.
   * @param bImageName   The name of the blue component image.
   */
  void rgbCombine(String newImageName, String rImageName, String gImageName, String bImageName)
      throws NoSuchElementException;

  void rgbCombine(String newImageName, Image redImage, Image greenImage, Image blueImage);

  /**
   * Method to get the greyscale image of an image - calls the image object method.
   *
   * @param greyScaleComponent The method to get the grey scale of the image.
   * @param imageName          The image to get the grey scale image from.
   * @param newImageName       The name to save the resulting image as.
   */
  void greyscale(String greyScaleComponent, String imageName, String newImageName)
      throws NoSuchElementException;

  /**
   * Method to get the dither image of an image.
   * @param imageName The image to get the dither image from.
   * @param newImageName The name to save the resulting image as.
   */
  void dither(String imageName, String newImageName);

  /**
   * Method to get the sepia image of an image.
   * @param imageName The image to get the sepia image from.
   * @param newImageName The name to save the resulting image as.
   */
  void sepia(String imageName, String newImageName);

  /**
   * Method to get the sharpened image of an image.
   * @param imageName The image to get the sharpened image from.
   * @param newImageName The name to save the resulting image as.
   */
  void sharpen(String imageName, String newImageName);

  /**
   * Method to get the blurred image of an image.
   * @param imageName The image to get the blurred image from.
   * @param newImageName The name to save the resulting image as.
   */
  void blur(String imageName, String newImageName);

  /**
   * Method to get the greyscale image of an image via matrix multiplication.
   * @param imageName The image to get the greyscale image from.
   * @param newImageName The name to save the resulting image as.
   */
  void matrixGreyscale(String imageName, String newImageName);

}
