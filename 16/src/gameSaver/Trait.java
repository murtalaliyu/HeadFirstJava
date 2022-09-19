package gameSaver;

import java.io.Serializable;

public class Trait implements Serializable {

	private String name;
	
	public Trait(String name) {
		this.name = name;
	}
	
	public String getName() { return name; }
}
