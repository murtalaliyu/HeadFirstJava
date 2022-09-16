import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class SimpleGui2 implements ActionListener {

  private JButton button;

  public static void main(String[] args) {
    SimpleGui2 gui = new SimpleGui2();
    gui.go();
  }

  public void go() {
    JFrame frame = new JFrame();
    button = new JButton("click me");

    // Register your interest with the button.
    // (this says to the button, "Add me to your list of listeners.")
    // The argument you pass MUST be an object from a class that implements ActionListener!
    button.addActionListener(this);

    // Add our button to the frame and make the frame visible
    frame.getContentPane().add(button);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setSize(300, 300);
    frame.setVisible(true);
  }

  // Implement the ActionListener interface's actionPerformed() method.
  // This is the actual event-handling method
  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    button.setText("I've been clicked!");
    System.out.println("ActionEvent:\n" + e);
  }
}
