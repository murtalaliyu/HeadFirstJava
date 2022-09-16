import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.JFrame;
import javax.swing.JPanel;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static javax.sound.midi.Sequence.PPQ;
import static javax.sound.midi.ShortMessage.NOTE_ON;
import static javax.sound.midi.ShortMessage.NOTE_OFF;
import static javax.sound.midi.ShortMessage.CONTROL_CHANGE;

public class MiniMusicPlayer3 {

	private MyDrawPanel panel;
	private Random random = new Random();
	
	public static void main(String[] args) {
		MiniMusicPlayer3 mini = new MiniMusicPlayer3();
		mini.go();
	}
	
	public void setUpGui() {
		// initialize panel
		panel = new MyDrawPanel();
		
		// create frame
		JFrame frame = new JFrame("My First Music Video");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.setBounds(30, 30, 1200, 700);
		frame.setVisible(true);
	}
	
	public void go() {
		setUpGui();
		
		// Sequencer, Sequence, Track, Events
		try {
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.addControllerEventListener(panel, new int[] {127});
			
			Sequence sequence = new Sequence(PPQ, 4);
			Track track = sequence.createTrack();
			
			int note;
			for (int i = 0; i < 60; i+=4) {
				note = random.nextInt(50) + 1;
				track.add(MiniMusicPlayer1.makeEvent(NOTE_ON, 1, note, 100, i));
				track.add(MiniMusicPlayer1.makeEvent(CONTROL_CHANGE, 1, 127, 0, i));
				track.add(MiniMusicPlayer1.makeEvent(NOTE_OFF, 1, note, 100, i+2));
			}
			
			sequencer.setSequence(sequence);
			sequencer.setTempoInBPM(220);
			sequencer.start();
			
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	// ------------------------------ INNER CLASSES ---------------------------------
	
	class MyDrawPanel extends JPanel implements ControllerEventListener {
		private boolean msg = false;

		@Override
		public void controlChange(ShortMessage event) {
			// Auto-generated method stub
			msg = true;
			repaint();
		}
		
		public void paintComponent(Graphics g) {
			if (msg) {
				int r = random.nextInt(250);
				int gr = random.nextInt(250);
				int b = random.nextInt(250);
				
				g.setColor(new Color(r, gr, b));
				
				int xPos = random.nextInt(1000) + 10;
				int yPos = random.nextInt(600) + 10;
				
				int height = random.nextInt(120) + 10;
				int width = random.nextInt(120) + 10;
				
				g.fillRect(xPos, yPos, width, height);
				msg = false;
			}
		}
		
	}
	
}
