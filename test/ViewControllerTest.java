import static org.junit.Assert.assertEquals;

import ime.control.Controller;
import ime.control.TextController;
import ime.control.UIController;
import ime.model.ImageModel;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;

/**
 * Class to test Controller to View interaction.
 */
public class ViewControllerTest {

  @Test
  public void testTextPromptAndGetScanner() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("");
    StringBuilder log = new StringBuilder();
    Controller controllerTest = new TextController(new ImageModel(),
        new MockView(log, 3390, in));
    controllerTest.run();
    assertEquals("textPrompt: uniqueCode = 3390\ngetting scanner: uniqueCode = 3390\n",
        log.toString());
  }

  @Test
  public void testUnknownCommandPrompt() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("blah");
    StringBuilder log = new StringBuilder();
    Controller controllerTest = new TextController(new MockModel(log, 99),
        new MockView(log, 99, in));
    controllerTest.run();
    assertEquals("textPrompt: uniqueCode = 99\ngetting scanner: uniqueCode = 99\n"
        + "unknown command prompt: uniqueCode = 99\ntextPrompt: uniqueCode = 99\n", log.toString());
  }

  @Test
  public void testPrintGeneralError() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("run invalidpath");
    StringBuilder log = new StringBuilder();
    Controller controllerTest = new TextController(new MockModel(log, 3),
        new MockView(log, 3, in));
    controllerTest.run();
    assertEquals("textPrompt: uniqueCode = 3\ngetting scanner: uniqueCode = 3\n"
        + "printing error message: error message = Could not get path: invalidpath "
        + "uniqueCode = 3\ntextPrompt: uniqueCode = 3\n", log.toString());
  }


  @Test
  public void testAddFeatures() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("");
    StringBuilder log = new StringBuilder();
    Controller controllerTest = new UIController(new MockModel(log, 39),
        new MockView(log, 39, in));
    controllerTest.run();
    assertEquals("adding features: uniqueCode = 39\n", log.toString());
  }



}
