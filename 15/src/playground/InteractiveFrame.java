package playground;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.SOUTH;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InteractiveFrame {
	
	private JFrame frame;
	private int textFieldCount = 0;

	public static void main(String[] args) {
		new InteractiveFrame().go();
	}
	
	public void go() {
		// make a frame
		frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(700, 600);
		
		// register action listener on frame
		frame.addMouseListener(new FrameListener());
		
		// create button, add action listener, and add to frame
		JButton button = new JButton("Click me!!!");
		button.addActionListener(e -> {
			System.out.println("you clicked:\n" + e + "\n");
		});
		frame.getContentPane().add(EAST, button);
		
		// create text field and add to frame
		JTextField textField = new JTextField("Is this a placeholder or real text???");
		textField.addActionListener(e -> {
			textFieldCount++;
			System.out.println("something is happening in this textField [" + textFieldCount + "]\n" + e + "\n");
		});
		frame.getContentPane().add(SOUTH, textField);
	}
	
	// -------------- INNER CLASSES --------------------------
	
	class FrameListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			System.out.println("you clicked:\n" + e + "\n");
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	}
}
