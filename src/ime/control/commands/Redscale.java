package ime.control.commands;

import java.io.FileNotFoundException;

import ime.model.Model;

/**
 * Class for redscale command.
 */
public class Redscale implements Command {
  String[] commands;

  /**
   * Constructor for Redscale.
   *
   * @param commands String array of commands.
   */
  public Redscale(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m run its getRedComponent method.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) throws FileNotFoundException {
    if (commands.length != 2) {
      throw new IllegalArgumentException("Invalid number of arguments for command \"redscale\". "
          + "2 required.");
    }
    String imageName = commands[0];
    String newImageName = commands[1];
    m.getRedComponent(imageName, newImageName);
  }
}

