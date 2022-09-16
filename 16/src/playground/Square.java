package playground;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Square implements Serializable {
	
	private int width;
	private int height;
	
	public Square(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public static void main(String[] args) {
		Square square = new Square(50, 20);
		
		try {
			
			FileOutputStream fs = new FileOutputStream("foo.ser");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(square);
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found! :(");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
