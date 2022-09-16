import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class SimpleAnimation {

  private int xPos = 1200;
  private int yPos = 700;

  public static void main(String[] args) {
    new SimpleAnimation().go();
  }

  public void go() {
    // make a frame
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

    // make a panel
    MyDrawPanel drawPanel = new MyDrawPanel();

    // add panel to frame
    frame.getContentPane().add(drawPanel);

    // show frame
    frame.setSize(1300, 700);
    frame.setVisible(true);

    // animate oval
    for (int i = 0; i < 250; i++) {
      xPos -= 6;
      yPos -= 3;
//      if (i <= 200) {
//    	  yPos++;
//      } else {
//    	  yPos--;
//      }

      drawPanel.repaint();

      try {
        TimeUnit.MILLISECONDS.sleep(50);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  // inner-class for JPanel
  class MyDrawPanel extends JPanel {
    public void paintComponent(Graphics g) {
      if (xPos == 100) System.out.println("From MyDrawPanel Inner class [this]: \n" + this + "\nHeight: " + this.getHeight() + "\nWidth: " + this.getWidth() + "\n");
    	
      // reset background
      g.setColor(Color.white);
      g.fillRect(0, 0, this.getWidth(), this.getHeight());
      
      // update drawing
      g.setColor(Color.orange);
      g.fillOval(xPos, yPos, 100, 100);
    }
  }
}
