package codeMagnets;

import java.io.*;

class DungeonGame implements Serializable {

	public int x = 3;
	transient long y = 4;
	private short z = 5;
	
	int getX() { return this.x; }
	long getY() { return this.y; }
	short getZ() { return this.z; }
	
}
