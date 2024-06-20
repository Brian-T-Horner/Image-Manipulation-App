package ime.control.commands;

import java.util.NoSuchElementException;

import ime.model.Model;

/**
 * Class for value command.
 */
public class Value implements Command {
  String[] commands;

  /**
   * Constructor for Value.
   *
   * @param commands String array of commands.
   */
  public Value(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m call its getValueImage method.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) throws NoSuchElementException, IllegalArgumentException {
    if (commands.length != 2) {
      throw new IllegalArgumentException("Invalid number of arguments for command \"value\". "
          + "2 required.");
    }
    String imageName = commands[0];
    String newImageName = commands[1];
    m.getValueImage(imageName, newImageName);
  }
}
