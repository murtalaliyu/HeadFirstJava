package gameSaver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class GameSaverTest {

	public static void main(String[] args) {
		GameSaverTest gst = new GameSaverTest();
		
		GameCharacter one = new GameCharacter(50, "Elf", new String[] {"bow", "sword", "dust"});
		GameCharacter two = new GameCharacter(200, "Troll", new String[] {"bare hands", "bix ax"});
		GameCharacter three = new GameCharacter(120, "Magician", new String[] {"spells", "invisibility"});
		
		List<GameCharacter> characters = new ArrayList<>();
		characters.add(one);
		characters.add(two);
		characters.add(three);
		
		// imagine code that does things with the characters that changes their state values
		
		gst.serialize(characters, "Game.ser");
		gst.deserialize("Game.ser");
		
	}
	
	private void serialize(List<GameCharacter> characters, String fileName) {
		try {
			
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
			for (GameCharacter c : characters) os.writeObject(c);
			os.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void deserialize(String fileName) {		
		try {
			
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
			GameCharacter oneRestore = (GameCharacter) is.readObject();
			GameCharacter twoRestore = (GameCharacter) is.readObject();
			GameCharacter threeRestore = (GameCharacter) is.readObject();
			is.close();
			
			System.out.println("One's type: " + oneRestore.getType());
			System.out.println("Two's type: " + twoRestore.getType());
			System.out.println("Three's type: " + threeRestore.getType());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
