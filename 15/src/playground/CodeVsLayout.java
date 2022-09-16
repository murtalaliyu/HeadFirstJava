package playground;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.CENTER;

public class CodeVsLayout {
	
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JButton button = new JButton("tesuji");
	private JButton buttonTwo = new JButton("watari");

	public static void main(String[] args) {
		CodeVsLayout gui = new CodeVsLayout();
		gui.pre();
//		gui.A();
//		gui.B();
//		gui.C();
//		gui.D();
		gui.E();
		gui.post();
	}
	
	void pre() {
		panel.setBackground(Color.darkGray);
	}
	
	void post() {
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	
	void A() {
		panel.add(button);
		frame.getContentPane().add(NORTH, buttonTwo);
		frame.getContentPane().add(EAST, panel);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}

	void B() {
		panel.add(buttonTwo);
		frame.getContentPane().add(CENTER, button);
		frame.getContentPane().add(EAST, panel);
	}

	void C() {
		panel.add(buttonTwo);
		frame.getContentPane().add(CENTER, button);
	}
	
	void D() {
		frame.getContentPane().add(NORTH, panel);
		panel.add(buttonTwo);
		frame.getContentPane().add(CENTER, button);
	}
	
	void E() {
		frame.getContentPane().add(SOUTH, panel);
		panel.add(buttonTwo);
		frame.getContentPane().add(NORTH, button);
	}
	
}
