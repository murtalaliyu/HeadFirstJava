package playground;

import java.time.LocalDateTime;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.time.format.DateTimeFormatter.ofLocalizedTime;

public class ConcurrentReaders {
	
	public static void main(String[] args) {
		new ConcurrentReaders().go();
	}
	
	private void go() {
		List<Chat> chatHistory = new CopyOnWriteArrayList<>();
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 5; i++) {
			String iStr = String.valueOf(i);
			executor.execute(() -> chatHistory.add(new Chat(iStr + ": Hi there! from " + Thread.currentThread().getName())));
			executor.execute(() -> System.out.println(iStr + ": " + chatHistory + " from " + Thread.currentThread().getName()));
			executor.execute(() -> System.out.println(iStr + ": " + chatHistory + " from " + Thread.currentThread().getName()));
		}
		executor.shutdown();
	}

}

final class Chat {
	
	private final String message;
	private final LocalDateTime timestamp;
	
	public Chat(String message) {
		this.message = message;
		timestamp = LocalDateTime.now();
	}
	
	public String toString() {
		String time = timestamp.format(ofLocalizedTime(FormatStyle.MEDIUM));
		return time + " " + message;
	}
}
