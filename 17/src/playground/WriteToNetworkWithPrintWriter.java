package playground;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class WriteToNetworkWithPrintWriter {

	public static void main(String[] args) {
		new WriteToNetworkWithPrintWriter().go();
	}
	
	private void go() {
		try {

			// 1. Make a connection to the server
			SocketAddress serverAddr = new InetSocketAddress("127.0.0.1", 5000);
			SocketChannel socketChannel = SocketChannel.open(serverAddr);
			
			// 2. Create or get a Writer from the connection
			// Writer acts as a bridge between character data and the
			// bytes to be written to the Channel.
			Writer writer = Channels.newWriter(socketChannel, StandardCharsets.UTF_8);
			
			// 3. Make a PrintWriter and write (print) something
			PrintWriter printWriter = new PrintWriter(writer);
			printWriter.println("message to send");
			printWriter.print("another message");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
