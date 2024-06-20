package ime.control.commands;

import ime.model.Model;
import java.io.FileNotFoundException;

/**
 * Class for sharpen command.
 */
public class Sharpen implements Command {

  String[] commands;

  /**
   * Constructor for Sharpen.
   *
   * @param commands String array of commands for object.
   */
  public Sharpen(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m call its sharpen method.
   *
   * @param m A model.
   * @throws FileNotFoundException If imageName is not found in memory.
   * @throws IllegalArgumentException If commands passed are not of sufficient length.
   */
  @Override
  public void run(Model m) throws FileNotFoundException, IllegalArgumentException {
    if (commands.length != 2) {
      throw new IllegalArgumentException("Invalid number of arguments for command \"dither\". "
          + "2 required.");
    }
    String imageName = commands[0];
    String newImageName = commands[1];
    m.sharpen(imageName, newImageName);

  }
}
