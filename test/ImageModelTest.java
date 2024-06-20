import ime.model.Image;
import ime.model.ImageModel;
import ime.model.Model;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test class for ImageModel.
 */
public class ImageModelTest {


  Model m;


  @Before
  public void setUp() {
    m = new ImageModel();

    try {
      m.loadImage("test/donut.ppm", "donuts");
    } catch (Exception ignored) {
    }
  }


  @Test
  public void testFlipHorizontal() {
    m.flipHorizontal("donuts", "donuts-hflipped");
    Image i = m.getImage("donuts-hflipped");
    Image orig = m.getImage("donuts");
    assertArrayEquals(flipArrayHorizontal(orig, orig.getRedComponent()), i.getRedComponent());
    assertArrayEquals(flipArrayHorizontal(orig, orig.getBlueComponent()), i.getBlueComponent());
    assertArrayEquals(flipArrayHorizontal(orig, orig.getGreenComponent()), i.getGreenComponent());
    assertThrows(NoSuchElementException.class, ()
        -> m.flipHorizontal("invalid-donuts", "donuts-hflipped"));
  }

  @Test
  public void testFlipVertical() {
    m.flipVertical("donuts", "donuts-vflipped");
    Image i = m.getImage("donuts-vflipped");
    Image orig = m.getImage("donuts");
    assertArrayEquals(flipArrayVertical(orig, orig.getRedComponent()), i.getRedComponent());
    assertArrayEquals(flipArrayVertical(orig, orig.getBlueComponent()), i.getBlueComponent());
    assertArrayEquals(flipArrayVertical(orig, orig.getGreenComponent()), i.getGreenComponent());
    assertThrows(NoSuchElementException.class, ()
        -> m.flipVertical("invalid-donuts", "donuts-vflipped"));
  }

  @Test
  public void testGetValueImage() {
    m.getValueImage("donuts", "donuts-value");
    Image i = m.getImage("donuts-value");
    assertArrayEquals(valueArray(m.getImage("donuts")), i.getBlueComponent());
    assertArrayEquals(valueArray(m.getImage("donuts")), i.getGreenComponent());
    assertArrayEquals(valueArray(m.getImage("donuts")), i.getRedComponent());
    assertThrows(NoSuchElementException.class, ()
        -> m.getValueImage("invalid-donuts", "donuts-value"));
    assertTrue(checkCeilAndFloorVals(i.getGreenComponent()));
  }

  @Test
  public void testGetIntensityImage() {
    m.getIntensityImage("donuts", "donuts-intensity");
    Image i = m.getImage("donuts-intensity");
    assertArrayEquals(intensityArray(m.getImage("donuts")), i.getBlueComponent());
    assertArrayEquals(intensityArray(m.getImage("donuts")), i.getGreenComponent());
    assertArrayEquals(intensityArray(m.getImage("donuts")), i.getRedComponent());
    assertThrows(NoSuchElementException.class, ()
        -> m.getIntensityImage("invalid-donuts", "donuts-intensity"));
    assertTrue(checkCeilAndFloorVals(i.getGreenComponent()));
  }

  @Test
  public void testGetLumaImage() {
    m.getLumaImage("donuts", "donuts-luma");
    Image i = m.getImage("donuts-luma");
    assertArrayEquals(lumaArray(m.getImage("donuts")), i.getBlueComponent());
    assertArrayEquals(lumaArray(m.getImage("donuts")), i.getGreenComponent());
    assertArrayEquals(lumaArray(m.getImage("donuts")), i.getRedComponent());
    assertThrows(NoSuchElementException.class, ()
        -> m.getLumaImage("invalid-donuts", "donuts-luma"));
    assertTrue(checkCeilAndFloorVals(i.getGreenComponent()));
  }

  @Test
  public void testBrighten() {
    m.brighten("donuts", "donuts-brighten-by-10", 10);
    Image i = m.getImage("donuts-brighten-by-10");
    Image orig = m.getImage("donuts");
    assertArrayEquals(brightenArray(orig, orig.getRedComponent(), 10), i.getRedComponent());
    assertArrayEquals(brightenArray(orig, orig.getBlueComponent(), 10), i.getBlueComponent());
    assertArrayEquals(brightenArray(orig, orig.getGreenComponent(), 10), i.getGreenComponent());
    assertThrows(NoSuchElementException.class, ()
        -> m.flipVertical("invalid-donuts", "donuts-vflipped"));
    //TODO add test for inputting NotANumber
    assertTrue(checkCeilAndFloorVals(i.getGreenComponent()));
    assertTrue(checkCeilAndFloorVals(i.getRedComponent()));
    assertTrue(checkCeilAndFloorVals(i.getBlueComponent()));
  }

  @Test
  public void testGetRedComponent() {
    assertThrows(NoSuchElementException.class, ()
        -> m.getRedComponent("invalid-donuts", "donuts-red"));
  }

  @Test
  public void testGetGreenComponent() {
    assertThrows(NoSuchElementException.class, ()
        -> m.getGreenComponent("invalid-donuts", "donuts-red"));
  }

  @Test
  public void testGetBlueComponent() {
    assertThrows(NoSuchElementException.class, ()
        -> m.getBlueComponent("invalid-donuts", "donuts-red"));
  }

  @Test
  public void testRgbSplit() {
    m.rgbSplit("donuts", "donuts-red",
        "donuts-green", "donuts-blue");
    Image red = m.getImage("donuts-red");
    Image green = m.getImage("donuts-green");
    Image blue = m.getImage("donuts-blue");
    Image orig = m.getImage("donuts");
    assertArrayEquals(orig.getRedComponent(), red.getRedComponent());
    assertArrayEquals(orig.getRedComponent(), red.getGreenComponent());
    assertArrayEquals(orig.getRedComponent(), red.getBlueComponent());

    assertArrayEquals(orig.getGreenComponent(), green.getRedComponent());
    assertArrayEquals(orig.getGreenComponent(), green.getGreenComponent());
    assertArrayEquals(orig.getGreenComponent(), green.getBlueComponent());

    assertArrayEquals(orig.getBlueComponent(), blue.getRedComponent());
    assertArrayEquals(orig.getBlueComponent(), blue.getGreenComponent());
    assertArrayEquals(orig.getBlueComponent(), blue.getBlueComponent());
    assertThrows(NoSuchElementException.class, () -> m.rgbSplit("invalid-donuts",
        "donuts-red", "donuts-green", "donuts-blue"));
  }

  @Test
  public void testRgbCombine() {
    m.rgbSplit("donuts", "donuts-red",
        "donuts-green", "donuts-blue");
    Image red = m.getImage("donuts-red");
    Image green = m.getImage("donuts-green");
    Image blue = m.getImage("donuts-blue");

    m.rgbCombine("donuts-new", "donuts-red",
        "donuts-green", "donuts-blue");

    Image newImg = m.getImage("donuts-new");

    assertArrayEquals(red.getRedComponent(), newImg.getRedComponent());
    assertArrayEquals(green.getGreenComponent(), newImg.getGreenComponent());
    assertArrayEquals(blue.getBlueComponent(), newImg.getBlueComponent());
    assertThrows(NoSuchElementException.class, () -> m.rgbCombine("donuts-new",
        "invalid-donuts-red", "donuts-green", "donuts-blue"));
    assertThrows(NoSuchElementException.class, () -> m.rgbCombine("donuts-new",
        "donuts-red", "invalid-donuts-green", "donuts-blue"));
    assertThrows(NoSuchElementException.class, () -> m.rgbCombine("donuts-new",
        "donuts-red", "donuts-green", "invalid-donuts-blue"));
  }

  @Test
  public void testGreyscale() {
    m.greyscale("red-component", "donuts",
        "donuts-red");
    m.greyscale("green-component", "donuts",
        "donuts-green");
    m.greyscale("blue-component", "donuts",
        "donuts-blue");

    m.greyscale("value-component", "donuts",
        "donuts-value");
    m.greyscale("intensity-component", "donuts",
        "donuts-intensity");
    m.greyscale("luma-component", "donuts",
        "donuts-luma");

    Image red = m.getImage("donuts-red");
    Image green = m.getImage("donuts-green");
    Image blue = m.getImage("donuts-blue");
    Image value = m.getImage("donuts-value");
    Image intensity = m.getImage("donuts-intensity");
    Image luma = m.getImage("donuts-luma");
    Image orig = m.getImage("donuts");

    m.getValueImage("donuts", "donuts-value");

    m.getIntensityImage("donuts", "donuts-intensity");

    m.getLumaImage("donuts", "donuts-luma");
    Image lumaExpected = m.getImage("donuts-luma");

    Image valueExpected = m.getImage("donuts-value");

    Image intensityExpected = m.getImage("donuts-intensity");

    assertArrayEquals(red.getRedComponent(), orig.getRedComponent());
    assertArrayEquals(green.getGreenComponent(), orig.getGreenComponent());
    assertArrayEquals(blue.getBlueComponent(), orig.getBlueComponent());

    assertArrayEquals(lumaExpected.getGreenComponent(), luma.getGreenComponent());
    assertArrayEquals(valueExpected.getGreenComponent(), value.getGreenComponent());
    assertArrayEquals(intensityExpected.getGreenComponent(), intensity.getGreenComponent());
    assertThrows(IllegalArgumentException.class, ()
        -> m.greyscale("invalid-component", "donuts",
        "invalid-greyscale"));
  }

  @Test
  public void testLoadImage() {
    try {
      m.loadImage("test/donut.ppm", "donut-1");
    } catch (Exception e) {
      fail("ppm file could not be loaded");
    }

    try {
      m.loadImage("test/donut.png", "donut-2");
    } catch (Exception e) {
      fail("png file could not be loaded");
    }

    try {
      m.loadImage("test/donut.bmp", "donut-3");
    } catch (Exception e) {
      fail("bmp file could not be loaded");
    }

    try {
      m.loadImage("test/donut.jpg", "donut-4");
    } catch (Exception e) {
      fail("jpg file could not be loaded");
    }

    assertNotNull(m.getImage("donut-1"));
    assertNotNull(m.getImage("donut-2"));
    assertNotNull(m.getImage("donut-3"));
    assertNotNull(m.getImage("donut-4"));
    assertThrows(IllegalArgumentException.class, ()
        -> m.loadImage("invalid/path", "donut"));
    assertThrows(FileNotFoundException.class, ()
        -> m.loadImage("invalid/path.ppm", "donut"));
  }

  @Test
  public void testSaveImage() {
    try {
      m.loadImage("test/donut.ppm", "donut-1");
    } catch (Exception e) {
      fail("Error in testSaveImage because loadImage fails");
    }

    try {
      m.saveImage("test/donut.ppm", "donut-1");
    } catch (Exception e) {
      fail("ppm file could not be saved");
    }

    try {
      m.saveImage("test/donut.png", "donut-1");
    } catch (Exception e) {
      fail("png file could not be saved");
    }

    try {
      m.saveImage("test/donut.bmp", "donut-1");
    } catch (Exception e) {
      fail("bmp file could not be saved");
    }

    try {
      m.saveImage("test/donut.jpg", "donut-1");
    } catch (Exception e) {
      fail("jpg file could not be saved");
    }

    assertThrows(IOException.class, ()
        -> m.saveImage("invalid-path", "donut-1"));
    assertThrows(NoSuchElementException.class, ()
        -> m.saveImage("test/test.ppm", "invalid-donut"));
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

  private int[][] valueArray(Image img) {
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
    return value;
  }

  private int[][] intensityArray(Image img) {
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
    return intensity;
  }

  private int[][] lumaArray(Image img) {
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
    return luma;
  }

  private boolean checkCeilAndFloorVals(int[][] arr) {
    for (int[] ints : arr) {
      for (int j = 0; j < arr[0].length; j++) {
        if (ints[j] > 255 || ints[j] < 0) {
          return false;
        }
      }
    }
    return true;
  }

  @Test
  public void testSharpen() {
    sharpenArray(m.getImage("donuts"));
    assertNotNull(m.getImage("donuts"));

  }

  private int[][] sharpenArray(Image img) {
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
        System.out.println((int) Math.rint(redSum));

        //blue
        newB[i][j] = (int) Math.rint(blueSum);
        // green
        newG[i][j] = (int) Math.rint(greenSum);
      }
    }
    return new int[1][1];

  }


  private int[][] blurArray(Image img) {
    return new int[1][1];
  }

  private int[][] ditherArray(Image img) {
    return new int[1][1];
  }

  @Test
  public void testSepia() throws FileNotFoundException {
    m.sepia("donuts", "donuts-sepia");
    Image i = m.getImage("donuts-sepia");
    Image control = createTestImage("test/sepiaImg.txt");
    assertArrayEquals(i.getRedComponent(), control.getRedComponent());
    assertArrayEquals(i.getBlueComponent(), control.getBlueComponent());
    assertArrayEquals(i.getGreenComponent(), control.getGreenComponent());
    assertThrows(NoSuchElementException.class, ()
        -> m.sepia("invalid-donuts", "donuts-sepia"));
  }

  private Image createTestImage(String pathname) throws FileNotFoundException {
    int height = 150;
    int width = 200;
    Scanner scanner = new Scanner(new File(pathname));
    int [][] rComponent = new int[height][width];
    int [][] gComponent = new int[height][width];
    int [][] bComponent = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        rComponent[i][j] = scanner.nextInt();
      }
    }


    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        gComponent[i][j] = scanner.nextInt();
      }
    }
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        bComponent[i][j] = scanner.nextInt();
      }
    }

    Image controlImg = new ImageModel.IMEImage(width, height, rComponent, bComponent, gComponent);
    return controlImg;
  }
}