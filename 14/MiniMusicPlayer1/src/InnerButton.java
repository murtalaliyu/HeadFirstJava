import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import static java.awt.BorderLayout.SOUTH;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

class InnerButton {

	private JButton button;
	
	public static void main(String[] args) {
		InnerButton gui = new InnerButton();
		gui.go();
	}
	
	public void go() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		button = new JButton("A");
		button.addActionListener(new ButtonListener());

		frame.getContentPane().add(SOUTH, button);
		frame.setSize(200, 100);
		frame.setVisible(true);
	}
	
	// --------------------------- INNER CLASSES -------------------------------------------
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (button.getText().equals("A")) {
				button.setText("B");
			} else {
				button.setText("A");
			}
		}
		
	}
}
