import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import ime.ImageUtil;
import ime.model.Image;
import ime.model.ImageModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test class for ImageUtil.
 */
public class ImageUtilTest {

  Image donutImg;

  BufferedImage buf;


  @Before
  public void setUp() {
    donutImg = readInTestImage();

    assert donutImg != null;
    buf = readInTestBufferedImage(donutImg);

  }

  @Test
  public void testReadIntoPPMImage() {
    assertNotNull(ImageUtil.loadPPMImage("test/donut.ppm"));
    assertNull(ImageUtil.loadPPMImage("invalid-path"));
  }


  @Test
  public void testLoadImage() {
    // TODO edge cases?
    assertNotNull(ImageUtil.loadImage("test/donut.ppm"));
    assertNotNull(ImageUtil.loadImage("test/donut.png"));
    assertNotNull(ImageUtil.loadImage("test/donut.bmp"));
    assertNotNull(ImageUtil.loadImage("test/donut.jpg"));
    assertNull(ImageUtil.loadImage("invalid-path"));
  }


  @Test
  public void testWriteBufferedImage() {
    BufferedImage donutBuf = ImageUtil.writeBufferedImage(donutImg);
    assertTrue(getBufferedImageEquality(donutBuf, buf));
  }


  @Test
  public void testReadBufferedImage() {
    Image bufImg = ImageUtil.readBufferedImage(buf);
    assertEquals(bufImg, donutImg);
  }


  @Test
  public void testSaveImage() {
    try {
      ImageUtil.saveImage(donutImg, "test/saved-donut-1.ppm");
    } catch (Exception e) {
      fail("testSaveImage:Saving ppm file failed");
    }

    try {
      ImageUtil.saveImage(donutImg, "test/saved-donut-1.png");
    } catch (Exception e) {
      fail("testSaveImage:Saving png file failed");
    }

    try {
      ImageUtil.saveImage(donutImg, "test/saved-donut-1.bmp");
    } catch (Exception e) {
      fail("testSaveImage:Saving bmp file failed");
    }

    try {
      ImageUtil.saveImage(donutImg, "test/saved-donut-1.jpg");
    } catch (Exception e) {
      fail("testSaveImage:Saving jpg file failed");
    }

    File ppm = new File("test/saved-donut-1.ppm");
    ppm.deleteOnExit();
    assertTrue(ppm.exists());
    File png = new File("test/saved-donut-1.png");
    png.deleteOnExit();
    assertTrue(png.exists());
    File bmp = new File("test/saved-donut-1.bmp");
    bmp.deleteOnExit();
    assertTrue(bmp.exists());
    File jpg = new File("test/saved-donut-1.jpg");
    jpg.deleteOnExit();
    assertTrue(jpg.exists());

    assertThrows(IllegalArgumentException.class,
        () -> ImageUtil.saveImage(donutImg, "invalid-path"));
  }


  @Test
  public void testWriteToPPMFile() {

    try {
      ImageUtil.writeToPPMFile(donutImg, "test.ppm");
    } catch (Exception ignored) {
      fail("ppm image could not be written");
    }
    File file = new File("test.ppm");
    file.deleteOnExit();
    assertTrue(file.exists());
    assertThrows(IOException.class, () -> ImageUtil.writeToPPMFile(donutImg, "test"));
  }


  private Image readInTestImage() {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream("test/donut.ppm"));
    } catch (FileNotFoundException e) {
      return null;
    }


    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    int[][] red = new int[height][width];

    int[][] green = new int[height][width];

    int[][] blue = new int[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        red[i][j] = r;
        green[i][j] = g;
        blue[i][j] = b;
      }
    }
    return new ImageModel.IMEImage(width, height, red, blue, green);
  }

  private BufferedImage readInTestBufferedImage(Image image) {
    BufferedImage buffImage = new BufferedImage(image.getWidth(), image.getHeight(),
            BufferedImage.TYPE_INT_RGB);
    int r;
    int g;
    int b;
    int pixel;
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        r = image.getRedComponent()[i][j];
        g = image.getGreenComponent()[i][j];
        b = image.getBlueComponent()[i][j];
        pixel = 0xFF000000 + (r << 16) + (g << 8) + b;
        buffImage.setRGB(j, i, pixel);
      }
    }
    return buffImage;
  }

  private boolean getBufferedImageEquality(BufferedImage img1, BufferedImage img2) {
    if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
      for (int x = 0; x < img1.getWidth(); x++) {
        for (int y = 0; y < img1.getHeight(); y++) {
          if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
            return false;
          }
        }
      }
    } else {
      return false;
    }
    return true;
  }
}