package ime.control.commands;

import ime.model.Model;
import java.util.NoSuchElementException;

/**
 * Class for horizontal-flip command.
 */
public class HorizontalFlip implements Command {

  String[] commands;

  /**
   * Constructor for HorizontalFlip.
   *
   * @param commands String array of commands for object.
   */
  public HorizontalFlip(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m run its flipHorizontal method.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) throws NoSuchElementException,IllegalArgumentException {
    if (commands.length != 2) {
      throw new IllegalArgumentException("Invalid number of arguments for command "
          + "\"horizontal-flip\". 2 required.");
    }
    String imageName = commands[0];
    String newImageName = commands[1];
    m.flipHorizontal(imageName, newImageName);
  }
}
