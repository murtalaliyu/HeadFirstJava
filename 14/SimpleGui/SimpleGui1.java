import javax.swing.JButton;
import javax.swing.JFrame;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class SimpleGui1 {

  public static void main(String[] args) {
    // Make a frame and a button
    JFrame frame = new JFrame();
    JButton button = new JButton("click me");

    // Add an ActionListener to this button
    button.addActionListener(e -> {
      // do something when an action occurs
      System.out.println("button action: " + button.getActionCommand());
      new SimpleGui1().changeIt(button);
    });

    // This line makes the program quit as soon as you close the window
    // (if you leave this out it will just sit there on the screen forever)
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

    // Add the button to the frame's content pane
    frame.getContentPane().add(button);
    
    // Give the frame a size, in pixels
    frame.setSize(300, 300);

    // Finally, make it visible. If you forget this step, you won't see anything
    // when the code is run
    frame.setVisible(true);
  }

  public void changeIt(JButton button) {
    button.setText("I've been clicked!");
  }

}
