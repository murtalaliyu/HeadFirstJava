package _4;

import java.util.List;
import java.util.stream.Stream;

import jukebox.Song;
import jukebox.Songs;

public class Test {
	
	public static void main(String[] args) {
		Test test = new Test();
		test.parallelStream();
	}
	
	private void parallelStream() {
		// You can simply tell the Streams API you want your stream
		// pipeline to be run on multiple CPU cores. 2 ways to do this
		
		// 1. Start a parallelStream
		List<Song> songs = Songs.getSongs();
		Stream<Song> par = songs.parallelStream();
		System.out.println("par: " + par);
		
		// 2. Add parallel() to the steam pipeline
		List<Song> songs2 = Songs.getSongs();
		Stream<Song> par2 = songs2.stream().parallel();
		System.out.println("par2: " + par2);
	}

}
