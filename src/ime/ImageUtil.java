package ime;

import ime.model.Image;

import ime.model.ImageModel;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;


/**
 * Utility class with all the IO operations used in the model.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static void printPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
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
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Color of pixel (" + i + "," + j + "): " + r + "," + g + "," + b);
      }
    }
  }

  /**
   * Method to read in an image in the PPM Format.
   *
   * @param filename Path to the image file.
   * @return A new IME.model.Image object from the loaded image.
   */
  public static Image loadPPMImage(String filename) {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
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

  /**
   * Method to read in an image in the PPM Format.
   *
   * @param filename Path to the image file.
   * @return A new IME.model.Image object from the loaded image.
   */
  public static Image loadImage(String filename) {

    if (filename.endsWith(".ppm")) {
      return loadPPMImage(filename);
    }
    BufferedImage buf;
    try {
      buf = ImageIO.read(new File(filename));
    } catch (IOException e) {
      return null;
    }
    return readBufferedImage(buf);
  }

  /**
   * Method to save an image.
   * @param image The name of the image to save.
   * @param fileName The place to save the image to disk.
   * @throws IOException If the path is not found.
   * @throws IllegalArgumentException If the type of image to be saved is not supported.
   */
  public static void saveImage(Image image, String fileName)
      throws IOException, IllegalArgumentException {
    if (fileName.endsWith(".ppm")) {
      writeToPPMFile(image, fileName);
      return;
    }
    String filetype;
    if (fileName.endsWith(".bmp")) {
      filetype = "bmp";
    } else if (fileName.endsWith(".png")) {
      filetype = "png";
    } else if (fileName.endsWith(".jpg")) {
      filetype = "jpg";
    } else {
      throw new IllegalArgumentException("File must be saved as a .ppm, .bmp, .png, .jpg file");
    }
    BufferedImage buf = writeBufferedImage(image);
    File outputfile = new File(fileName);
    ImageIO.write(buf, filetype, outputfile);
  }

  /**
   * Method to write a PPMImage to disk.
   *
   * @param image    Name of the image to write to disk.
   * @param fileName Path to location to write the image.
   * @throws IOException If path is invalid.
   */
  public static void writeToPPMFile(Image image, String fileName) throws IOException {
    File ppmFile = new File(fileName);
    FileWriter ppmWriter = new FileWriter(fileName);
    ppmWriter.write("P3\n");
    ppmWriter.write(String.format("%d %d\n", image.getWidth(), image.getHeight()));
    ppmWriter.write("255\n");
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        ppmWriter.write(String.format("%d\n", image.getRedComponent()[i][j]));
        ppmWriter.write(String.format("%d\n", image.getGreenComponent()[i][j]));
        ppmWriter.write(String.format("%d\n", image.getBlueComponent()[i][j]));
      }
    }
    ppmWriter.close();
  }

  /**
   * Method to write a buffered image from an image.
   *
   * @param image IME.model.Image to be used to write buffered image.
   * @return The resulting buffered image.
   */
  public static BufferedImage writeBufferedImage(Image image) {
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

  /**
   * Method to read a buffered image from a buffered image object.
   *
   * @param buf The buffered image object to read in.
   * @return A new PPMImage from the buffered image.
   */
  public static Image readBufferedImage(BufferedImage buf) {
    int height = buf.getHeight();
    int width = buf.getWidth();
    int[][] r = new int[height][width];
    int[][] g = new int[height][width];
    int[][] b = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int rgb = buf.getRGB(j, i);
        r[i][j] = (rgb >> 16) & 0xff;
        g[i][j] = rgb >> 8 & 0xff;
        b[i][j] = rgb & 0xff;
      }
    }
    return new ImageModel.IMEImage(width, height, r, b, g);
  }

}

