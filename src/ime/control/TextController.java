package ime.control;

import ime.model.Model;
import ime.view.FileView;
import ime.view.View;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

/**
 * Class that is the IME.control.Controller of our MVC model.
 */
public class TextController extends AbstractController {


  /**
   * Contructor for the creation of a IME.control.Controller object.
   *
   */
  public TextController(Model model, View view) {
    super(model, view);
  }

  @Override
  public void run() throws IOException {
    Objects.requireNonNull(model);
    Objects.requireNonNull(view);

    view.textPrompt();
    putCommands();

    // LOGIC FOR TEXT/FILE BASED BELOW
    Scanner scan = view.getScanner();
    while (scan.hasNext()) {
      boolean cont = false;
      String in = scan.next();

      // If quit command
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        quit = true;
        cont = true;
      }

      // if run command
      if (in.equalsIgnoreCase("run")) {
        String[] commands = scan.nextLine().trim().split(" ");
        if (commands.length != 1) {
          throw new IllegalArgumentException(
                  "Invalid number of arguments for command \"run\". 1 required.");
        }
        try {
          quit = runFile(commands[0], model);
        } catch (IOException e) {
          view.printGeneralError("Could not get path: " + e.getMessage());
        }
        cont = true;
      }

      // If command is empty of pound sign provided
      if (in.isEmpty() || in.charAt(0) == '#') {
        cont = true;
      }

      if (cont) {
        if (isQuit()) {
          return;
        }
        view.textPrompt();
        continue;
      }
      executeCommand(in,scan);
      view.textPrompt();
    }
  }
}








