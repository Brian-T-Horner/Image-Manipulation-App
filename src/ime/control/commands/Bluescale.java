package ime.control.commands;

import java.io.FileNotFoundException;

import ime.model.Model;

/**
 * Class for bluescale command.
 */
public class Bluescale implements Command {

  String[] commands;

  /**
   * Constructor for Bluescale.
   *
   * @param commands String array of commands.
   */
  public Bluescale(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m call its getBlueComponent method.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) throws FileNotFoundException,IllegalArgumentException {
    if (commands.length != 2) {
      throw new IllegalArgumentException("Invalid number of arguments for command \"bluescale\". "
          + "2 required.");
    }
    String imageName = commands[0];
    String newImageName = commands[1];
    m.getBlueComponent(imageName, newImageName);

  }
}
