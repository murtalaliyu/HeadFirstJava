import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.WEST;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class TwoButtons {

  private JFrame frame;
  private JLabel label;
  private static int labelButtonClickCount = 0;

  private int x = 20;
  private int y = 50;

  public static void main(String[] args) {
    new TwoButtons().go();
  }

  public void go() {
    // initialize the frame frame
    frame = new JFrame();
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

    // initialize the label
    label = new JLabel("I'm a label");

    // make a label button
    JButton labelButton = new JButton("Change label");
    labelButton.addActionListener(e -> {
      labelButtonClickCount++;
      label.setText("Ouch! [" + labelButtonClickCount + "]");
    });

    // make a color button
    JButton colorButton = new JButton("Change circle");
    colorButton.addActionListener(e -> {
      for (int i = 0; i < 50; i += 5) {
        frame.repaint();
        x += i;
        y += i;
      }
    });

    // make a drawPanel
    // MyDrawPanel drawPanel = new MyDrawPanel();
    MyDrawPanelInner drawPanel = new MyDrawPanelInner();

    // arrange frame content
    frame.getContentPane().add(SOUTH, colorButton);
    frame.getContentPane().add(CENTER, drawPanel);
    frame.getContentPane().add(EAST, labelButton);
    frame.getContentPane().add(WEST, label);

    // show frame
    frame.setSize(500, 400);
    frame.setVisible(true);
  }


  class MyDrawPanelInner extends JPanel {
    public void paintComponent(Graphics g) {
      g.setColor(Color.orange);
      g.fillOval(x, y, 100, 100);
    }
  }

  /**
   * The two ActionListener inner-classes below can be replaced with a lambda function
   * since ActionListener is a functional interface
   */

  class ColorButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      // Auto-generated method stub
      frame.repaint();
    }
    
  }

  class LabelButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      // Auto-generated method stub
      labelButtonClickCount++;
      label.setText("Ouch! [" + labelButtonClickCount + "]");
    }

  }
}
