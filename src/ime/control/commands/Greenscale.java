package ime.control.commands;

import java.io.FileNotFoundException;

import ime.model.Model;

/**
 * Class for greenscale command.
 */
public class Greenscale implements Command {

  String [] commands;

  /**
   * Constructor for Greenscale.
   *
   * @param commands String array of commands for the object.
   */
  public Greenscale(String[] commands) {
    this.commands = commands;
  }


  /**
   * Method to have model m run its getGreenComponent method.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) throws FileNotFoundException {
    if (commands.length != 2) {
      throw new IllegalArgumentException("Invalid number of arguments for command \"greenscale\". "
          + "3 required.");
    }
    String imageName = commands[0];
    String newImageName = commands[1];
    m.getGreenComponent(imageName, newImageName);
  }
}
