package beatBox;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import static javax.sound.midi.ShortMessage.PROGRAM_CHANGE;
import static javax.sound.midi.ShortMessage.CONTROL_CHANGE;
import static javax.sound.midi.ShortMessage.NOTE_OFF;
import static javax.sound.midi.ShortMessage.NOTE_ON;

public class BeatBoxFinal {
	
	private JList<String> incomingList;
	private JTextArea userMessage;
	private ArrayList<JCheckBox> checkboxList;
	
	private Vector<String> listVector = new Vector<>();
	private HashMap<String, boolean[]> otherSeqsMap = new HashMap<>();
	
	private String username;
	private int nextNum;
	
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	private Sequencer sequencer;
	private Sequence sequence;
	private Track track;
	
	String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal",
			"Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap",
			"Low-mid Tom", "High Agogo", "Open Hi Conga"};
	int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};
	
	public static void main(String[] args) {
		new BeatBoxFinal().startUp(args[0]);;
	}
	
	public void startUp(String name) {
		username = name;
		
		// open connection to the server
		try {
			
			// We're using Sockets here instead of Channels
			// because they work better with Object Input/Output streams.
			Socket socket = new Socket("127.0.0.1", 4242);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			
			ExecutorService executor = Executors.newSingleThreadExecutor();
			executor.submit(new RemoteReader());
			
		} catch (Exception ex) {
			System.out.println("Couldn't connect - you'll have to play alone.");
		}
		
		setUpMidi();
		buildGUI();
	}
	
	public void buildGUI() {
		JFrame frame = new JFrame("Cyber BeatBox");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		Box buttonBox = new Box(BoxLayout.Y_AXIS);
		
		// arrange buttons
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
		
		JButton sendIt = new JButton("sendIt");
		sendIt.addActionListener(e -> sendMessageAndTracks());
		buttonBox.add(sendIt);
		
		userMessage = new JTextArea();
		userMessage.setLineWrap(true);
		userMessage.setWrapStyleWord(true);
		JScrollPane messageScroller = new JScrollPane(userMessage);
		buttonBox.add(messageScroller);
		
		// this is where incoming messages are displayed.
		// Only instead of a normal chat where you just LOOK
		// at the messages, in this app you can SELECT a message
		// from the list to load and play the attached beat pattern.
		incomingList = new JList<>();
		incomingList.addListSelectionListener(new MyListSelectionListener());
		incomingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane theList = new JScrollPane(incomingList);
		buttonBox.add(theList);
		incomingList.setListData(listVector);
		
		// add name box content
		Box nameBox = new Box(BoxLayout.Y_AXIS);
		for (String instrumentName : instrumentNames) {
			JLabel instrumentLabel = new JLabel(instrumentName);
			instrumentLabel.setBorder(BorderFactory.createEmptyBorder(4, 1, 4, 1));
			nameBox.add(instrumentLabel);
		}
		
		background.add(BorderLayout.EAST, buttonBox);
		background.add(BorderLayout.WEST, nameBox);
		frame.getContentPane().add(background);
		
		GridLayout grid = new GridLayout(16, 16);
		grid.setVgap(1);
		grid.setHgap(2);
		
		JPanel mainPanel = new JPanel(grid);
		background.add(BorderLayout.CENTER, mainPanel);
		
		checkboxList = new ArrayList<>();
		for (int i = 0; i < 256; i++) {
			JCheckBox c = new JCheckBox();
			c.setSelected(false);
			checkboxList.add(c);
			mainPanel.add(c);
		}
		
		frame.setBounds(50, 50, 300, 300);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void setUpMidi() {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequence = new Sequence(Sequence.PPQ, 4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(120);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void buildTrackAndStart() {
		ArrayList<Integer> trackList;	// this will hold the instruments for each
		
		sequence.deleteTrack(track);
		track = sequence.createTrack();
		
		for (int i = 0; i < 16; i++) {
			trackList = new ArrayList<>();
			int key = instruments[i];
			
			for (int j = 0; j < 16; j++) {
				JCheckBox jc = checkboxList.get(j + (16*i));
				if (jc.isSelected()) {
					trackList.add(key);
				} else {
					trackList.add(null);	// because this slot should be empty in the track
				}
			}
			makeTracks(trackList);
			track.add(makeEvent(CONTROL_CHANGE, 1, 127, 0, 16));
		}
		
		track.add(makeEvent(PROGRAM_CHANGE, 9, 1, 0, 15));	// so we always go to 16 beats
		try {
			sequencer.setSequence(sequence);
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
			sequencer.setTempoInBPM(120);
			sequencer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void changeTempo(float tempoMultiplier) {
		float tempoFactor = sequencer.getTempoFactor();
		sequencer.setTempoFactor(tempoFactor * tempoMultiplier);
	}
	
	/**
	 * We serialize two objects (the String message and the beat pattern)
	 * and write those two objects to the socket output stream (to the server).
	 */
	private void sendMessageAndTracks() {
		boolean[] checkboxState = new boolean[256];
		for (int i = 0; i < 256; i++) {
			JCheckBox check = checkboxList.get(i);
			if (check.isSelected()) {
				checkboxState[i] = true;
			}
		}
		
		try {
			out.writeObject(username + nextNum++ + ": " + userMessage.getText());
			out.writeObject(checkboxState);
		} catch (IOException e) {
			System.out.println("Terribly sorry. Could not send it to the server.");
			e.printStackTrace();
		}
		userMessage.setText("");
	}
	
	/**
	 * This method is called when the user selects something from the 
	 * list. We IMMEDIATELY change the pattern to the one they selected.
	 */
	private void changeSequence(boolean[] checkboxState) {
		for (int i = 0; i < 256; i++) {
			JCheckBox check = checkboxList.get(i);
			check.setSelected(checkboxState[i]);
		}
	}
	
	public void makeTracks(ArrayList<Integer> list) {
		for (int i = 0; i < 16; i++) {
			Integer instrumentKey = list.get(i);
			if (instrumentKey != null) {
				track.add(makeEvent(NOTE_ON, 9, instrumentKey, 100, i));
				track.add(makeEvent(NOTE_OFF, 9, instrumentKey, 100, i+1));
			}
		}
	}
	
	public static MidiEvent makeEvent(int cmd, int chn1, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage msg = new ShortMessage();
			msg.setMessage(cmd, chn1, one, two);
			event = new MidiEvent(msg, tick);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return event;
	}
	
	// --------------------------- INNER CLASSES ---------------------------------------
	
	/**
	 * A ListSelectionListener that tells us when the user made a selection on the list
	 * of messages. When the user selects a message, we IMMEDIATELY load the associated
	 * beat pattern (it's in the HashMap called otherSeqsMap) and start playing it.
	 * There's some if tests because of little quirky things about getting ListSelectionEvents.
	 *
	 */
	public class MyListSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent lse) {
			// Auto-generated method stub
			if (!lse.getValueIsAdjusting()) {
				String selected = incomingList.getSelectedValue();
				if (selected != null) {
					// now go to the map, and change the sequence
					boolean[] selectedState = otherSeqsMap.get(selected);
					changeSequence(selectedState);
					sequencer.stop();
					buildTrackAndStart();
				}
			}
		}
		
	}
	
	/**
	 * This is the thread job - it reads in data from the server. In this code, "data" will
	 * always be two serialized objects: the String message and the beat pattern (a boolean
	 * array of checkbox state values).
	 * 
	 * When a message comes in, we read (deserialize) the two objects (the message and the
	 * array of boolean checkbox state values), which we want to add to the JList component.
	 * 
	 * Adding to a JList is a two-step thing: you keep a Vector of the lists data (Vector is
	 * an old-fashioned ArrayList), and then tell the JList to use that Vector as it's source
	 * for what to display in the list.
	 */
	public class RemoteReader implements Runnable {

		@Override
		public void run() {
			// Auto-generated method stub
			try {
				
				Object obj;
				while ((obj = in.readObject()) != null) {
					System.out.println("got an object from server");
					System.out.println(obj.getClass());
					
					String nameToShow = (String) obj;
					boolean[] checkboxState = (boolean[]) in.readObject();
					otherSeqsMap.put(nameToShow, checkboxState);
					
					listVector.add(nameToShow);
					incomingList.setListData(listVector);
				}
				
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
