package ime.control.commands;

import ime.model.Model;
import java.io.IOException;

/**
 * Class for save command.
 */
public class Save implements Command {

  String[] commands;

  /**
   * Constructor for Save.
   *
   * @param commands String array of commands.
   */
  public Save(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m run its saveImage method.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) throws IOException, IllegalArgumentException {
    if (commands.length != 2) {
      throw new IllegalArgumentException("Invalid number of arguments for command \"save\". "
          + "2 required.");
    }
    String saveImagePath = commands[0];
    String saveImageName = commands[1];
    m.saveImage(saveImagePath, saveImageName);
  }
}
