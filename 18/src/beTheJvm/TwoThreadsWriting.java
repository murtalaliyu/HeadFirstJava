package beTheJvm;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TwoThreadsWriting {
	
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(2);
		Data data = new Data();
		threadPool.execute(() -> addLetterToData('a', data));
		threadPool.execute(() -> addLetterToData('A', data));
		threadPool.shutdown();
	}
	
	private static void addLetterToData(char letter, Data data) {
		for (int i = 0; i < 26; i++) {
			data.addLetter(letter++);
			try { Thread.sleep(50); } catch (InterruptedException ignored) {}
		}
		
		System.out.println(Thread.currentThread().getName() + data.getLetters());
		System.out.println(Thread.currentThread().getName() + " size = " + data.getLetters().size());
	}

}

/** If addLetter() is synchronized, only one thread at a time
 * can write to the data, and therefore no updates will be lost.
 * This will not work if there's a DIFFERENT thread reading
 * at the same time as one of these threads are writing.
 * 
 *  Using CopyOnWriteArrayList will allow the threads
 *  to both safely write to the letters list.*/
final class Data {
	private final List<String> letters = new /*ArrayList*/CopyOnWriteArrayList<>();
	public List<String> getLetters() { return letters; }
	public void addLetter(char letter) { letters.add(String.valueOf(letter)); }
}
