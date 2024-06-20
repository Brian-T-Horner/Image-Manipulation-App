package ime.control;

import java.io.IOException;

import ime.model.Model;

/**
 * Interface for controller objects for the MVC model of this program.
 */
public interface Controller {

  /**
   * Run method for the program. Takes in input, parses input and calls methods to instruct
   *    its model.
   *
   */
  void run() throws IOException;

  /**
   * Determines whether the program should exit.
   *
   * @return true if quit prompted false if not prompted
   */
  boolean isQuit();

  boolean runFile(String fileIn, Model currentModel) throws IOException;

}
