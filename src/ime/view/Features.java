package ime.view;

/**
 * Interface of features for the view.
 */
public interface Features {

  void blur();

  void brighten(int scale);

  void dither();

  void greyscale();

  void horizontalFlip();

  void verticalFlip();

  void loadImage(String path);

  void rgbCombine(String redPath, String greenPath, String bluePath);

  void rgbSplit();

  void save(String path);

  void sepia();

  void sharpen();

  void luma();

  void value();

  void intensity();

  void exit();


}
