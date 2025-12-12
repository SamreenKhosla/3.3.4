import java.awt.Color;
import javax.swing.JOptionPane; 

public class ImageApp {

  public static void main(String[] args) {

    String pictureFile = "lib/beach.jpg";

    // Load the original image from library folder
    Picture origImg = new Picture(pictureFile);
    Pixel[][] origPixels = origImg.getPixels2D();
    //Print color of top-left pixel for debugging
    System.out.println(origPixels[0][0].getColor());
    //open a window to explore original image 
    origImg.explore();

    boolean running = true;

    //Main loop that keeps running until user chooses to quit
    while (running) {

      //show menu and get user choice
      String choice = showMenu();

      switch (choice) { 

        case "Recolor":
          doRecolor(pictureFile);
          break;

        case "Negative":
          doNegative(pictureFile);
          break;

        case "Grayscale":
          doGrayscale(pictureFile);
          break;

        case "Rotate 180":
          doRotate180(pictureFile);
          break;

        case "Rotate 90 CW":
          doRotate90CW(pictureFile);
          break;

        case "Rotate 90 CCW":
          doRotate90CCW(pictureFile);
          break;

        case "Add Sticker":
          doAddSticker();
          break;

        case "Quit":
          //exit the loop and end program
          running = false;
          break;
      }
    }
  }


  //Displays a menu using JOptionPane and returns the user's choice
  public static String showMenu() {

    String[] ops = {
      "Recolor",
      "Negative",
      "Grayscale",
      "Rotate 180",
      "Rotate 90 CW",
      "Rotate 90 CCW",
      "Add Sticker",
      "Quit"
    };

    String choice = (String) JOptionPane.showInputDialog(
      null,
      "Choose an image operation:",
      "ImageApp Menu",
      JOptionPane.PLAIN_MESSAGE,
      null,
      ops,
      ops[0]
    );

    if (choice == null) return "Quit";
    return choice;
  }


  //Recolors the image by swapping RGB to BRG
  public static void doRecolor(String file) {
    Picture img = new Picture(file);
    Pixel[][] pixels = img.getPixels2D();

    for (Pixel[] row : pixels)
      for (Pixel p : row) {
        int r = p.getRed(), g = p.getGreen(), b = p.getBlue();
        // Swap Red and Blue
        p.setRed(b);
        p.setGreen(r);
        p.setBlue(g);
      }

    img.setTitle("Recolor (BRG)");
    img.explore();
  }

  //Creates a negative of the image by inverting RGB values
  public static void doNegative(String file) {
    Picture img = new Picture(file);
    Pixel[][] pixels = img.getPixels2D();

    for (Pixel[] row : pixels)
      for (Pixel p : row) {
    //` Invert each color component
        p.setRed(255 - p.getRed());
        p.setGreen(255 - p.getGreen());
        p.setBlue(255 - p.getBlue());
      }

    img.setTitle("Negative");
    img.explore();
  }

  //Converts the image to grayscale by averaging RGB values
  public static void doGrayscale(String file) {
    Picture img = new Picture(file);
    Pixel[][] pixels = img.getPixels2D();

    for (Pixel[] row : pixels)
      for (Pixel p : row) {

        // Calculate average of RGB components
        int avg = (p.getRed() + p.getGreen() + p.getBlue()) / 3;
        p.setRed(avg);
        p.setGreen(avg);
        p.setBlue(avg);
      }

    img.setTitle("Grayscale");
    img.explore();
  }

  //Rotates the image 180 degrees
  public static void doRotate180(String file) {
    Picture orig = new Picture(file);
    Pixel[][] src = orig.getPixels2D();

    // Create new picture with swapped dimensions
    Picture out = new Picture(orig.getHeight(), orig.getWidth());
    Pixel[][] dest = out.getPixels2D();

    int h = src.length, w = src[0].length;

    // copy pixels in reverse order to rotate 180 degrees
    for (int r = 0; r < h; r++)
      for (int c = 0; c < w; c++)
        dest[h - 1 - r][w - 1 - c].setColor(src[r][c].getColor());

    out.setTitle("Rotate 180");
    out.explore();
  }

  //Rotates the image 90 degrees clockwise
  public static void doRotate90CW(String file) {
    Picture orig = new Picture(file);
    Pixel[][] src = orig.getPixels2D();

    int h = src.length, w = src[0].length;
    Picture out = new Picture(w, h);
    Pixel[][] dest = out.getPixels2D();

    // assigns pixels to new rotated positions 
    for (int r = 0; r < h; r++)
      for (int c = 0; c < w; c++)
        dest[c][h - 1 - r].setColor(src[r][c].getColor());

    out.setTitle("Rotate 90 CW");
    out.explore();
  }

  //Rotates the image 90 degrees counter-clockwise
  public static void doRotate90CCW(String file) {
    Picture orig = new Picture(file);
    Pixel[][] src = orig.getPixels2D();

    int h = src.length, w = src[0].length;
    Picture out = new Picture(w, h); //Swap width and height 
    Pixel[][] dest = out.getPixels2D();

    // assigns pixels to new rotated positions
    for (int r = 0; r < h; r++)
      for (int c = 0; c < w; c++)
        dest[w - 1 - c][r].setColor(src[r][c].getColor());

    out.setTitle("Rotate 90 CCW");
    out.explore();
  }

  //Adds a sticker image onto the larger main image at a fixed position
  public static void doAddSticker() {

    Picture big = new Picture("lib/beach.jpg");
    Picture small = new Picture("lib2/balloon.png");

    Pixel[][] bigPix = big.getPixels2D();
    Pixel[][] smPix = small.getPixels2D();

    int startR = 50; //Starting row for sticker
    int startC = 50; //Starting column for sticker

    for (int r = 0; r < smPix.length; r++)
      for (int c = 0; c < smPix[0].length; c++) {

        Color col = smPix[r][c].getColor();

        // Skip white pixels (make background  transparent)
        if (col.getRed() == 255 && col.getGreen() == 255 && col.getBlue() == 255)
          continue;

        int br = startR + r;
        int bc = startC + c;

        // only change pixels inside the bounds of the big image 
        if (br < bigPix.length && bc < bigPix[0].length)
          bigPix[br][bc].setColor(col);
      }

    big.setTitle("Sticker Added");
    big.explore();
  }
}


//ChatGPT - Add JOptionPane GUI. (2025). ChatGPT. https://chatgpt.com/share/6939ef5a-e710-8002-8925-005ae010afdd
