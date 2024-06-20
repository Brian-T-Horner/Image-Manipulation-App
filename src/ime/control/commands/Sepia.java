package ime.control.commands;

import java.io.FileNotFoundException;
import ime.model.Model;

/**
 * Class for sepia command.
 */
public class Sepia implements Command {

  String[] commands;

  /**
   * Constructor for Sepia.
   *
   * @param commands String array of commands.
   */
  public Sepia(String[] commands) {
    this.commands = commands;
  }


  /**
   * Method to have model m call its sepia method.
   *
   * @param m A model.
   * @throws FileNotFoundException If imageName is not found in memory.
   * @throws IllegalArgumentException If commands are not sufficient in length.
   */
  @Override
  public void run(Model m) throws FileNotFoundException, IllegalArgumentException {
    if (commands.length != 2) {
      throw new IllegalArgumentException("Invalid number of arguments for command \"sepia\". "
          + "2 required.");
    }
    String imageName = commands[0];
    String newImageName = commands[1];
    m.sepia(imageName, newImageName);

  }
}
