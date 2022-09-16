import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;

import static javax.sound.midi.Sequence.PPQ;
import static javax.sound.midi.ShortMessage.NOTE_ON;
import static javax.sound.midi.ShortMessage.NOTE_OFF;
import static javax.sound.midi.ShortMessage.CONTROL_CHANGE;

import javax.sound.midi.InvalidMidiDataException;

public class MiniMusicPlayer2 {

	private int count = 0;

	public static void main(String[] args) {
		new MiniMusicPlayer2().go();
	}
	
	public void go() {
		try {
			
			// create and open Sequencer
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			
			// register for events with the Sequencer. 
			// takes in the listener and an integer array representing the list of ControllerEvents you want.
			// we care about event #127.
			int[] eventsIWant = {127};
			sequencer.addControllerEventListener(event -> {
				count++;
				System.out.println("la: " + count);
			}, eventsIWant);
			
			// create Sequence
			Sequence sequence = new Sequence(PPQ, 4);
			
			// add Track to Sequence
			Track track = sequence.createTrack();
			
			// add events to track
			for (int i = 5; i < 60; i += 4) {
				track.add(MiniMusicPlayer1.makeEvent(NOTE_ON, 1, i, 100, i));
				track.add(MiniMusicPlayer1.makeEvent(CONTROL_CHANGE, 1, 127, 0, i));	// add this to receive NOTE_ON events
				track.add(MiniMusicPlayer1.makeEvent(NOTE_OFF, 1, i, 100, i+2));
			}
			
			// start play-back
			sequencer.setSequence(sequence);
			sequencer.setTempoInBPM(220);
			sequencer.start();
			
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
	}

}
