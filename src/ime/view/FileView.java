package ime.view;

/**
 * View for running program from a text file of scripts.
 */
public class FileView extends TextView {

  public FileView(Appendable out, Readable in) {
    super(out, in);
  }

  @Override
  public void textPrompt() {
    //no contents
  }
}
