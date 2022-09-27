package simpleChat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import static java.awt.BorderLayout.CENTER;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static java.nio.charset.StandardCharsets.UTF_8;

public class SimpleChatClientA {
	
	private JTextField outgoing;
	private PrintWriter writer;
	
	public static void main(String[] args) {
		new SimpleChatClientA().go();
	}
	
	private void go() {
		// call the setUpNetworking() method
		// make GUI and register a listener with the send button
		setUpNetworking();
		
		outgoing = new JTextField(20);
		
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(e -> sendMessage());
		
		JPanel mainPanel = new JPanel();
		mainPanel.add(outgoing);
		mainPanel.add(sendButton);
		
		JFrame frame = new JFrame("Ludicrously Simple Chat Client");
		frame.getContentPane().add(CENTER, mainPanel);
		frame.setSize(400, 100);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void setUpNetworking() {
		// open a SocketChannel to the server
		// make a PrintWriter and assign to writer instance variable
		try (SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 5000))) {
			
			writer = new PrintWriter(Channels.newWriter(socketChannel, UTF_8));
			System.out.println("Networking established at SimpleChatClientA.");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendMessage() {
		// get the text from the text field and
		// send it to the server using the writer (a PrintWriter)
		String message = outgoing.getText();
		System.out.println("client is about to send: " + message);
		writer.println(message);
		writer.flush();
		outgoing.setText("");
		outgoing.requestFocus();
	}

}
