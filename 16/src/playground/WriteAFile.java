package playground;

import java.io.FileWriter;
import java.io.IOException;

public class WriteAFile {
	
	private static FileWriter writer;

	public static void main(String[] args) {
		WriteAFile.go();
	}
	
	private static void go() {
		try {
			
			writer = new FileWriter("Foo.txt"); //$NON-NLS-1$
			writer.write("My first String to save\nhello foo!"); //$NON-NLS-1$
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
