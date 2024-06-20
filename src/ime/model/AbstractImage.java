package ime.model;

import java.util.Arrays;

/**
 * An abstract class for Image.
 */
public abstract class AbstractImage implements Image {

  protected final int height;

  protected final int width;
  protected final int[][] redComponent;
  protected final int[][] greenComponent;
  protected final int[][] blueComponent;

  /**
   * Constructor for AbstractImage.
   * @param width Int width of the image.
   * @param height Int height of the image.
   * @param red Integer matrix of images red component.
   * @param blue Integer matrix of images blue component.
   * @param green Integer matrix of images green component.
   */
  public AbstractImage(int width, int height, int[][] red,
                       int[][] blue, int[][] green) {
    this.height = height;
    this.width = width;
    this.redComponent = red;
    this.greenComponent = green;
    this.blueComponent = blue;
  }


  @Override
  public int[][] getRedComponent() {
    return redComponent;
  }

  @Override
  public int[][] getGreenComponent() {
    return greenComponent;
  }


  @Override
  public int[][] getBlueComponent() {
    return blueComponent;
  }


  @Override
  public int getHeight() {
    return height;
  }


  @Override
  public int getWidth() {
    return width;
  }


  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof ImageModel.IMEImage)) {
      return false;
    }
    ImageModel.IMEImage c = (ImageModel.IMEImage) o;

    return c.getWidth() == this.getWidth() && c.getHeight() == this.getHeight()
        && Arrays.deepEquals(c.blueComponent, blueComponent)
        && Arrays.deepEquals(c.greenComponent, greenComponent)
        && Arrays.deepEquals(c.redComponent, redComponent);

  }


  @Override
  public int hashCode() {
    return 67499 * this.getWidth() * this.getHeight();
  }
}
