package ime.control.commands;

import java.io.FileNotFoundException;
import ime.model.Model;

/**
 * Class for dither command.
 */
public class Dither implements Command {

  String[] commands;

  /**
   * Constructor for Dither.
   *
   * @param commands String array of commands.
   */
  public Dither(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m call its dither method.
   *
   * @param m A model.
   * @throws FileNotFoundException If imageName is not found in memory.
   * @throws IllegalArgumentException If commands passed are not sufficient in length.
   */
  @Override
  public void run(Model m) throws FileNotFoundException, IllegalArgumentException {
    if (commands.length != 2) {
      throw new IllegalArgumentException("Invalid number of arguments for command \"dither\". "
          + "2 required.");
    }
    String imageName = commands[0];
    String newImageName = commands[1];
    m.dither(imageName, newImageName);
  }
}
