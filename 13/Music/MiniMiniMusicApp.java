import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import static javax.sound.midi.ShortMessage.NOTE_ON;
import static javax.sound.midi.ShortMessage.NOTE_OFF;

public class MiniMiniMusicApp {

  public static void main(String[] args) {
    MiniMiniMusicApp mini = new MiniMiniMusicApp();
    mini.play();
  }

  public void play() {
    try {

      //Get a Sequencer and open it
      Sequencer player = MidiSystem.getSequencer();
      player.open();

      //Make a new Sequence
      Sequence sequence = new Sequence(Sequence.PPQ, 4);

      //Ask the Sequence for a Track. The Track lives in the Sequence,
      //and the the MIDI data lives in the Track.
      Track track = sequence.createTrack();

      //Put some MidiEvents into the Track.
      //Change the channel (instrument) first
      ShortMessage changeMsg = new ShortMessage();
      changeMsg.setMessage(192, 1, 74, 0);
      MidiEvent channelChange = new MidiEvent(changeMsg, 1);
      track.add(channelChange);

      ShortMessage msg1 = new ShortMessage();
      msg1.setMessage(NOTE_ON, 1, 80, 100);
      MidiEvent noteOn = new MidiEvent(msg1, 1);
      track.add(noteOn);

      ShortMessage msg2 = new ShortMessage();
      msg2.setMessage(NOTE_OFF, 1, 44, 100);
      MidiEvent noteOff = new MidiEvent(msg2, 3);
      track.add(noteOff);

      //Give the Sequence to the Sequencer (like selecting the song to play)
      player.setSequence(sequence);

      //Start the Sequencer (play the Song)
      player.start();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
