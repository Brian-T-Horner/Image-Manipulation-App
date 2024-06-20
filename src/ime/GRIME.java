package ime;

import java.io.IOException;
import java.io.InputStreamReader;

import ime.control.Controller;
import ime.control.TextController;
import ime.control.UIController;
import ime.model.ImageModel;
import ime.model.Model;
import ime.view.JFrameView;
import ime.view.TextView;
import ime.view.View;

/**
 * Main class of program in which main method is located.
 */
public class GRIME {

  /**
   * Main method of the program.
   *
   * @param args command line arguments to be passed to the program.
   */
  public static void main(String[] args) throws IOException {
    Model model = new ImageModel();
    View textView = new TextView(System.out, new InputStreamReader(System.in));
    Controller controller;
    if (args.length > 0) {
      controller = new TextController(model,textView);
      if (args[0].equals("-text") && args.length == 1) {
        controller.run();
      } else if (args[0].equals("-file") && args.length == 2) {
        //todo this is kinda hacky

        controller.runFile(args[1],model);
      } else {
        System.out.println("To run a text file please input "
                + "\"-file file-path\" as command line arguments.\n"
            + "To run in command line, please input \"-text\"");
      }
    } else {
      // TODO run UI controller here
      controller = new UIController(model, new JFrameView("Image Manipulation"));
      controller.run();
    }

  }
}
