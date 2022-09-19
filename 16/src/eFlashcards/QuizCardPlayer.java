package eFlashcards;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import static java.awt.BorderLayout.CENTER;

public class QuizCardPlayer {
	
	private ArrayList<QuizCard> cardList;
	private int currentCardIndex;
	private QuizCard currentCard;
	private JTextArea display;
	private JFrame frame;
	private JButton nextButton;
	private boolean isShowAnswer;
	private BufferedReader reader;
	
	public static void main(String[] args) {
		QuizCardPlayer reader = new QuizCardPlayer();
		reader.go();
	}

	private void go() {
		// build and display GUI
		this.frame = new JFrame("Quiz Card Player"); //$NON-NLS-1$
		JPanel mainPanel = new JPanel();
		Font bigFont = new Font("sanserif", Font.BOLD, 24); //$NON-NLS-1$
		
		this.display = new JTextArea(10, 20);
		this.display.setFont(bigFont);
		this.display.setLineWrap(true);
		this.display.setEditable(false);
		
		JScrollPane scroller = QuizCardBuilder.createScroller(this.display);
		mainPanel.add(scroller);
		
		this.nextButton = new JButton("Show Question"); //$NON-NLS-1$
		this.nextButton.addActionListener(e -> nextCard());
		mainPanel.add(this.nextButton);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File"); //$NON-NLS-1$
		JMenuItem loadMenuItem = new JMenuItem("Load card set"); //$NON-NLS-1$
		loadMenuItem.addActionListener(e -> open());
		fileMenu.add(loadMenuItem);
		menuBar.add(fileMenu);
		
		this.frame.setJMenuBar(menuBar);
		this.frame.getContentPane().add(CENTER, mainPanel);
		this.frame.setSize(500, 500);
		this.frame.setVisible(true);
	}
	
	/**
	 * Check the isShowAnswer boolean flag to 
	 * see if they're currently viewing a question 
	 * or an answer, and do the appropriate thing
	 * depending on the answer.
	 */
	private void nextCard() {
		// if this is a question, show the answer, otherwise show
		// next question set a flag for whether we're viewing a 
		// question or answer
		if (this.isShowAnswer) {
			// show the answer because they've seen the question
			this.display.setText(this.currentCard.getAnswer());
			this.nextButton.setText("Next Card"); //$NON-NLS-1$
			this.isShowAnswer = false;
		} else {
			if (this.currentCardIndex < this.cardList.size()) {
				showNextCard();
			} else {
				// there are no more cards!
				this.display.setText("That was the last card"); //$NON-NLS-1$
				this.nextButton.setEnabled(false);
			}
		}
	}
	
	/**
	 * Helper method
	 */
	private void showNextCard() {
		this.currentCard = this.cardList.get(this.currentCardIndex);
		this.currentCardIndex++;
		this.display.setText(this.currentCard.getQuestion());
		this.nextButton.setText("Show Answer"); //$NON-NLS-1$
		this.isShowAnswer = true;
	}

	/**
	 * Bring up a file dialog box
	 * let the user navigate to and choose a card set to open
	 */
	private void open() {
		JFileChooser fileOpen = new JFileChooser();
		fileOpen.showOpenDialog(this.frame);
		loadFile(fileOpen.getSelectedFile());
	}
	
	/**
	 * Make a BufferedReader chained
	 * to a new FileReader, giving the
	 * FileReader the File object the user
	 * chose from the open file dialog.
	 */
	private void loadFile(File file) {
		// must build an ArrayList of cards, by reading them from
		// a text file called from the OpenMenuListener event handler,
		// reads the file one line at a time and tells the makeCard()
		// method to make a new card out of the line (one line in the
		//file holds both the question and answer, separated by a "/")
		this.cardList = new ArrayList<>();
		this.currentCardIndex = 0;
		
		try {
			
			this.reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = this.reader.readLine()) != null) makeCard(line);
			this.reader.close();
			
		} catch (IOException e) {
			System.out.println("Couldn't write the cardList out: " + e.getMessage()); //$NON-NLS-1$
		}
		
		// now time to start, show the first card
		showNextCard();
	}

	/**
	 * Each line of text corresponds to a single
	 * flashcard, but we have to parse out the
	 * question and answer as separate pieces. We
	 * use the String split() method to break the
	 * line into two tokens (one for the question
	 * and one for the answer).
	 */
	private void makeCard(String lineToParse) {
		String[] result = lineToParse.split("/"); //$NON-NLS-1$
		QuizCard card = new QuizCard(result[0], result[1]);
		this.cardList.add(card);
		System.out.println("made a card"); //$NON-NLS-1$
	}
}
