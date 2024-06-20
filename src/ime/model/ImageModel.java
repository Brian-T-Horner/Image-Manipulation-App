package ime.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import ime.ImageUtil;

/**
 * Implementation of Model that provides a set of operations over a set of Images.
 */
public class ImageModel implements Model {

  private final Map<String, Image> images = new HashMap<>();

  /**
   * Constructor for ImageModel. No parameters as only one default field.
   */
  public ImageModel() {
    //only one default field
  }

  @Override
  public Image getImage(String imageName) throws NoSuchElementException {
    Image i = images.get(imageName);
    if (i == null) {
      throw new NoSuchElementException("IME.model.Image with name \"" + imageName
          + "\" not in memory.");
    }
    return i;
  }

  @Override
  public void loadImage(String imagePath, String newImageName)
      throws FileNotFoundException, IllegalArgumentException {
    if (!(imagePath.endsWith(".ppm") || imagePath.endsWith(".bmp")
        || imagePath.endsWith(".png") || imagePath.endsWith(".jpg"))) {
      throw new IllegalArgumentException("File must be a .bmp, .jpg, .png, or .ppm file.");
    }
    Image i = ImageUtil.loadImage(imagePath);
    if (i == null) {
      throw new FileNotFoundException("File \"" + imagePath + "\" not found!");
    }
    images.put(newImageName, i);
  }


  @Override
  public void saveImage(String imagePath, String imageName)
          throws IOException, NoSuchElementException {
    Image i = images.get(imageName);
    if (i == null) {
      throw new NoSuchElementException("IME.model.Image with name \"" + imageName
          + "\" not in memory.");
    }
    if (!(imagePath.endsWith(".ppm") || imagePath.endsWith(".bmp")
        || imagePath.endsWith(".png") || imagePath.endsWith(".jpg"))) {
      throw new IOException("File must be a .bmp, .jpg, .png, or .ppm file.");
    }
    ImageUtil.saveImage(i, imagePath);
  }


  @Override
  public void getRedComponent(String currentImageName, String newImageName)
          throws NoSuchElementException {
    Image i = images.get(currentImageName);
    if (i != null) {
      int[][] r = i.getRedComponent();
      images.put(newImageName, new IMEImage(i.getWidth(), i.getHeight(), r, r, r));
    } else {
      throw new NoSuchElementException("IME.model.Image with name " + currentImageName
          + " not in memory.");
    }
  }


  @Override
  public void getGreenComponent(String currentImageName, String newImageName)
          throws NoSuchElementException {
    Image i = images.get(currentImageName);
    if (i != null) {
      int[][] r = i.getGreenComponent();
      images.put(newImageName, new IMEImage(i.getWidth(), i.getHeight(), r, r, r));
    } else {
      throw new NoSuchElementException("IME.model.Image with name " + currentImageName
          + " not in memory.");
    }
  }


  @Override
  public void getBlueComponent(String currentImageName, String newImageName)
          throws NoSuchElementException {
    Image i = images.get(currentImageName);
    if (i != null) {
      int[][] r = i.getBlueComponent();
      images.put(newImageName, new IMEImage(i.getWidth(), i.getHeight(), r, r, r));
    } else {
      throw new NoSuchElementException("IME.model.Image with name " + currentImageName
          + " not in memory.");
    }
  }


  @Override
  public void flipHorizontal(String currentImageName, String newImageName)
          throws NoSuchElementException {
    Image i = images.get(currentImageName);
    if (i == null) {
      throw new NoSuchElementException("IME.model.Image with name " + currentImageName
          + " not in memory.");
    }
    int[][] red = flipArrayHorizontal(i, i.getRedComponent());
    int[][] green = flipArrayHorizontal(i, i.getGreenComponent());
    int[][] blue = flipArrayHorizontal(i, i.getBlueComponent());
    Image horizontal = new IMEImage(i.getWidth(), i.getHeight(), red, blue, green);

    images.put(newImageName, horizontal);
  }


  @Override
  public void flipVertical(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    if (i == null) {
      throw new NoSuchElementException("IME.model.Image with name " + currentImageName
          + " not in memory.");
    }
    int[][] red = flipArrayVertical(i, i.getRedComponent());
    int[][] green = flipArrayVertical(i, i.getGreenComponent());
    int[][] blue = flipArrayVertical(i, i.getBlueComponent());
    Image vertical = new IMEImage(i.getWidth(), i.getHeight(), red, blue, green);

    images.put(newImageName, vertical);
  }


  @Override
  public void getValueImage(String currentImageName, String newImageName)
          throws NoSuchElementException {
    Image img = images.get(currentImageName);
    if (img == null) {
      throw new NoSuchElementException("IME.model.Image with name " + currentImageName
          + " not in memory.");
    }
    int width = img.getWidth();
    int height = img.getHeight();
    int[][] value = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int redValue = img.getRedComponent()[i][j];
        int greenValue = img.getGreenComponent()[i][j];
        int blueValue = img.getBlueComponent()[i][j];
        int max = Math.max(redValue, greenValue);
        max = Math.max(max, blueValue);
        value[i][j] = max;
      }
    }
    Image val = new IMEImage(width, height, value, value, value);

    images.put(newImageName, val);
  }


  @Override
  public void getIntensityImage(String currentImageName, String newImageName)
          throws NoSuchElementException {
    Image img = images.get(currentImageName);
    if (img == null) {
      throw new NoSuchElementException("IME.model.Image with name " + currentImageName
          + " not in memory.");
    }
    int width = img.getWidth();
    int height = img.getHeight();
    int[][] intensity = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int redValue = img.getRedComponent()[i][j];
        int greenValue = img.getGreenComponent()[i][j];
        int blueValue = img.getBlueComponent()[i][j];
        int intensityValue = (int) Math.ceil((double) (redValue + greenValue + blueValue) / 3);
        if (intensityValue > 255) {
          intensityValue = 255;
        } else if (intensityValue < 0) {
          intensityValue = 0;
        }
        intensity[i][j] = intensityValue;
      }
    }
    Image i = new IMEImage(width, height, intensity, intensity, intensity);

    images.put(newImageName, i);
  }


  @Override
  public void getLumaImage(String currentImageName, String newImageName)
          throws NoSuchElementException {
    Image img = images.get(currentImageName);
    if (img == null) {
      throw new NoSuchElementException("IME.model.Image with name " + currentImageName
          + " not in memory.");
    }
    int width = img.getWidth();
    int height = img.getHeight();
    int[][] luma = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int redValue = img.getRedComponent()[i][j];
        int greenValue = img.getGreenComponent()[i][j];
        int blueValue = img.getBlueComponent()[i][j];
        int lumaValue = (int) Math.ceil(0.2126 * redValue + 0.7152
                * greenValue + 0.0722 * blueValue);
        if (lumaValue > 255) {
          lumaValue = 255;
        } else if (lumaValue < 0) {
          lumaValue = 0;
        }
        luma[i][j] = lumaValue;
      }
    }
    Image lumaImg = new IMEImage(width, height, luma, luma, luma);
    images.put(newImageName, lumaImg);
  }


  @Override
  public void brighten(String currentImageName, String newImageName, int scale)
          throws NoSuchElementException {
    Image img = images.get(currentImageName);
    if (img == null) {
      throw new NoSuchElementException("IME.model.Image with name \"" + currentImageName
              + "\" not in memory.");
    }
    int width = img.getWidth();
    int height = img.getHeight();
    int[][] red = brightenArray(img, img.getRedComponent(), scale);
    int[][] green = brightenArray(img, img.getGreenComponent(), scale);
    int[][] blue = brightenArray(img, img.getBlueComponent(), scale);
    Image brightened = new IMEImage(width, height, red, blue, green);

    images.put(newImageName, brightened);

  }


  @Override
  public void rgbSplit(String currentImageName, String redImageName, String greenImageName,
                       String blueImageName) throws NoSuchElementException {
    Image i = images.get(currentImageName);
    if (i == null) {
      throw new NoSuchElementException("IME.model.Image with name \"" + currentImageName
              + "\" not in memory.");
    }
    int[][] r = i.getRedComponent();
    int[][] g = i.getGreenComponent();
    int[][] b = i.getBlueComponent();
    int height = i.getHeight();
    int width = i.getWidth();
    Image red = new IMEImage(width, height, r, r, r);
    images.put(redImageName, red);
    Image green = new IMEImage(width, height, g, g, g);
    images.put(greenImageName, green);
    Image blue = new IMEImage(width, height, b, b, b);
    images.put(blueImageName, blue);
  }


  @Override
  public void rgbCombine(String newImageName, String rImageName, String gImageName,
                         String bImageName) throws NoSuchElementException {
    Image redImage = images.get(rImageName);
    Image greenImage = images.get(gImageName);
    Image blueImage = images.get(bImageName);
    String exceptionOut = "";
    if (redImage == null) {
      exceptionOut += "IME.model.Image with name \"" + rImageName + "\" not in memory.\t";
    }
    if (blueImage == null) {
      exceptionOut += "IME.model.Image with name \"" + bImageName + "\" not in memory.\t";
    }
    if (greenImage == null) {
      exceptionOut += "IME.model.Image with name \"" + gImageName + "\" not in memory.\t";
    }
    if (!exceptionOut.isEmpty()) {
      throw new NoSuchElementException(exceptionOut);
    }

    if (compareDimensions(redImage, greenImage) && compareDimensions(greenImage, blueImage)
            && compareDimensions(blueImage, redImage)) {
      images.put(newImageName, new IMEImage(redImage.getWidth(), redImage.getHeight(),
              redImage.getRedComponent(), blueImage.getBlueComponent(),
              greenImage.getGreenComponent()));
    }
  }

  @Override
  public void rgbCombine(String newImageName, Image redImage, Image greenImage, Image blueImage) {

    if (compareDimensions(redImage, greenImage) && compareDimensions(greenImage, blueImage)
            && compareDimensions(blueImage, redImage)) {
      images.put(newImageName, new IMEImage(redImage.getWidth(), redImage.getHeight(),
              redImage.getRedComponent(), blueImage.getBlueComponent(),
              greenImage.getGreenComponent()));
    }
  }


  @Override
  public void greyscale(String greyScaleComponent, String imageName, String newImageName)
          throws IllegalArgumentException {
    switch (greyScaleComponent) {
      case "value-component":
        getValueImage(imageName, newImageName);
        break;
      case "luma-component":
        getLumaImage(imageName, newImageName);
        break;
      case "intensity-component":
        getIntensityImage(imageName, newImageName);
        break;
      case "red-component":
        getRedComponent(imageName, newImageName);
        break;
      case "green-component":
        getGreenComponent(imageName, newImageName);
        break;
      case "blue-component":
        getBlueComponent(imageName, newImageName);
        break;
      default:
        throw new IllegalArgumentException("Argument \"" + greyScaleComponent
                + "\" invalid for \"greyscale\"");
    }
  }

  @Override
  public void dither(String imageName, String newImageName) {
    Image img = images.get(imageName);
    System.out.println("p2");
    if (img == null) {
      throw new NoSuchElementException("IME.model.Image with name \"" + imageName
              + "\" not in memory.");
    }

    int height = img.getHeight();
    int width = img.getWidth();
    int[][] redComp = new int [height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        redComp[i][j] = img.getRedComponent()[i][j];
      }
    }
    int[][] newRed = new int[height][width];
    int[][] newBlue = new int[height][width];
    int[][] newGreen = new int[height][width];
    int oldColor;
    int newColor;
    double error;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        oldColor = redComp[i][j];
        if (oldColor >= 128) {
          newColor = 255;
        } else {
          newColor = 0;
        }
        // Calculating error
        error = oldColor - newColor;

        // Setting pixel to new color
        newRed[i][j] = newColor;
        newBlue[i][j] = newColor;
        newGreen[i][j] = newColor;

        // Setting pixels besides it by the error
        if (j + 1 < width) {
          redComp[i][j + 1] = redComp[i][j + 1] + (int) Math.ceil((7.0 / 16.0) * error);
        }
        if (i + 1 < height && j - 1 >= 0) {
          redComp[i + 1][j - 1] = redComp[i + 1][j - 1] + (int) Math.ceil((3.0 / 16.0) * error);
        }

        if (i + 1 < height) {
          redComp[i + 1][j] = redComp[i + 1][j] + (int) Math.ceil((5.0 / 16.0) * error);
        }

        if (i + 1 < height && j + 1 < width) {
          redComp[i + 1][j + 1] = redComp[i + 1][j + 1] + (int) Math.ceil((1.0 / 16.0) * error);
        }
      }
    }
    Image dither = new IMEImage(width, height, newRed, newBlue, newGreen);
    images.put(newImageName, dither);
  }

  @Override
  public void sepia(String imageName, String newImageName) {
    Image img = images.get(imageName);
    if (img == null) {
      throw new NoSuchElementException("IME.model.Image with name \"" + imageName
              + "\" not in memory.");
    }

    int[][] red = img.getRedComponent();
    int[][] green = img.getGreenComponent();
    int[][] blue = img.getBlueComponent();
    int height = img.getHeight();
    int width = img.getWidth();
    int[][] r = new int[height][width];
    int[][] g = new int[height][width];
    int[][] b = new int[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int redVal = (int) Math.rint(0.393 * red[i][j]
                + (0.769 * green[i][j])
                + (0.189 * blue[i][j]));
        int greenVal = (int) Math.rint((0.349 * red[i][j])
                + (0.686 * green[i][j])
                + (0.168 * blue[i][j]));
        int blueVal = (int) Math.rint((0.272 * red[i][j])
                + (0.534 * green[i][j])
                + (0.131 * blue[i][j]));
        r[i][j] = redVal;
        g[i][j] = greenVal;
        b[i][j] = blueVal;

        if (r[i][j] > 255) {
          r[i][j] = 255;
        }
        if (g[i][j] > 255) {
          g[i][j] = 255;
        }
        if (b[i][j] > 255) {
          b[i][j] = 255;
        }

        if (b[i][j] < 0) {
          b[i][j] = 0;
        }

        if (g[i][j] < 0) {
          g[i][j] = 0;
        }

        if (r[i][j] < 0) {
          r[i][j] = 0;
        }

      }
    }

    Image sepiaImg = new IMEImage(width, height, r, b, g);
    images.put(newImageName, sepiaImg);
  }

  @Override
  public void sharpen(String imageName, String newImageName) {
    Image img = images.get(imageName);
    if (img == null) {
      throw new NoSuchElementException("IME.model.Image with name \"" + imageName
              + "\" not in memory.");
    }
    int[][] r = img.getRedComponent();
    int[][] g = img.getGreenComponent();
    int[][] b = img.getBlueComponent();
    int[][] newR = new int[r.length][r[0].length];
    int[][] newB = new int[b.length][b[0].length];
    int[][] newG = new int [g.length][g[0].length];
    int height = img.getHeight();
    int width = img.getWidth();
    double[][] sharpenFilter = new double[5][];
    sharpenFilter[0] = new double[]{-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0};
    sharpenFilter[1] = new double[]{-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0};
    sharpenFilter[2] = new double[]{-1.0 / 8.0, 1.0 / 4.0, 1.0, 1.0 / 4.0, -1.0 / 8.0};
    sharpenFilter[3] = new double[]{-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0};
    sharpenFilter[4] = new double[]{-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0};


    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        double redSum = 0;
        double blueSum = 0;
        double greenSum = 0;


        // -------- First Row Operations ---------------='
        if (i - 2 >= 0) {

          // [0][0] - row 0 column 0 -  (i-2, j-2)
          if (j - 2 >= 0) {
            redSum += Math.ceil((double) r[i - 2][j - 2] * sharpenFilter[0][0]);
            blueSum += Math.ceil((double) b[i - 2][j - 2] * sharpenFilter[0][0]);
            greenSum += Math.ceil((double) g[i - 2][j - 2] * sharpenFilter[0][0]);
          }

          // [0][1] - row 0 column 1    (i-2, j-1)
          if (j - 1 >= 0) {
            redSum += Math.ceil((double) r[i - 2][j - 1] * sharpenFilter[0][1]);
            blueSum += Math.ceil((double) b[i - 2][j - 1] * sharpenFilter[0][1]);
            greenSum += Math.ceil((double) g[i - 2][j - 1] * sharpenFilter[0][1]);
          }

          // [0][2] - row 0 column 2    (i-2, j)
          redSum += Math.ceil((double) r[i - 2][j] * sharpenFilter[0][2]);
          blueSum += Math.ceil((double) b[i - 2][j] * sharpenFilter[0][2]);
          greenSum += Math.ceil((double) g[i - 2][j] * sharpenFilter[0][2]);

          // [0][3] - row 0 column 3    (i-2, j+1)
          if ((j + 1) < width) {
            redSum += Math.ceil((double) r[i - 2][j + 1] * sharpenFilter[0][3]);
            blueSum += Math.ceil((double) b[i - 2][j + 1] * sharpenFilter[0][3]);
            greenSum += Math.ceil((double) g[i - 2][j + 1] * sharpenFilter[0][3]);
          }

          // [0][4] - row 0 column 4    (i-2, j+2)
          if ((j + 2) < width) {
            redSum += Math.ceil((double) r[i - 2][j + 2] * sharpenFilter[0][4]);
            blueSum += Math.ceil((double) b[i - 2][j + 2] * sharpenFilter[0][4]);
            greenSum += Math.ceil((double) g[i - 2][j + 2] * sharpenFilter[0][4]);
          }
        }


        // -----------Second Row Operations ---------------
        if (i - 1 >= 0) {

          // [1][0] - row 1 column 0  (i-1, j-2)
          if (j - 2 >= 0) {
            redSum += Math.ceil((double) r[i - 1][j - 2] * sharpenFilter[1][0]);
            blueSum += Math.ceil((double) b[i - 1][j - 2] * sharpenFilter[1][0]);
            greenSum += Math.ceil((double) g[i - 1][j - 2] * sharpenFilter[1][0]);
          }

          // [1][1] - row 1 column 1  (i-1, j-1)
          if (j - 1 >= 0) {
            redSum += Math.ceil((double) r[i - 1][j - 1] * sharpenFilter[1][1]);
            blueSum += Math.ceil((double) b[i - 1][j - 1] * sharpenFilter[1][1]);
            greenSum += Math.ceil((double) g[i - 1][j - 1] * sharpenFilter[1][1]);
          }

          // [1][2] - row 1 column 2  (i-1, j)
          redSum += Math.ceil((double) r[i - 1][j] * sharpenFilter[1][2]);
          blueSum += Math.ceil((double) b[i - 1][j] * sharpenFilter[1][2]);
          greenSum += Math.ceil((double) g[i - 1][j] * sharpenFilter[1][2]);

          // [1][3] - row 1 column 3  (i-1, j+1)
          if ((j + 1) < width) {
            redSum += Math.ceil((double) r[i - 1][j + 1] * sharpenFilter[1][3]);
            blueSum += Math.ceil((double) b[i - 1][j + 1] * sharpenFilter[1][3]);
            greenSum += Math.ceil((double) g[i - 1][j + 1] * sharpenFilter[1][3]);
          }

          // [1][4] - row 1 column 4  (i-1, j+2)
          if ((j + 2) < width) {
            redSum += Math.ceil((double) r[i - 1][j + 2] * sharpenFilter[1][4]);
            blueSum += Math.ceil((double) b[i - 1][j + 2] * sharpenFilter[1][4]);
            greenSum += Math.ceil((double) g[i - 1][j + 2] * sharpenFilter[1][4]);
          }
        }


        // -------------Pixel Row Operations -------------------
        // [2][0] - row 2 column 0  (i, j-2)
        if (j - 2 >= 0) {
          redSum += Math.ceil((double) r[i][j - 2] * sharpenFilter[2][0]);
          blueSum += Math.ceil((double) b[i][j - 2] * sharpenFilter[2][0]);
          greenSum += Math.ceil((double) g[i][j - 2] * sharpenFilter[2][0]);
        }

        // [2][1] - row 2 column 1  (i, j-1)
        if (j - 1 >= 0) {
          redSum += Math.ceil((double) r[i][j - 1] * sharpenFilter[2][1]);
          blueSum += Math.ceil((double) b[i][j - 1] * sharpenFilter[2][1]);
          greenSum += Math.ceil((double) g[i][j - 1] * sharpenFilter[2][1]);
        }

        // [2][2] - row 2 column 2  (i, j)
        redSum += Math.ceil((double) r[i][j] * sharpenFilter[2][2]);
        blueSum += Math.ceil((double) b[i][j] * sharpenFilter[2][2]);
        greenSum += Math.ceil((double) g[i][j] * sharpenFilter[2][2]);

        // [2][3] - row 2 column 3  (i, j+1)
        if ((j + 1) < width) {
          redSum += Math.ceil((double) r[i][j + 1] * sharpenFilter[2][3]);
          blueSum += Math.ceil((double) b[i][j + 1] * sharpenFilter[2][3]);
          greenSum += Math.ceil((double) g[i][j + 1] * sharpenFilter[2][3]);
        }

        // [2][4] - row 2 column 4  (i, j+2)
        if ((j + 2) < width) {
          redSum += Math.ceil((double) r[i][j + 2] * sharpenFilter[2][4]);
          blueSum += Math.ceil((double) b[i][j + 2] * sharpenFilter[2][4]);
          greenSum += Math.ceil((double) g[i][j + 2] * sharpenFilter[2][4]);
        }

        // -----------Fourth Row Operations ---------------
        if ((i + 1) < height) {

          // [3][0] - row 4 column 0  (i+1, j-2)
          if (j - 2 >= 0) {
            redSum += Math.ceil((double) r[i + 1][j - 2] * sharpenFilter[3][0]);
            blueSum += Math.ceil((double) b[i + 1][j - 2] * sharpenFilter[3][0]);
            greenSum += Math.ceil((double) g[i + 1][j - 2] * sharpenFilter[3][0]);
          }

          // [3][1] - row 4 column 1  (i+1, j-1)
          if (j - 1 >= 0) {
            redSum += Math.ceil((double) r[i + 1][j - 1] * sharpenFilter[3][1]);
            blueSum += Math.ceil((double) b[i + 1][j - 1] * sharpenFilter[3][1]);
            greenSum += Math.ceil((double) g[i + 1][j - 1] * sharpenFilter[3][1]);
          }

          // [3][2] - row 4 column 2  (i+1, j)
          redSum += Math.ceil((double) r[i + 1][j] * sharpenFilter[3][2]);
          blueSum += Math.ceil((double) b[i + 1][j] * sharpenFilter[3][2]);
          greenSum += Math.ceil((double) g[i + 1][j] * sharpenFilter[3][2]);

          // [3][3] - row 4 column 3  (i+1, j+1)
          if ((j + 1) < width) {
            redSum += Math.ceil((double) r[i + 1][j + 1] * sharpenFilter[3][3]);
            blueSum += Math.ceil((double) b[i + 1][j + 1] * sharpenFilter[3][3]);
            greenSum += Math.ceil((double) g[i + 1][j + 1] * sharpenFilter[3][3]);
          }

          // [3][4] - row 4 column 4  (i+1, j+2)
          if ((j + 2) < width) {
            redSum += Math.ceil((double) r[i + 1][j + 2] * sharpenFilter[3][4]);
            blueSum += Math.ceil((double) b[i + 1][j + 2] * sharpenFilter[3][4]);
            greenSum += Math.ceil((double) g[i + 1][j + 2] * sharpenFilter[3][4]);
          }
        }
        // -----------Fifth Row Operations ---------------
        if ((i + 2) < height) {

          // [4][0] - row 5 column 0  (i+2, j-2)
          if (j - 2 >= 0) {
            redSum += Math.ceil((double) r[i + 2][j - 2] * sharpenFilter[4][0]);
            blueSum += Math.ceil((double) b[i + 2][j - 2] * sharpenFilter[4][0]);
            greenSum += Math.ceil((double) g[i + 2][j - 2] * sharpenFilter[4][0]);
          }

          // [4][1] - row 5 column 1  (i+2, j-1)
          if (j - 1 >= 0) {
            redSum += Math.ceil((double) r[i + 2][j - 1] * sharpenFilter[4][1]);
            blueSum += Math.ceil((double) b[i + 2][j - 1] * sharpenFilter[4][1]);
            greenSum += Math.ceil((double) g[i + 2][j - 1] * sharpenFilter[4][1]);
          }

          // [4][2] - row 5 column 2  (i+2, j)
          redSum += Math.ceil((double) r[i + 2][j] * sharpenFilter[4][2]);
          blueSum += Math.ceil((double) b[i + 2][j] * sharpenFilter[4][2]);
          greenSum += Math.ceil((double) g[i + 2][j] * sharpenFilter[4][2]);

          // [4][3] - row 5 column 3  (i+2, j+1)
          if ((j + 1) < width) {
            redSum += Math.ceil((double) r[i + 2][j + 1] * sharpenFilter[4][3]);
            blueSum += Math.ceil((double) b[i + 2][j + 1] * sharpenFilter[4][3]);
            greenSum += Math.ceil((double) g[i + 2][j + 1] * sharpenFilter[4][3]);
          }

          // [4][4] - row 5 column 4  (i+2, j+2)
          if ((j + 2) < width) {
            redSum += Math.ceil((double) r[i + 2][j + 2] * sharpenFilter[4][4]);
            blueSum += Math.ceil((double) b[i + 2][j + 2] * sharpenFilter[4][4]);
            greenSum += Math.ceil((double) g[i + 2][j + 2] * sharpenFilter[4][4]);
          }
        }

        //-------------------Check floor and ceiling------------------

        // red
        if (redSum < 0) {
          redSum = 0;
        }

        if (redSum > 255) {
          redSum = 255;
        }
        // blue
        if (blueSum < 0) {
          blueSum = 0;
        }

        if (blueSum > 255) {
          blueSum = 255;
        }
        // green
        if (greenSum < 0) {
          greenSum = 0;
        }

        if (greenSum > 255) {
          greenSum = 255;
        }

        //-------------------Store the red blue and green values ---------------

        // red
        newR[i][j] = (int) Math.rint(redSum);
        //blue
        newB[i][j] = (int) Math.rint(blueSum);
        // green
        newG[i][j] = (int) Math.rint(greenSum);
      }
    }
    Image sharpenedImage = new IMEImage(width, height,newR, newB, newG);
    images.put(newImageName, sharpenedImage);

  }

  @Override
  public void blur(String imageName, String newImageName) {
    Image img = images.get(imageName);
    if (img == null) {
      throw new NoSuchElementException("IME.model.Image with name \"" + imageName
              + "\" not in memory.");
    }
    int[][] r = img.getRedComponent();
    int[][] g = img.getGreenComponent();
    int[][] b = img.getBlueComponent();
    int[][] newR = new int[img.getRedComponent().length][img.getRedComponent()[0].length];
    int[][] newB = new int[img.getBlueComponent().length][img.getBlueComponent()[0].length];
    int[][] newG = new int[img.getGreenComponent().length][img.getGreenComponent()[0].length];
    int height = img.getHeight();
    int width = img.getWidth();
    double[][] blurFilter = new double[3][];
    blurFilter[0] = new double[]{1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0};
    blurFilter[1] = new double[]{1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0};
    blurFilter[2] = new double[]{1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0};

    for (int i = 0; i < height; i++) {  //[height][width] [i][j]
      for (int j = 0; j < width; j++) {
        double redSum = 0;
        double blueSum = 0;
        double greenSum = 0;
        // Get and operate on top left [i-1][j-1]
        if ((i - 1) >= 0 && (j - 1) >= 0) {
          try {
            // red
            redSum += (r[i - 1][j - 1] * blurFilter[0][0]);
            // blue
            blueSum += (b[i - 1][j - 1] * blurFilter[0][0]);
            // green
            greenSum += (g[i - 1][j - 1] * blurFilter[0][0]);
          } catch (Exception e) {
            throw new IndexOutOfBoundsException("Index out of bounds at top left");
          }
        }
        // Get and operate on above [i-1][j]
        if ((i - 1) >= 0) {
          try {
            // red
            redSum += (r[i - 1][j] * blurFilter[0][1]);
            // blue
            blueSum += (b[i - 1][j] * blurFilter[0][1]);
            // green
            greenSum += (g[i - 1][j] * blurFilter[0][1]);
          } catch (Exception e) {
            throw new IndexOutOfBoundsException("Index out of bounds at above.");
          }
        }
        // Get and operate on top right [i-1][j+1]
        if ((i - 1) >= 0 && (j + 1) < width) {
          try {
            // red
            redSum += (r[i - 1][j + 1] * blurFilter[0][2]);
            // blue
            blueSum += (b[i - 1][j + 1] * blurFilter[0][2]);
            // green
            greenSum += (g[i - 1][j + 1] * blurFilter[0][2]);
          } catch (Exception e) {
            throw new IndexOutOfBoundsException("Index out of bounds top right");
          }
        }
        // Get and operate on left [i][j-1]
        if ((j - 1) >= 0) {
          try {
            // red
            redSum += (r[i][j - 1] * blurFilter[1][0]);
            // blue
            blueSum += (b[i][j - 1] * blurFilter[1][0]);
            // green
            greenSum += (g[i][j - 1] * blurFilter[1][0]);
          } catch (Exception e) {
            throw new IndexOutOfBoundsException("Index out of bounds at left");
          }
        }
        // Get and operate on center [i][j]



        // red
        redSum += (r[i][j] * blurFilter[1][1]);
        // blue
        blueSum += (b[i][j] * blurFilter[1][1]);
        // green
        greenSum += (g[i][j] * blurFilter[1][1]);


        // Get and operate on right [i][j+1]
        if (j + 1 < width) {
          try {
            // red
            redSum += (r[i][j + 1] * blurFilter[1][2]);
            // blue
            blueSum += (b[i][j + 1] * blurFilter[1][2]);
            // green
            greenSum += (g[i][j + 1] * blurFilter[1][2]);
          } catch (Exception e) {
            throw new IndexOutOfBoundsException("Index out of bounds at right i = "
                + i + " j = " + j + "width " + width + "height " + height);
          }
        }
        // Get and operate on bottom left [i+1][j-1]  [2][0]
        if ((i + 1) < height && (j - 1) >= 0) {
          try {
            // red
            redSum += (r[i + 1][j - 1] * blurFilter[2][0]);
            // blue
            blueSum += (b[i + 1][j - 1] * blurFilter[2][0]);
            // green
            greenSum += (g[i + 1][j - 1] * blurFilter[2][0]);
          } catch (Exception e) {
            throw new IndexOutOfBoundsException("Index out of bounds bottom left");
          }
        }
        // Get and operate on below [i+1][j] [2][1]
        if ((i + 1) < height) {
          try {
            // red
            redSum += (r[i + 1][j] * blurFilter[2][1]);
            // blue
            blueSum += (b[i + 1][j] * blurFilter[2][1]);
            // green
            greenSum += (g[i + 1][j] * blurFilter[2][1]);
          } catch (Exception e) {
            throw new IndexOutOfBoundsException("Index out of bounds below");
          }
        }
        // Get and operate on bottom right [i+1][j+1] [2][2]
        if ((i + 1) < height && (j + 1) < width) {
          try {
            // red
            redSum += (r[i + 1][j + 1] * blurFilter[2][2]);
            // blue
            blueSum += (b[i + 1][j + 1] * blurFilter[2][2]);
            // green
            greenSum += (g[i + 1][j + 1] * blurFilter[2][2]);
          } catch (Exception e) {
            throw new IndexOutOfBoundsException("Index out of bounds bottom right");
          }
        }
        // Check for floor and ceiling
        // red
        if (redSum < 0) {
          redSum = 0;
        }

        if (redSum > 255) {
          redSum = 255;
        }
        // blue
        if (blueSum < 0) {
          blueSum = 0;
        }

        if (blueSum > 255) {
          blueSum = 255;
        }
        // green
        if (greenSum < 0) {
          greenSum = 0;
        }

        if (greenSum > 255) {
          greenSum = 255;
        }

        // Store [i][j]
        //red
        newR[i][j] = (int) Math.round(redSum);
        //blue
        newB[i][j] = (int) Math.round(blueSum);
        // green
        newG[i][j] = (int) Math.round(greenSum);


      }
    }
    Image blurredImage = new IMEImage(width, height, newR,
            newB, newG);
    images.put(newImageName, blurredImage);

  }


  @Override
  public void matrixGreyscale(String imageName, String newImageName) {
    Image img = images.get(imageName);
    if (img == null) {
      throw new NoSuchElementException("IME.model.Image with name \"" + imageName
              + "\" not in memory.");
    }
    getLumaImage(imageName, newImageName);
  }

  private boolean compareDimensions(Image firstImage, Image secondImage) {
    return (firstImage.getHeight() == secondImage.getHeight()
            && firstImage.getWidth() == secondImage.getWidth());
  }

  private int[][] flipArrayHorizontal(Image img, int[][] arr) {
    int temp;
    int height = img.getHeight();
    int width = img.getWidth();
    int[][] flippedH = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width / 2; j++) {
        temp = arr[i][width - j - 1];
        flippedH[i][width - j - 1] = arr[i][j];
        flippedH[i][j] = temp;
      }
    }

    return flippedH;
  }

  private int[][] flipArrayVertical(Image img, int[][] arr) {
    int temp;
    int height = img.getHeight();
    int width = img.getWidth();
    int[][] flippedV = new int[height][width];
    for (int i = 0; i < height / 2; i++) {
      for (int j = 0; j < width; j++) {
        temp = arr[i][j];
        flippedV[i][j] = arr[height - 1 - i][j];
        flippedV[height - 1 - i][j] = temp;
      }
    }
    return flippedV;
  }


  private int[][] brightenArray(Image img, int[][] arr, int scale) {
    int height = img.getHeight();
    int width = img.getWidth();
    int[][] brightened = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int val = arr[i][j] + scale;
        if (val > 255) {
          val = 255;
        } else if (val < 0) {
          val = 0;
        }
        brightened[i][j] = val;
      }
    }
    return brightened;
  }


  /**
   * A class to represent an image in PPM Format.
   */
  public static class IMEImage extends AbstractImage {

    /**
     * Constructor for a PPMImage object.
     *
     * @param width  The width of the image in pixels.
     * @param height The height of the image in pixels.
     * @param red    The red component of the rgb of the image.
     * @param blue   The blue component of the rgb of the image.
     * @param green  The green component of the rgb of the image.
     */
    public IMEImage(int width, int height, int[][] red,
                    int[][] blue, int[][] green) {
      super(width, height, red,
              blue, green);
    }

  }
}
