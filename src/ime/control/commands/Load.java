package ime.control.commands;

import ime.model.Model;
import java.io.FileNotFoundException;

/**
 * Class for load command.
 */
public class Load implements Command {

  String[] commands;

  /**
   * Constructor for Load.
   *
   * @param commands String array of commands.
   */
  public Load(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have the model m run its loadImage method.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) throws FileNotFoundException {
    if (commands.length != 2) {
      throw new IllegalArgumentException("Invalid number of arguments for command \"load\". "
          + "2 required.");
    }
    String loadImagePath = commands[0];
    String loadImageName = commands[1];
    m.loadImage(loadImagePath, loadImageName);
  }
}
