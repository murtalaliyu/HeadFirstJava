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
		gst.go();
	}
	
	private void go() {
		List<GameCharacter> characters = new ArrayList<>();
		characters.add(new GameCharacter(50, "Elf", new String[] {"bow", "sword", "dust"}, new Trait[] {new Trait("curious, excited")}));
		characters.add(new GameCharacter(200, "Troll", new String[] {"bare hands", "bix ax"}, new Trait[] {new Trait("aggressive, angry")}));
		characters.add(new GameCharacter(120, "Magician", new String[] {"spells", "invisibility"}, new Trait[] {new Trait("cunning, clever")}));
		
		// imagine code that does things with the characters that changes their state values
		
		serialize(characters, "Game.ser");
		deserialize("Game.ser");
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
			
			System.out.println(oneRestore.getType() + "'s traits: " + oneRestore.getTraits());
			System.out.println(twoRestore.getType() + "'s traits: " + twoRestore.getTraits());
			System.out.println(threeRestore.getType() + "'s traits: " + threeRestore.getTraits());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
