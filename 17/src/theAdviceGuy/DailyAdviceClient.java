package theAdviceGuy;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class DailyAdviceClient {
	
	public static void main(String[] args) {
		DailyAdviceClient.go();
	}
	
	private static void go() {
		InetSocketAddress serverAddress = new InetSocketAddress("localhost", 5000);
		
		try (SocketChannel socketChannel = SocketChannel.open(serverAddress);
				BufferedReader reader = new BufferedReader(Channels.newReader(socketChannel, StandardCharsets.UTF_8))) {
			
			String advice = reader.readLine();
			System.out.println("client received: " + advice);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
