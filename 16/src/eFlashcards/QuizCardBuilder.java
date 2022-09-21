package eFlashcards;

import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import static java.awt.BorderLayout.CENTER;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class QuizCardBuilder {
	
	private ArrayList<QuizCard> cardList = new ArrayList<>();
	private JTextArea question;
	private JTextArea answer;
	private JFrame frame;
	
	public static void main(String[] args) {
		new QuizCardBuilder().go();
	}

	/**
	 * Builds and displays the GUI, including making
	 * and registering event listeners.
	 */
	public void go() {
		// build and display GUI.
		this.frame = new JFrame("Quiz Card Builder"); //$NON-NLS-1$
		JPanel mainPanel = new JPanel();
		Font bigFont = new Font("sanserif", Font.BOLD, 24); //$NON-NLS-1$
		
		this.question = createTextArea(bigFont);
		JScrollPane qScroller = createScroller(this.question);
		this.answer = createTextArea(bigFont);
		JScrollPane aScroller = createScroller(this.answer);
		
		mainPanel.add(new JLabel("Question")); //$NON-NLS-1$
		mainPanel.add(qScroller);
		mainPanel.add(new JLabel("Answer")); //$NON-NLS-1$
		mainPanel.add(aScroller);
		
		JButton nextButton = new JButton("Next Card"); //$NON-NLS-1$
		nextButton.addActionListener(e -> nextCard());
		mainPanel.add(nextButton);
		
		// create menu bar stuff
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File"); //$NON-NLS-1$
		
		JMenuItem newMenuItem = new JMenuItem("New"); //$NON-NLS-1$
		newMenuItem.addActionListener(e -> clearAll());
		
		JMenuItem saveMenuItem = new JMenuItem("Save"); //$NON-NLS-1$
		saveMenuItem.addActionListener(e -> saveCard());
		
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		menuBar.add(fileMenu);
		
		this.frame.setJMenuBar(menuBar);
		this.frame.getContentPane().add(CENTER, mainPanel);
		this.frame.setSize(500, 600);
		this.frame.setVisible(true);
	}

	/**
	 * Helper method
	 * @param textArea
	 * @return
	 */
	public static JScrollPane createScroller(JTextArea textArea) {
		JScrollPane scroller = new JScrollPane(textArea);
		scroller.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		return scroller;
	}

	/**
	 * Helper method
	 * @param font
	 * @return
	 */
	public static JTextArea createTextArea(Font font) {
		JTextArea textArea = new JTextArea(6, 20);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(font);
		return textArea;
	}

	/**
	 * Call when user hits 'Next Card' button;
	 * means the user wants to store that card
	 * in the list and start a new card.
	 */
	private void nextCard() {
		// add the current card to the list
		// and clear the text areas
		QuizCard card = new QuizCard(this.question.getText(), this.answer.getText());
		this.cardList.add(card);
		clearCard();
	}
	
	/**
	 * Call when user chooses 'Save' from the File menu;
	 * means the user wants to save all the cards in the 
	 * current list as a 'set' (like, Quantum Mechanics Set,
	 * Hollywood Trivia, Java Rules, etc.).
	 */
	private void saveCard() {
		// bring up a file dialog box
		// let the user name and save the set
		QuizCard card = new QuizCard(this.question.getText(), this.answer.getText());
		this.cardList.add(card);
		
		JFileChooser fileSave = new JFileChooser();
		fileSave.showSaveDialog(this.frame);
		saveFile(fileSave.getSelectedFile());
	}
	
	/**
	 * Helper method
	 */
	private void clearAll() {
		this.cardList.clear();
		clearCard();
	}
	
	/**
	 * Will need to clear the screen when the user
	 * chooses 'New' from the File menu or moves
	 * to the next card.
	 */
	private void clearCard() {
		// clear out the text areas
		this.question.setText(""); //$NON-NLS-1$
		this.answer.setText(""); //$NON-NLS-1$
		this.question.requestFocus();
	}
	
	/**
	 * Called by the SaveMenuListener;
	 * does the actual file writing.
	 */
	private void saveFile(File file) {
		// iterate through the list of cards and write
		// each one out to a text file in a parseable way
		// (in other words, with clear separations between parts)
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			for (QuizCard card : this.cardList) writer.write(card.getQuestion() + "/" + card.getAnswer() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (IOException e) {
			System.out.println("Couldn't write the cardList out:"); //$NON-NLS-1$
			e.printStackTrace();
		}
	}
	
}
