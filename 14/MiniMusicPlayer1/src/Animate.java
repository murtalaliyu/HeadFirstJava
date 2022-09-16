import java.awt.Graphics;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import static java.awt.Color.blue;
import static java.awt.Color.red;
import static java.awt.Color.white;
import static java.awt.Color.yellow;
import static java.awt.Frame.MAXIMIZED_BOTH;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Animate {

	private JFrame frame;
	private int frameWidth = 1000;
	private int frameHeight = 500;
	private int rectWidth = 500;
	private int rectHeight = 250;
	private int x = 1;
	private int y = 1;
	
	public static void main(String[] args) {
		new Animate().go();
	}
	
	public void go() {
		MyDrawPanel drawP = new MyDrawPanel();
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.getContentPane().add(drawP);
		frame.setSize(frameWidth, frameHeight);
		frame.setVisible(true);
		frame.setExtendedState(MAXIMIZED_BOTH);
		
		for (int i = 0; i < 124; i++, y++, x++) {
			x++;
			drawP.repaint();
			
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	// ------------------------- INNER CLASS --------------------------------
	
	class MyDrawPanel extends JPanel {
		public void paintComponent(Graphics g) {
			int width = frame.getWidth();
			int height = frame.getHeight();
			g.setColor(blue);
			g.fillRect(0, 0, width, height);
			g.setColor(red);
			g.fillRect((width/2)-(rectWidth/2)+x, (height/2)-(rectHeight/2)+y,rectWidth-x*2,rectHeight-y*2);
		}
	}
}
