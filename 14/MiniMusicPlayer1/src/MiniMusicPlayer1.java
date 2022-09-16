import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import static javax.sound.midi.Sequence.PPQ;
import static javax.sound.midi.ShortMessage.NOTE_OFF;
import static javax.sound.midi.ShortMessage.NOTE_ON;

public class MiniMusicPlayer1 {

	public static void main(String[] args) {
		try {
			
			// create Sequencer and Sequence
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			Sequence sequence = new Sequence(PPQ, 4);
			
			// add a Track to Sequence
			Track track = sequence.createTrack();
			
			// make a bunch of events that make the notes keep going up (from piano note 5 to piano note 61)
			for (int i = 5; i <= 61; i++) {
				track.add(makeEvent(NOTE_ON, 1, i, 100, i));
				track.add(makeEvent(NOTE_OFF, 1, i, 100, i+2));
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
}
