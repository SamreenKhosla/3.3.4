import java.awt.Color;
import javax.swing.JOptionPane; 

public class ImageApp {

  public static void main(String[] args) {

    String pictureFile = "lib/beach.jpg";

    Picture origImg = new Picture(pictureFile);
    Pixel[][] origPixels = origImg.getPixels2D();
    System.out.println(origPixels[0][0].getColor());
    origImg.explore();

    boolean running = true;

    while (running) {

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
          running = false;
          break;
      }
    }
  }


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


  public static void doRecolor(String file) {
    Picture img = new Picture(file);
    Pixel[][] pixels = img.getPixels2D();

    for (Pixel[] row : pixels)
      for (Pixel p : row) {
        int r = p.getRed(), g = p.getGreen(), b = p.getBlue();
        p.setRed(b);
        p.setGreen(r);
        p.setBlue(g);
      }

    img.setTitle("Recolor (BRG)");
    img.explore();
  }

  public static void doNegative(String file) {
    Picture img = new Picture(file);
    Pixel[][] pixels = img.getPixels2D();

    for (Pixel[] row : pixels)
      for (Pixel p : row) {
        p.setRed(255 - p.getRed());
        p.setGreen(255 - p.getGreen());
        p.setBlue(255 - p.getBlue());
      }

    img.setTitle("Negative");
    img.explore();
  }

  public static void doGrayscale(String file) {
    Picture img = new Picture(file);
    Pixel[][] pixels = img.getPixels2D();

    for (Pixel[] row : pixels)
      for (Pixel p : row) {
        int avg = (p.getRed() + p.getGreen() + p.getBlue()) / 3;
        p.setRed(avg);
        p.setGreen(avg);
        p.setBlue(avg);
      }

    img.setTitle("Grayscale");
    img.explore();
  }

  public static void doRotate180(String file) {
    Picture orig = new Picture(file);
    Pixel[][] src = orig.getPixels2D();

    Picture out = new Picture(orig.getHeight(), orig.getWidth());
    Pixel[][] dest = out.getPixels2D();

    int h = src.length, w = src[0].length;

    for (int r = 0; r < h; r++)
      for (int c = 0; c < w; c++)
        dest[h - 1 - r][w - 1 - c].setColor(src[r][c].getColor());

    out.setTitle("Rotate 180");
    out.explore();
  }

  public static void doRotate90CW(String file) {
    Picture orig = new Picture(file);
    Pixel[][] src = orig.getPixels2D();

    int h = src.length, w = src[0].length;
    Picture out = new Picture(w, h);
    Pixel[][] dest = out.getPixels2D();

    for (int r = 0; r < h; r++)
      for (int c = 0; c < w; c++)
        dest[c][h - 1 - r].setColor(src[r][c].getColor());

    out.setTitle("Rotate 90 CW");
    out.explore();
  }

  public static void doRotate90CCW(String file) {
    Picture orig = new Picture(file);
    Pixel[][] src = orig.getPixels2D();

    int h = src.length, w = src[0].length;
    Picture out = new Picture(w, h);
    Pixel[][] dest = out.getPixels2D();

    for (int r = 0; r < h; r++)
      for (int c = 0; c < w; c++)
        dest[w - 1 - c][r].setColor(src[r][c].getColor());

    out.setTitle("Rotate 90 CCW");
    out.explore();
  }

  public static void doAddSticker() {

    Picture big = new Picture("lib/beach.jpg");
    Picture small = new Picture("lib2/balloon.png");

    Pixel[][] bigPix = big.getPixels2D();
    Pixel[][] smPix = small.getPixels2D();

    int startR = 50;
    int startC = 50;

    for (int r = 0; r < smPix.length; r++)
      for (int c = 0; c < smPix[0].length; c++) {

        Color col = smPix[r][c].getColor();

        if (col.getRed() == 255 && col.getGreen() == 255 && col.getBlue() == 255)
          continue;

        int br = startR + r;
        int bc = startC + c;

        if (br < bigPix.length && bc < bigPix[0].length)
          bigPix[br][bc].setColor(col);
      }

    big.setTitle("Sticker Added");
    big.explore();
  }
}


//ChatGPT - Add JOptionPane GUI. (2025). ChatGPT. https://chatgpt.com/share/6939ef5a-e710-8002-8925-005ae010afdd
