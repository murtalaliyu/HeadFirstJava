package simpleChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SimpleChatServer {
	
	private final List<PrintWriter> clientWriters = new ArrayList<>();
	
	public static void main(String[] args) {
		new SimpleChatServer().go();
	}
	
	private void go() {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		
		try {
			
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.bind(new InetSocketAddress(5000));
			
			while (serverSocketChannel.isOpen()) {
				SocketChannel clientSocket = serverSocketChannel.accept();
				PrintWriter writer = new PrintWriter(Channels.newWriter(clientSocket, UTF_8));
				clientWriters.add(writer);
				threadPool.submit(new ClientHandler(clientSocket));
				System.out.println("Server got a connection");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void tellEveryone(String message) {
		for (PrintWriter writer : clientWriters) {
			writer.println(message);
			writer.flush();
		}
	}
	
	// --------------------------- INNER CLASSES ----------------------
	
	public class ClientHandler implements Runnable {
		
		BufferedReader reader;
		SocketChannel socket;
		
		public ClientHandler(SocketChannel clientSocket) {
			socket = clientSocket;
			reader = new BufferedReader(Channels.newReader(socket, UTF_8));
		}

		@Override
		public void run() {
			// Auto-generated method stub
			String message;
			try {
				while ((message = reader.readLine()) != null) {
					System.out.println("Server read " + message);
					tellEveryone(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
