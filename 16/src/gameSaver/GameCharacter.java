package gameSaver;

import java.io.Serializable;
import java.util.Arrays;

public class GameCharacter implements Serializable {

	private static final long serialVersionUID = -7515190610923331232L;
	private final int power;
	private final String type;
	private final String[] weapons;
	private Trait[] traits;
	
	public GameCharacter(int power, String type, String[] weapons, Trait[] traits) {
		this.power = power;
		this.type = type;
		this.weapons = weapons;
		this.traits = traits;
	}
	
	public int getPower() { return power; }

	public String getType() { return type; }

	public String getWeapons() { return Arrays.toString(weapons); }
	
	public String getTraits() { 
		String res = "";
		for (Trait t : traits) res += " " + t.getName();
		return res;
	}

}
