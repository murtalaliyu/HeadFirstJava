import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Graphics implements ActionListener {

  private JFrame frame;
  
  public static void main(String[] args) {
    new Graphics().go();
  }

  public void go() {
    frame = new JFrame();
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

    JButton button = new JButton("Change colors");
    button.addActionListener(this);
    frame.getContentPane().add(SOUTH, button);

    MyDrawPanel drawPanel = new MyDrawPanel();
    frame.getContentPane().add(CENTER, drawPanel);
    
    frame.setSize(700, 700);
    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    frame.repaint();
    System.out.println("\nevent just occured from '" + e.getSource() + "'");
  }
}
