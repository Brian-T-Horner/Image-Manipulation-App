package ime.control.commands;

import ime.model.Model;
import java.io.IOException;

/**
 * Interface for commands of the program that run their given methods in the model.
 */
public interface Command {

  /**
   * Method to run the inheriting commands functionality on a model m.
   * @param m A model.
   */
  void run(Model m) throws IOException;

}
