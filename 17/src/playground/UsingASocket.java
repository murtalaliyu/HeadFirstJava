package playground;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UsingASocket {
	
	public static void main(String[] args) {
		new UsingASocket().go();
	}
	
	private void go() {
		try {
			
			Socket chatSocket = new Socket("localhost", 5000);
			
			InputStreamReader in = new InputStreamReader(chatSocket.getInputStream());
			BufferedReader reader = new BufferedReader(in);
			String message = reader.readLine();
			
			PrintWriter writer = new PrintWriter(chatSocket.getOutputStream());
			writer.println("message to send");
			writer.print("another message");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
