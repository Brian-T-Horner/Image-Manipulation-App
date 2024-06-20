package ime.control.commands;

import java.util.NoSuchElementException;

import ime.model.Model;

/**
 * Class for intensity command.
 */
public class Intensity implements Command {

  String[] commands;

  /**
   * Constructor for Intensity.
   *
   * @param commands String array of commands for the object.
   */
  public Intensity(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m run its getIntensityImage method.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) throws NoSuchElementException, IllegalArgumentException {
    if (commands.length != 2) {
      throw new IllegalArgumentException("Invalid number of arguments for command \"intensity\". "
          + "2 required.");
    }
    String imageName = commands[0];
    String newImageName = commands[1];
    m.getIntensityImage(imageName, newImageName);
  }
}
