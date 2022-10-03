package beatBox;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MusicServer {
	
	/**
	 * List of all the client output streams to 
	 * send messages to when a message is received
	 */
	final List<ObjectOutputStream> clientOutputStreams = new ArrayList<>();
	
	public static void main(String[] args) {
		new MusicServer().go();
	}
	
	private void go() {
		try {
			
			ServerSocket serverSock = new ServerSocket(4242);
			ExecutorService threadPool = Executors.newCachedThreadPool();
			
			/**
			 * Keep listening for client connections; create a new Socket
			 * and new ClientHandler for each connected client
			 */
			while (!serverSock.isClosed()) {
				Socket clientSocket = serverSock.accept();
				ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
				clientOutputStreams.add(out);
				
				ClientHandler clientHandler = new ClientHandler(clientSocket);
				threadPool.execute(clientHandler);
				System.out.println("Server got a connection");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Send the message and the beat pattern to all the clients
	 * @param one
	 * @param two
	 */
	private void tellEveryone(Object one, Object two) {
		for (ObjectOutputStream clientOutputStream : clientOutputStreams) {
			try {
				clientOutputStream.writeObject(one);
				clientOutputStream.writeObject(two);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// --------------------------- INNER CLASSES -------------------------------------------
	
	public class ClientHandler implements Runnable {
		
		private ObjectInputStream in;
		
		public ClientHandler(Socket socket) {
			// create an OIS for reading messages from this client
			try {
				in = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			// Auto-generated method stub
			Object userName;
			Object beatSequence;
			
			try {
				
				while ((userName = in.readObject()) != null) {
					beatSequence = in.readObject();
					
					System.out.println("read two objects");
					tellEveryone(userName, beatSequence);
				}
				
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}

}
