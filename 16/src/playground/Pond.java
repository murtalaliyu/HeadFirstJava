package playground;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Pond implements Serializable {
	
	private Duck duck = new Duck();
	
	public static void main(String[] args) {
		Pond myPond = new Pond();
		
		try {
			
			FileOutputStream fs = new FileOutputStream("Pond.ser");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(myPond);
			
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound\n");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Exception\n");
			e.printStackTrace();
		}
		
	}

}
