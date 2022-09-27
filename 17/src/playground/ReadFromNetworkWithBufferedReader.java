package playground;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ReadFromNetworkWithBufferedReader {

	public static void main(String[] args) {
		new ReadFromNetworkWithBufferedReader().go();
	}
	
	private void go() {
		try {

			// 1. Make a connection to the server
			SocketAddress serverAddr = new InetSocketAddress("127.0.0.1", 5000);
			SocketChannel socketChannel = SocketChannel.open(serverAddr);
			
			// 2. Create or get a Reader from the connection
			// this Reader is a "bridge" between a low-level byte stream (like the one
			// coming from the Channel) and a high-level character stream (like the 
			// BufferedReader we're after as our top of the chain stream)
			Reader reader = Channels.newReader(socketChannel, StandardCharsets.UTF_8);
			
			// 3. Make a BufferedReader and read!
			BufferedReader bufferedReader = new BufferedReader(reader);
			String message = bufferedReader.readLine();
			System.out.println("message is: " + message);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
