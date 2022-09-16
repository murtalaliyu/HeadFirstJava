import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import static javax.sound.midi.Sequence.PPQ;
import static javax.sound.midi.ShortMessage.NOTE_OFF;
import static javax.sound.midi.ShortMessage.NOTE_ON;
import static javax.sound.midi.ShortMessage.PROGRAM_CHANGE;

import java.util.Scanner;

import javax.sound.midi.MidiEvent;

public class MiniMusicCmdLine {
  
  public static void main(String[] args) {
    MiniMusicCmdLine mini = new MiniMusicCmdLine();
    Scanner sc = new Scanner(System.in);
    String next = "";

    System.out.println("Enter the instrument and note args (/-separated)");
    
    while (!((next = sc.next()).equals("end"))) {      
      String[] inputs = next.split("/");
      int instrument = Integer.parseInt(inputs[0]);
      int note = Integer.parseInt(inputs[1]);
      mini.play(instrument, note);
    }
  }

  public void play(int instrument, int note) {
    try {

      // preprocessing
      Sequencer player = MidiSystem.getSequencer();
      player.open();
      Sequence sequence = new Sequence(PPQ, 4);
      Track track = sequence.createTrack();

      // change instrument
      ShortMessage msg1 = new ShortMessage();
      msg1.setMessage(PROGRAM_CHANGE, 1, instrument, 0);
      MidiEvent changeInstrument = new MidiEvent(msg1, 1);
      track.add(changeInstrument);

      // note on
      ShortMessage msg2 = new ShortMessage();
      msg2.setMessage(NOTE_ON, 1, note, 100);
      MidiEvent noteOn = new MidiEvent(msg2, 1);
      track.add(noteOn);

      // note off
      ShortMessage msg3 = new ShortMessage();
      msg3.setMessage(NOTE_OFF, 1, note, 100);
      MidiEvent noteOff = new MidiEvent(msg3, 16);
      track.add(noteOff);

      // start playback
      player.setSequence(sequence);
      player.start();

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}
