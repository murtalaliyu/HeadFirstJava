package playground;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import static java.awt.BorderLayout.EAST;
import static java.awt.Color.darkGray;

import static javax.swing.BoxLayout.Y_AXIS;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ListSelectionModel.SINGLE_SELECTION;

public class Panel1 {

	public static void main(String[] args) {
		Panel1 gui = new Panel1();
//		gui.addPanelToEastRegion();
//		gui.addButtonToPanel();
//		gui.addTwoButtonsToPanel();
//		gui.addThreeButtonsToPanel();
//		gui.addLabelAndTextField();
//		gui.showTextArea();
//		gui.showCheckBox();
		gui.showList();
	}
	
	public void showList() {
		// make a frame and panel
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		// create list of entries
		String[] listEntries = {"alpha", "beta", "gamma", "delta", "epsilon", "zeta", "eta", "theta"};
		JList<String> list = new JList<>(listEntries);
//		list.setVisibleRowCount(4);
		list.setSelectionMode(SINGLE_SELECTION);
		list.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {	// you'll get the event TWICE if you don't put this if test
				String selection = list.getSelectedValue();
				System.out.println(selection);
			}
		});
		
		// create scroller and add list to it
		JScrollPane scroller = new JScrollPane(list);
		scroller.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		
		// add scroller to panel and panel to frame
		panel.add(scroller);
		frame.getContentPane().add(panel);
	
		// show frame
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(200, 200);
		frame.setVisible(true);
	}
	
	public void showCheckBox() {
		JFrame frame = new JFrame();
		JCheckBox checkbox = new JCheckBox("Goes to 11");
		
		checkbox.addItemListener(e -> {
			String onOrOff = "off";
			if (checkbox.isSelected()) {
				onOrOff = "on";
			}
			System.out.println("Check box is " + onOrOff);
		});
		
		frame.getContentPane().add(checkbox);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	
	/*
	 * You give the text area to the scroll pane,
	 * then add the scroll pane to the panel.
	 * You don't add the text area directly to the panel
	 */
	public void showTextArea() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JTextArea textArea = new JTextArea(10, 20);
		JScrollPane scroller = new JScrollPane(textArea);
		JButton buttonNewLine = new JButton("click for new line");
		JButton buttonAppend = new JButton("click for just append");
		
		buttonNewLine.addActionListener(e -> textArea.append("button clicked\n"));
		buttonAppend.addActionListener(e -> textArea.append("button clicked"));
		
		textArea.setLineWrap(true);
		
		scroller.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		
		panel.setLayout(new BoxLayout(panel, Y_AXIS));
		panel.add(scroller);
		panel.add(buttonNewLine);
		panel.add(buttonAppend);
		
//		textArea.setText("Not all who are lost are wandering");
//		textArea.append("button clicked");
//		textArea.selectAll();
		textArea.requestFocus();
		
		frame.getContentPane().add(panel);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
	
	public void addLabelAndTextField() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Dog's first name:");
		JTextField textField = new JTextField(20);
		
		textField.addKeyListener(new MyKeyListener());
		
		panel.add(label);
		panel.add(textField);
		
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
	
	public void addPanelToEastRegion() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		panel.setBackground(darkGray);
		
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.getContentPane().add(EAST, panel);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	
	public void addButtonToPanel() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JButton button = new JButton("shock me");
		
		panel.setBackground(darkGray);
		panel.add(button);
		
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.getContentPane().add(EAST, panel);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	
	/*
	 * The default layout for Panel is Flow, so we need to 
	 * manually change it's layout to Box for vertical stacking
	 */
	public void addTwoButtonsToPanel() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JButton button = new JButton("shock me");
		JButton buttonTwo = new JButton("bliss");
		
		panel.setLayout(new BoxLayout(panel, Y_AXIS));
		panel.setBackground(darkGray);
		panel.add(button);
		panel.add(buttonTwo);
		
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.getContentPane().add(EAST, panel);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	
	public void addThreeButtonsToPanel() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JButton button = new JButton("shock me");
		JButton buttonTwo = new JButton("bliss");
		JButton buttonThree = new JButton("huh?");
		
		panel.setBackground(darkGray);
		panel.add(button);
		panel.add(buttonTwo);
		panel.add(buttonThree);
		
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.getContentPane().add(EAST, panel);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	
	// -------------- INNER CLASSES ----------------------
	
	class MyKeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// Auto-generated method stub
			System.out.println("You pressed: [" + e.getKeyChar() + "]\n");
		}
		
	}
	
}
