package ime.control;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import ime.ImageUtil;
import ime.model.Image;
import ime.model.Model;
import ime.view.Features;
import ime.view.View;

/**
 * Class to for the controller used with UI display.
 */
public class UIController extends AbstractController implements Features {

  /**
   * Constructor for UIController.
   *
   * @param model model for controller
   * @param view  view for controller
   */
  public UIController(Model model, View view) {
    super(model, view);
  }

  @Override
  public void run() throws IOException {
    Objects.requireNonNull(model);
    Objects.requireNonNull(view);
    view.addFeatures(this);
  }

  @Override
  public void blur() {
    if (checkImageInMemory()) {
      model.blur("currentImage", "currentImage");
      setImage();
      setChart();
    }
  }

  @Override
  public void brighten(int scale) {
    if (checkImageInMemory()) {
      model.brighten("currentImage", "currentImage", scale);
      setImage();
      setChart();
    }
  }

  @Override
  public void dither() {
    if (checkImageInMemory()) {
      model.dither("currentImage", "currentImage");
      setImage();
      setChart();
    }
  }

  @Override
  public void greyscale() {
    if (checkImageInMemory()) {
      model.greyscale("luma-component", "currentImage", "currentImage");
      setImage();
      setChart();

    }
  }

  @Override
  public void horizontalFlip() {
    if (checkImageInMemory()) {
      model.flipHorizontal("currentImage", "currentImage");
      setImage();
      setChart();
    }
  }

  @Override
  public void verticalFlip() {
    if (checkImageInMemory()) {
      model.flipVertical("currentImage", "currentImage");
      setImage();
      setChart();
    }
  }

  @Override
  public void loadImage(String path) {
    try {
      model.loadImage(path, "currentImage");
      setImage();
      setChart();
      view.setChartPanelVisible();
    } catch (Exception e) {
      view.printGeneralError(e.getMessage());
    }
  }

  @Override
  public void rgbCombine(String redPath, String greenPath, String bluePath) {
    Image cred = ImageUtil.loadImage(redPath);
    Image cgreen = ImageUtil.loadImage(greenPath);
    Image cblue = ImageUtil.loadImage(bluePath);
    model.rgbCombine("currentImage", cred, cgreen, cblue);
    view.setChartPanelVisible();
    setImage();
    setChart();

  }

  @Override
  public void rgbSplit() {
    if (checkImageInMemory()) {
      model.rgbSplit("currentImage", "currentImage", "green", "blue");
      setImage();
      setChart();
    }
  }

  @Override
  public void save(String path) {
    if (path.isEmpty()) {
      path = "./img.png";
    }
    if (checkImageInMemory()) {
      try {
        model.saveImage(path, "currentImage");
      } catch (Exception e) {
        view.printGeneralError(e.getMessage());
      }
    }

  }

  @Override
  public void sepia() {
    if (checkImageInMemory()) {
      model.sepia("currentImage", "currentImage");
      setImage();
      setChart();
    }
  }

  @Override
  public void sharpen() {
    if (checkImageInMemory()) {
      model.sharpen("currentImage", "currentImage");
      setImage();
      setChart();
    }
  }

  @Override
  public void luma() {
    if (checkImageInMemory()) {
      model.getLumaImage("currentImage", "currentImage");
      setImage();
      setChart();
    }
  }

  @Override
  public void value() {
    if (checkImageInMemory()) {
      model.getValueImage("currentImage", "currentImage");
      setImage();
      setChart();
    }
  }

  @Override
  public void intensity() {
    if (checkImageInMemory()) {
      model.getIntensityImage("currentImage", "currentImage");
      setImage();
      setChart();
    }
  }

  @Override
  public void exit() {
    System.out.println("Exiting application...");
    System.exit(1);
  }



  private void setImage() {
    BufferedImage i = ImageUtil.writeBufferedImage(model.getImage("currentImage"));
    view.setImage(i);

  }

  private void setChart() {


    // get intensity
    model.getIntensityImage("currentImage", "intensity-image-chart");
    Image intensity = model.getImage("intensity-image-chart");
    Image current = model.getImage("currentImage");

    if (Arrays.deepEquals(current.getRedComponent(), current.getGreenComponent())
            && Arrays.deepEquals(current.getGreenComponent(), current.getBlueComponent())
            && Arrays.deepEquals(current.getBlueComponent(), current.getRedComponent())) {
      view.updateGreyChartPanel(intensity.getRedComponent());
    } else {
      view.updateColoredChartPanel(current.getRedComponent(), current.getGreenComponent(),
              current.getBlueComponent(), intensity.getRedComponent());
    }
    // check if rgb is equal

    // if it is setChartGreyScale

    // if it is not setChart Normal
    // get red component
    // get green component
    // get blue component


  }

  private boolean checkImageInMemory() {
    try {
      model.getImage("currentImage");
      return true;
    } catch (Exception e) {
      view.printGeneralError("Please load an image.");
      return false;
    }
  }
}
