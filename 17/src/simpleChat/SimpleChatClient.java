package simpleChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import static java.awt.BorderLayout.CENTER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static java.nio.charset.StandardCharsets.UTF_8;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;;

public class SimpleChatClient {
	
	private JTextArea incoming;
	private JTextField outgoing;
	private BufferedReader reader;
	private PrintWriter writer;
	
	public static void main(String[] args) {
		new SimpleChatClient().go();
	}
	
	private void go() {
		setUpNetworking();
		
		JScrollPane scroller = createScrollableTextArea();
		
		outgoing = new JTextField(20);
		
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(e -> sendMessage());
		
		JPanel mainPanel = new JPanel();
		mainPanel.add(scroller);
		mainPanel.add(outgoing);
		mainPanel.add(sendButton);
		
		// We've got a new new job, an inner class, which is
		// a Runnable. The job is to read from the server's
		// socket stream, displaying any incoming messages in
		// the scrolling text area. We start this job using a 
		// single thread executor since we know we want to run
		// only this one job.
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(new IncomingReader());
		
		JFrame frame = new JFrame("Ludicrously Simple Chat Client");
		frame.getContentPane().add(CENTER, mainPanel);
		frame.setSize(400, 350);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private JScrollPane createScrollableTextArea() {
		incoming = new JTextArea(15, 30);
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setEditable(false);
		
		JScrollPane scroller = new JScrollPane(incoming);
		scroller.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		return scroller;
	}
	
	/**
	 * We're using Channels to create a new reader and writer
	 * for the SocketChannel that's connected to the server.
	 * The writer sends messages to the server, and now we're
	 * using a reader so that the reader job can get messages
	 * from the server.
	 */
	private void setUpNetworking() {
		try {
			
			InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1", 5000);
			SocketChannel socketChannel = SocketChannel.open(serverAddress);
			
			reader = new BufferedReader(Channels.newReader(socketChannel, UTF_8));
			writer = new PrintWriter(Channels.newWriter(socketChannel, UTF_8));
			
			System.out.println("Client networking established");
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendMessage() {
		writer.println(outgoing.getText());
		writer.flush();
		outgoing.setText("");
		outgoing.requestFocus();
	}
	
	// --------------------------------- INNER CLASSES -----------------------------
	
	public class IncomingReader implements Runnable {

		@Override
		public void run() {
			// Auto-generated method stub
			String message;
			try {
				while ((message = reader.readLine()) != null) {
					System.out.println("Client read " + message);
					incoming.append(message + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
