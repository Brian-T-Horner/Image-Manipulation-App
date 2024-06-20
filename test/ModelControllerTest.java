import static org.junit.Assert.assertEquals;

import ime.control.Controller;
import ime.control.TextController;
import ime.view.FileView;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;

/**
 * Class to test the controller in isolation with a MockModel.
 */
public class ModelControllerTest {


  /**
   * Method to test the controller to loadPPMImage method.
   */
  @Test
  public void testLoadPPMImage() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nquit");
    StringBuilder log = new StringBuilder();
    Controller controllerTest = new TextController(new MockModel(log, 3390),
        new FileView(out, in));
    controllerTest.run();
    assertEquals("loadPPMImage: imagePath = images/koala.ppm "
        + "newImageName = koala uniqueCode = 3390\n", log.toString());
    //TODO test .bmp, .jpg, .png file inputs
  }

  /**
   * Method to test the controller to savePPMImage method.
   */
  @Test
  public void testSavePPMImage() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("save images/koala-brighter.ppm koala-brighter");
    StringBuilder log = new StringBuilder();
    Controller controllerTest = new TextController(new MockModel(log, 4537),
        new FileView(out, in));
    controllerTest.run();
    assertEquals("savePPMImage: imagePath = images/koala-brighter.ppm "
        + "imageName = koala-brighter uniqueCode = 4537\n", log.toString());
    //TODO test .bmp, .jpg, .png file outputs
  }

  /**
   * Method to test the controller to flipHorizontal method.
   */
  @Test
  public void testFlipHorizontal() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("horizontal-flip koala-vertical koala-vertical-horizontal");
    StringBuilder log = new StringBuilder();
    Controller controllerTest = new TextController(new MockModel(log, 7362),
        new FileView(out, in));
    controllerTest.run();
    assertEquals("flipHorizontal: currentImageName = koala-vertical "
        + "newImageName = koala-vertical-horizontal uniqueCode = 7362\n", log.toString());
  }

  /**
   * Method to test the controller to flipVertical method.
   */
  @Test
  public void testFlipVertical() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("vertical-flip koala koala-vertical");
    StringBuilder log = new StringBuilder();
    Controller controllerTest = new TextController(new MockModel(log, 999),
        new FileView(out, in));
    controllerTest.run();
    assertEquals("flipVertical: currentImageName = koala "
        + "newImageName = koala-vertical uniqueCode = 999\n", log.toString());
  }

  /**
   * Method to test the controller to valueImage method.
   */
  @Test
  public void testValueImage() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("value koala koala-value");
    StringBuilder log = new StringBuilder();
    Controller controllerTest = new TextController(new MockModel(log, 1432),
        new FileView(out, in));
    controllerTest.run();
    assertEquals("getValueImage: currentImageName = koala "
        + "newImageName = koala-value uniqueCode = 1432\n", log.toString());
  }

  /**
   * Method to test the controller to intensityImage method.
   */
  @Test
  public void testIntensityImage() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("intensity koala koala-intensity");
    StringBuilder log = new StringBuilder();
    Controller controllerTest = new TextController(new MockModel(log, 33332),
        new FileView(out, in));
    controllerTest.run();
    assertEquals("getIntensityImage: currentImageName = koala "
        + "newImageName = koala-intensity uniqueCode = 33332\n", log.toString());
  }


  /**
   * Method to test the controller to lumaImage method.
   */
  @Test
  public void testLumaImage() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("luma koala koala-luma");
    StringBuilder log = new StringBuilder();
    Controller controllerTest = new TextController(new MockModel(log, 9191),
        new FileView(out, in));
    controllerTest.run();
    assertEquals("getLumaImage: currentImageName = koala "
        + "newImageName = koala-luma uniqueCode = 9191\n", log.toString());
  }

  /**
   * Method to test the controller to brighten method.
   */
  @Test
  public void testBrighten() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("brighten 50 koala koala-brighten");
    StringBuilder log = new StringBuilder();
    Controller controllerTest = new TextController(new MockModel(log, 34342),
        new FileView(out, in));
    controllerTest.run();
    assertEquals("brighten: currentImageName = koala "
        + "newImageName = koala-brighten scale = 50 uniqueCode = 34342\n", log.toString());
  }

  /**
   * Method to test the controller to brighten (darken) method.
   */
  @Test
  public void testDarken() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("brighten -50 koala koala-brighten");
    StringBuilder log = new StringBuilder();
    Controller controllerTest = new TextController(new MockModel(log, 3754),
        new FileView(out, in));
    controllerTest.run();
    assertEquals("brighten: currentImageName = koala "
        + "newImageName = koala-brighten scale = -50 uniqueCode = 3754\n", log.toString());
  }

  /**
   * Method to test the controller to rgbSplit method.
   */
  @Test
  public void testRGBSplit() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("rgb-split koala koala-red koala-green koala-blue");
    StringBuilder log = new StringBuilder();
    Controller controllerTest = new TextController(new MockModel(log, 6767),
        new FileView(out, in));
    controllerTest.run();
    assertEquals("rgbSplit: currentImageName = koala "
        + "redImageName = koala-red greenImageName = koala-green blueImageName "
        + "= koala-blue uniqueCode = 6767\n", log.toString());
  }

  /**
   * Method to test the controller to rgbCombine method.
   */
  @Test
  public void testRGBCombine() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("rgb-combine koala-red-tint koala-red koala-green koala-blue");
    StringBuilder log = new StringBuilder();
    Controller controllerTest = new TextController(new MockModel(log, 42131),
        new FileView(out, in));
    controllerTest.run();
    assertEquals("rgbCombine: newImageName = koala-red-tint "
            + "redImageName = koala-red greenImageName = koala-green blueImageName "
            + "= koala-blue uniqueCode = 42131\n", log.toString());
  }

  /**
   * Method to test the controller to greyScale method.
   */
  @Test
  public void testGreyScale() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("greyscale value-component koala koala-greyscale");
    StringBuilder log = new StringBuilder();
    Controller controllerTest = new TextController(new MockModel(log, 754329),
        new FileView(out, in));
    controllerTest.run();
    assertEquals("greyscale: greyScaleComponent = value-component "
            + "imageName = koala newImageName = koala-greyscale uniqueCode = 754329\n",
        log.toString());
  }

  /**
   * Method to test the controller to dither method.
   */
  @Test
  public void testDither() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("dither koala dither-koala");
    StringBuilder log = new StringBuilder();
    Controller controllerTest = new TextController(new MockModel(log, 645),
        new FileView(out, in));
    controllerTest.run();
    assertEquals("dither: imageName = koala newImageName "
            + "= dither-koala uniqueCode = 645\n", log.toString());

  }

  /**
   * Method to test the controller to sepia method.
   */
  @Test
  public void testSepia() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("sepia koala sepia-koala");
    StringBuilder log = new StringBuilder();
    Controller controllerTest = new TextController(new MockModel(log, 64),
        new FileView(out, in));
    controllerTest.run();
    assertEquals("sepia: imageName = koala newImageName "
            + "= sepia-koala uniqueCode = 64\n", log.toString());

  }


  /**
   * Method to test the controller to blur method.
   */
  @Test
  public void testBlur() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("blur koala blur-koala");
    StringBuilder log = new StringBuilder();
    Controller controllerTest = new TextController(new MockModel(log, 99),
        new FileView(out, in));
    controllerTest.run();
    assertEquals("blur: imageName = koala newImageName "
           + "= blur-koala uniqueCode = 99\n", log.toString());

  }

  /**
   * Method to test the controller to sharpen method.
   */
  @Test
  public void testSharpen() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("sharpen koala sharpen-koala");
    StringBuilder log = new StringBuilder();
    Controller controllerTest = new TextController(new MockModel(log, 346324),
        new FileView(out, in));
    controllerTest.run();
    assertEquals("sharpen: imageName = koala newImageName "
           + "= sharpen-koala uniqueCode = 346324\n", log.toString());

  }

  /**
   * Method to test the controller to matrixGreyscale method.
   */
  @Test
  public void testMatrixGreyscale() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("greyscale koala matrixGreyscale-koala");
    StringBuilder log = new StringBuilder();
    Controller controllerTest = new TextController(new MockModel(log, 3241),
        new FileView(out,in));
    controllerTest.run();
    assertEquals("matrixGreyscale: imageName = koala newImageName "
           + "= matrixGreyscale-koala uniqueCode = 3241\n", log.toString());

  }

}
