package ime.control.commands;

import ime.model.Model;
import java.util.NoSuchElementException;

//TODO: clarify with professor on what he means with these two -
// He mentioned that we can do color transformations off this gryscale

/**
 * Class for greyscale command.
 */
public class Greyscale implements Command {

  String[] commands;

  /**
   * Constructor for Greyscale.
   *
   * @param commands String array of commands for object.
   */
  public Greyscale(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m run its greyscale method.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) throws NoSuchElementException {
    if (commands.length == 3) {
      String greyScaleComponent = commands[0];
      String currentImageName = commands[1];
      String destImageName = commands[2];
      m.greyscale(greyScaleComponent, currentImageName, destImageName);
    } else if (commands.length == 2) {
      String imageName = commands[0];
      String newImageName = commands[1];
      m.matrixGreyscale(imageName, newImageName);
    } else {
      throw new IllegalArgumentException("Invalid number of arguments for command \"greyscale\". "
              + "2 required for matrix greyscale 3 required for greyscale with source image.");
    }
  }
}
