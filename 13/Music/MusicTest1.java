import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;

public class MusicTest1 {

  public static void main(String[] args) {
    MusicTest1 mt = new MusicTest1();
    mt.play();
  }

  public void play() {
    try {

      // Get a Sequencer and open it
      Sequencer player = MidiSystem.getSequencer();
      player.open();

      // Make a new Sequence
      Sequence sequence = new Sequence(Sequence.PPQ, 4);

      // Get a new Track from the Sequence
      Track track = sequence.createTrack();

      // Fill the Track with MidiEvents and give the Sequence to the Sequencer
      track.add(myMidiEvent1);
      player.setSequence(sequence);

      // Start the Sequencer
      player.start();
      

    } catch (MidiUnavailableException e) {
      System.out.println("Detailed message:\n" + e.getMessage());
      e.printStackTrace();
    }
  }

}
