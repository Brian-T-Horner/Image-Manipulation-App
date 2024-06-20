package ime.control.commands;

import ime.model.Model;
import java.io.FileNotFoundException;

/**
 * Class for blur command.
 */
public class Blur implements Command {

  String[] commands;

  /**
   * Constructor for Blur.
   * @param commands String array of commands.
   */
  public Blur(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m run its blur method.
   * @param m A model.
   * @throws FileNotFoundException If the imageName is not found.
   * @throws IllegalArgumentException If there are insufficient arguments.
   */
  @Override
  public void run(Model m) throws FileNotFoundException, IllegalArgumentException {
    if (commands.length != 2) {
      throw new IllegalArgumentException("Invalid number of arguments for command \"dither\". "
          + "2 required.");
    }
    String imageName = commands[0];
    String newImageName = commands[1];
    m.blur(imageName, newImageName);
  }
}
