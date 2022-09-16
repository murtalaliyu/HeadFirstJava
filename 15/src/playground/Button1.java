package playground;

import javax.swing.JFrame;
import java.awt.Font;

import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.WEST;
import static java.awt.BorderLayout.CENTER;
import static java.awt.Font.BOLD;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import javax.swing.JButton;

public class Button1 {

	public static void main(String[] args) {
		new Button1().north();
		new Button1().bigFont();
		new Button1().center();
	}
	
	// add a button to north region
	public void north() {
		JFrame frame = new JFrame("Border Layout");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JButton button = new JButton("There is no spoon...");
		frame.getContentPane().add(NORTH, button);
		
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	
	// make button font bigger
	public void bigFont() {
		JFrame frame = new JFrame("Border Layout");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JButton button = new JButton("Click This!");
		Font bigFont = new Font("serif", BOLD, 28);
		button.setFont(bigFont);
		frame.getContentPane().add(NORTH, button);
		
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	
	// look at center
	public void center() {
		JFrame frame = new JFrame("Border Layout");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JButton east = new JButton("East");
		JButton west = new JButton("West");
		JButton north = new JButton("North");
		JButton south = new JButton("South");
		JButton center = new JButton("Center");
		
		frame.getContentPane().add(EAST, east);
		frame.getContentPane().add(WEST, west);
		frame.getContentPane().add(NORTH, north);
		frame.getContentPane().add(SOUTH, south);
		frame.getContentPane().add(CENTER, center);
		
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
}
