import ime.model.Image;
import ime.model.Model;


/**
 * MockModel for testing Controller in isolation.
 */
public class MockModel implements Model {

  private StringBuilder log;
  private final int uniqueCode;


  /**
  * Constructor for MockModel object.
  * @param newLog StringBuilder object used for checking controller passed arguments.
  * @param newUniqueCode Unique int for each MockModel object for testing.
  */
  public MockModel(StringBuilder newLog, int newUniqueCode) {
    this.log = newLog;
    this.uniqueCode = newUniqueCode;
  }

  /**
   * Method to test the constructor load image command.
   * @param imageName Image name to get from memory.
   * @return An image gotten from memory.
   */
  @Override
  public Image getImage(String imageName) {
    log.append("getting image: imageName = ");
    log.append(imageName);
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
    return null;
  }

  /**
  * Method to test the controller to loadPPMImage command.
  * @param imagePath Path to the image to load into the model.
  * @param newImageName Name to assign to the image loaded into the model.
  */
  @Override
  public void loadImage(String imagePath, String newImageName) {
    log.append("loadPPMImage: imagePath = ");
    log.append(imagePath);
    log.append(" newImageName = ");
    log.append(newImageName);
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
  }

  /**
  * Method to test the controller to savePPMImage command.
  * @param imagePath Path to save the image stored in the model.
  * @param imageName IME.model.Image to save to the imagePath.
  */
  @Override
  public void saveImage(String imagePath, String imageName) {
    log.append("savePPMImage: imagePath = ");
    log.append(imagePath);
    log.append(" imageName = ");
    log.append(imageName);
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
  }

  /**
  * Method to test the controller to getRedComponent command.
  * @param currentImageName String that is the name of the current image.
  * @param newImageName String that is the new name of the new image from the redComponent of the
  *                   currentImage.
  */
  @Override
  public void getRedComponent(String currentImageName, String newImageName) {
    log.append("getRedScaleImage: currentImageName = ");
    log.append(currentImageName);
    log.append(" newImageName = ");
    log.append(newImageName);
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
  }

  /**
  * Method to test the controller to getGreenComponent command.
  * @param currentImageName String that is the name of the current image.
  * @param newImageName String that is the new name of the new image from the greenComponent of the
  *                     currentImage.
  */
  @Override
  public void getGreenComponent(String currentImageName, String newImageName) {
    log.append("getGreenScaleImage: currentImageName = ");
    log.append(currentImageName);
    log.append(" newImageName = ");
    log.append(newImageName);
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
  }

  /**
  * Method to test the controller to getBlueComponent command.
  * @param currentImageName String that is the name of the current image.
  * @param newImageName String that is the new name of the new image from the blueComponent of the
  *                     currentImage.
  */
  @Override
  public void getBlueComponent(String currentImageName, String newImageName) {
    log.append("getBlueScaleImage: currentImageName = ");
    log.append(currentImageName);
    log.append(" newImageName = ");
    log.append(newImageName);
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
  }

  /**
  * Method to test the controller to flipHorizontal command.
  * @param currentImageName String that is the name of the current image.
  * @param newImageName String that is the new name of the new image from the flipHorizontal
  *                     transformation of the currentImage.
  */
  @Override
  public void flipHorizontal(String currentImageName, String newImageName) {
    log.append("flipHorizontal: currentImageName = ");
    log.append(currentImageName);
    log.append(" newImageName = ");
    log.append(newImageName);
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
  }

  /**
  * Method to test the controller to flipVertical command.
  * @param currentImageName String that is the name of the current image.
  * @param newImageName String that is the new name of the new image from the flipVertical
  *                     transformation of the currentImage.
  */
  @Override
  public void flipVertical(String currentImageName, String newImageName) {
    log.append("flipVertical: currentImageName = ");
    log.append(currentImageName);
    log.append(" newImageName = ");
    log.append(newImageName);
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
  }

  /**
  * Method to test the controller to getValueImage command.
  * @param currentImageName String that is the name of the current image.
  * @param newImageName String that is the new name of the new image from the getValueImage
  *                     transformation of the currentImage.
  */
  @Override
  public void getValueImage(String currentImageName, String newImageName) {
    log.append("getValueImage: currentImageName = ");
    log.append(currentImageName);
    log.append(" newImageName = ");
    log.append(newImageName);
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
  }

  /**
  * Method to test the controller to the getIntensityImage command.
  * @param currentImageName String that is the name of the current image.
  * @param newImageName String that is the new name of the new image from the getIntensityImage
  *                     transformation of the currentImage.
  */
  @Override
  public void getIntensityImage(String currentImageName, String newImageName) {
    log.append("getIntensityImage: currentImageName = ");
    log.append(currentImageName);
    log.append(" newImageName = ");
    log.append(newImageName);
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
  }

  /**
  * Method to test the controller to the getLumaImage command.
  * @param currentImageName String that is the name of the current image.
  * @param newImageName String that is the new name of the new image from the getLumaImage
  *                     transformation of the currentImage.
  */
  @Override
  public void getLumaImage(String currentImageName, String newImageName) {
    log.append("getLumaImage: currentImageName = ");
    log.append(currentImageName);
    log.append(" newImageName = ");
    log.append(newImageName);
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
  }

  /**
  * Method to test the controller to the brighten command.
  * @param currentImageName String that is the name of the current image.
  * @param newImageName String that is the new name of the new image from the brighten
  *                     transformation of the currentImage.
  * @param scale The scale to be applied to the currentImage.
  *              Positive for brighten, negative for darken.
  */
  @Override
  public void brighten(String currentImageName, String newImageName, int scale) {
    log.append("brighten: currentImageName = ");
    log.append(currentImageName);
    log.append(" newImageName = ");
    log.append(newImageName);
    log.append(" scale = ");
    log.append(scale);
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
  }

  /**
  * Method to test the controller to rgbSplit command.
  * @param currentImageName String that is the name of the current image.
  * @param redImageName String that is the new name of the redComponent of the currentImage.
  * @param greenImageName String that is the new name of the greenComponent of the currentImage.
  * @param blueImageName String that is the new name of the blueComponent of the currentImage
  */
  @Override
  public void rgbSplit(String currentImageName, String redImageName,
      String greenImageName, String blueImageName) {
    log.append("rgbSplit: currentImageName = ");
    log.append(currentImageName);
    log.append(" redImageName = ");
    log.append(redImageName);
    log.append(" greenImageName = ");
    log.append(greenImageName);
    log.append(" blueImageName = ");
    log.append(blueImageName);
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
  }

  /**
  * Method to test the controller to rgbCombine command.
  * @param newImageName String that is the new name of the combined image from the rgb components.
  * @param rImageName String that is the name of the red component image.
  * @param gImageName String that is the name of the green component image.
  * @param bImageName String that is the name of the blue component image.
  */
  @Override
  public void rgbCombine(String newImageName, String rImageName,
      String gImageName, String bImageName) {
    log.append("rgbCombine: newImageName = ");
    log.append(newImageName);
    log.append(" redImageName = ");
    log.append(rImageName);
    log.append(" greenImageName = ");
    log.append(gImageName);
    log.append(" blueImageName = ");
    log.append(bImageName);
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
  }

  @Override
  public void rgbCombine(String newImageName, Image redImage, Image greenImage, Image blueImage) {
    log.append("rgbCombine: newImageName = ");
    log.append(newImageName);
    log.append(this.uniqueCode);
    log.append("\n");
  }

  /**
  * Method to test the controller to greyscale command.
  * @param greyScaleComponent Method of greyscale transformation.
  * @param imageName String that is the image name to do the greyscale transformation on.
  * @param newImageName String that is the new name of the new greyscale image.
  */
  @Override
  public void greyscale(String greyScaleComponent, String imageName, String newImageName) {
    log.append("greyscale: greyScaleComponent = ");
    log.append(greyScaleComponent);
    log.append(" imageName = ");
    log.append(imageName);
    log.append(" newImageName = ");
    log.append(newImageName);
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
  }

  /**
   * Method to test the controller dither command.
   * @param imageName Image name to get from memory.
   * @param newImageName Name to write the new image to.
   */
  @Override
  public void dither(String imageName, String newImageName) {
    log.append("dither: ");
    log.append("imageName = ");
    log.append(imageName);
    log.append(" newImageName = ");
    log.append(newImageName);
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
  }

  /**
   * Method to test the controller sepia command.
   * @param imageName Image name to get from memory.
   * @param newImageName Name to write the new image to.
   */
  @Override
  public void sepia(String imageName, String newImageName) {
    log.append("sepia: ");
    log.append("imageName = ");
    log.append(imageName);
    log.append(" newImageName = ");
    log.append(newImageName);
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
  }

  /**
   * Method to test the controller sharpen command.
   * @param imageName Image name to get from memory.
   * @param newImageName Name to write the new image to.
   */
  @Override
  public void sharpen(String imageName, String newImageName) {
    log.append("sharpen: ");
    log.append("imageName = ");
    log.append(imageName);
    log.append(" newImageName = ");
    log.append(newImageName);
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");

  }

  /**
   * Method to test the controller blur command.
   * @param imageName Image name to get from memory.
   * @param newImageName Name to write the new iamge to.
   */
  @Override
  public void blur(String imageName, String newImageName) {
    log.append("blur: ");
    log.append("imageName = ");
    log.append(imageName);
    log.append(" newImageName = ");
    log.append(newImageName);
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");

  }

  /**
   * Method to test the controller greyscale from matrix command.
   * @param imageName Image to load from memory.
   * @param newImageName Name to save new image as.
   */
  @Override
  public void matrixGreyscale(String imageName, String newImageName) {
    log.append("matrixGreyscale: ");
    log.append("imageName = ");
    log.append(imageName);
    log.append(" newImageName = ");
    log.append(newImageName);
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
  }
}
