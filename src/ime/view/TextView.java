package ime.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class for a TextView when running commands from script.
 */
public class TextView implements View {

  final Appendable out;

  final Readable in;

  public TextView(Appendable out, Readable in) {
    this.out = out;
    this.in = in;
  }

  @Override
  public void textPrompt() {
    try {
      out.append("$ ");
    } catch (IOException e) {
      System.out.println("View:User prompt failed to append");
    }
  }

  @Override
  public void unknownCommandPrompt() {
    try {
      out.append("Unrecognized command\n");
    } catch (IOException e) {
      System.out.println("View:Unknown command prompt failed to append");
    }
  }

  @Override
  public void printGeneralError(String errorMessage) {
    try {
      out.append(errorMessage).append("\n");
    } catch (IOException e) {
      System.out.println("View:Error message failed to append");
    }
  }


  public void readUserInput() {
    //do nothing
  }

  public Scanner getScanner() {
    return new Scanner(this.in);
  }

  @Override
  public void setImage(BufferedImage img) {
    // do nothing
  }

  @Override
  public void addFeatures(Features features) {
    // do nothing
  }

  @Override
  public void setChartPanelVisible(){
    // do nothing
  }

  @Override
  public void updateColoredChartPanel(int[][] red, int[][] green, int[][] blue, int[][] intensity) {
    //do nothing
  }

  @Override
  public void updateGreyChartPanel(int[][] intensity) {
    //do nothing
  }

}
