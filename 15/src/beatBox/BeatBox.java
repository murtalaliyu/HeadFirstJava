package beatBox;

/**
 * TODO: this.tempo is buggy (serialization.read...)
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static javax.swing.BoxLayout.Y_AXIS;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.WEST;
import static javax.sound.midi.Sequence.PPQ;
import static javax.sound.midi.ShortMessage.CONTROL_CHANGE;
import static javax.sound.midi.ShortMessage.PROGRAM_CHANGE;
import static javax.sound.midi.ShortMessage.NOTE_ON;
import static javax.sound.midi.ShortMessage.NOTE_OFF;

public class BeatBox {

	private ArrayList<JCheckBox> checkboxList;
	private Sequencer sequencer;
	private Sequence sequence;
	private Track track;
	private JFrame frame;
	private float tempo;
	
	private int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};
	private String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal",
			"Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap",
			"Low-mid Tom", "High Agogo", "Open Hi Conga"};
	
	public static void main(String[] args) {
		new BeatBox().buildGUI();
	}
	
	public void buildGUI() {
		// create frame
		frame = new JFrame("Cyber BeatBox");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// create border layout panel
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// create box layout
		Box buttonBox = new Box(Y_AXIS);
		
		// create start, stop, up, and down tempo buttons, 
		// and add them to button box layout
		JButton start = new JButton("Start");
		start.addActionListener(e -> buildTrackAndStart());
		buttonBox.add(start);
		
		JButton stop = new JButton("Stop");
		stop.addActionListener(e -> sequencer.stop());
		buttonBox.add(stop);
		
		JButton upTempo = new JButton("Tempo Up");
		upTempo.addActionListener(e -> changeTempo(1.03f));
		buttonBox.add(upTempo);
		
		JButton downTempo = new JButton("Tempo Down");
		downTempo.addActionListener(e -> changeTempo(0.97f));
		buttonBox.add(downTempo);
		
		JButton refresh = new JButton("Refresh");
		refresh.addActionListener(e -> refreshGrid());
		buttonBox.add(refresh);
		
		JButton serializeIt = new JButton("serializeIt");
		serializeIt.addActionListener(e -> save());
		buttonBox.add(serializeIt);
		
		JButton restore = new JButton("restore");
		restore.addActionListener(e -> open());
		buttonBox.add(restore);
		
		// generate instrument names box layout
		Box nameBox = new Box(Y_AXIS);
		for (String instrumentName : instrumentNames) {
			JLabel instrumentLabel = new JLabel(instrumentName);
			instrumentLabel.setBorder(BorderFactory.createEmptyBorder(4, 1, 4, 1));
			nameBox.add(instrumentLabel);
		}
		
		// add button & name boxes to background
		background.add(WEST, nameBox);
		background.add(EAST, buttonBox);
		
		// add background to frame
		frame.getContentPane().add(background);
		
		// make a grid of checkboxes
		GridLayout grid = new GridLayout(16, 16);
		grid.setVgap(1);
		grid.setHgap(2);
		
		JPanel mainPanel = new JPanel(grid);
		background.add(CENTER, mainPanel);
		
		checkboxList = new ArrayList<>();
		for (int i = 0; i < 256; i++) {
			JCheckBox c = new JCheckBox();
			c.setSelected(false);
			checkboxList.add(c);
			mainPanel.add(c);
		}
		
		setUpMidi();
		
		// show frame
		frame.setBounds(50, 50, 300, 300);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * Stop the playback and uncheck all check boxes
	 */
	private void refreshGrid() {
		if (sequencer.isRunning()) {
			sequencer.setTempoFactor(1);
			sequencer.stop();
		}
		for (int i = 0; i < 256; i++) {
			JCheckBox c = checkboxList.get(i);
			if (c.isSelected()) {
				c.setSelected(false);
			}
		}
	}
	
	/**
	 * The usual MIDI setup stuff for getting the Sequencer, Sequence, and the Track
	 */
	private void setUpMidi() {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequence = new Sequence(PPQ, 4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(120);
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This is where it all happens! Where we turn check box state into MIDI events and add them to the Track
	 */
	private void buildTrackAndStart() {
		// make a 16-element array to hold the values for one instrument, across all 16 beats.
		// if the instrument is supposed to play on that beat, the value at that element will be the key.
		// if that instrument is NOT supposed to play on that beat, put in a zero.
		int[] trackList;
		
		// make a fresh track
		sequence.deleteTrack(track);
		track = sequence.createTrack();
		
		// for each of the 16 ROWS (instruments), set the "key" that represents which instrument this is
		for (int i = 0; i < 16; i++) {
			trackList = new int[16];
			int key = instruments[i];
			
			// for each of the BEATS in this row; if the check box at this beat is selected, put the key 
			// value in this slot in the array. Otherwise, the instrument is NOT supposed to play at this beat (0)
			for (int j = 0; j < 16; j++) {
				if (checkboxList.get(j + 16 * i).isSelected()) {
					trackList[j] = key;
				} else {
					trackList[j] = 0;
				}
			}
			
			// for all 16 beats for this instrument, make events and add them to the track
			makeTracks(trackList);
			track.add(makeEvent(CONTROL_CHANGE, 1, 127, 0, 16));
		}
		
		// we always want to make sure that there IS an event at beat 16 (it goes from 0 to 15).
		// otherwise, the BeatBox might not go the full 16 beats before it starts over
		track.add(makeEvent(PROGRAM_CHANGE, 9, 1, 0, 15));
		
		try {
			sequencer.setSequence(sequence);
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
			sequencer.setTempoInBPM(120);
			sequencer.start();
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The Tempo Factor scales the sequencer's tempo by the factor provided, slowing the beat down or speeding it up
	 * @param tempoMultiplier
	 */
	private void changeTempo(float tempoMultiplier) {
		float tempoFactor = sequencer.getTempoFactor();
		this.tempo = tempoFactor * tempoMultiplier;
		sequencer.setTempoFactor(this.tempo);
	}
	
	/**
	 * This makes events for one instrument at a time, for all 16 beats. So it might get an int[] for the Bass drum,
	 * and each index in the array will hold either the key of that instrument or a zero. If it's a zero,
	 * the instrument isn't supposed to play at that beat. Otherwise, make an event and add it to the track
	 * @param list
	 */
	private void makeTracks(int[] list) {
		for (int i = 0; i < 16; i++) {
			int key = list[i];
			if (list[i] != 0) {
				track.add(makeEvent(NOTE_ON, 9, key, 100, i));
				track.add(makeEvent(NOTE_OFF, 9, key, 100, i+1));
			}
		}
	}
	
	/**
	 * This is the utility method for making events
	 */
	public static MidiEvent makeEvent(int command, int channel, int data1, int data2, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage sm = new ShortMessage();
			sm.setMessage(command, channel, data1, data2);
			event = new MidiEvent(sm, tick);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
		return event;
	}
	
	/**
	 * Call when user clicks the 'serializeIt' button.
	 * will open the file chooser and allow the user to name
	 * the resulting file.
	 */
	private void save() {
		JFileChooser fileSave = new JFileChooser();
		fileSave.showSaveDialog(this.frame);
		writeFile(fileSave.getSelectedFile());
	}
	
	/**
	 * We can call this from a lambda expression when we add an
	 * ActionListener to the serializeIt button, or create an
	 * ActionListener inner class that calls this.
	 */
	private void writeFile(File file) {
		boolean[] checkboxState = new boolean[256];
		for (int i = 0; i < 256; i++) {
			JCheckBox check = this.checkboxList.get(i);
			if (check.isSelected()) checkboxState[i] = true;
		}
		
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
			os.writeObject(checkboxState);
			os.writeObject(this.tempo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Bring up a file dialog box
	 * let the user navigate to and choose a beat pattern file to open
	 */
	private void open() {
		JFileChooser fileOpen = new JFileChooser();
		fileOpen.showOpenDialog(this.frame);
		readFile(fileOpen.getSelectedFile());
	}
	
	/**
	 * This is pretty much the save, writeFile(), in reverse...read the boolean
	 * array and use it to restore the state of the GUI checkboxes and tempo.
	 * It all happens when the user hits the "restore" button.
	 */
	private void readFile(File file) {
		boolean[] checkboxState = null;
		try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
			checkboxState = (boolean[]) is.readObject();
			this.tempo = (float) is.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < 256; i++) {
			JCheckBox check = this.checkboxList.get(i);
			check.setSelected(checkboxState[i]);
		}
		sequencer.setTempoFactor(this.tempo);
		System.out.println("loaded: " + file.getName() + ", tempo: " + this.tempo);
		
		sequencer.stop();
//		buildTrackAndStart();
	}

}
