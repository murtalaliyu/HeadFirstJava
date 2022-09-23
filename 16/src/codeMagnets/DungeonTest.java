package codeMagnets;

import java.io.*;

class DungeonTest {
	
	public static void main(String[] args) {
		// 1. Print 12
		DungeonGame d = new DungeonGame();
		System.out.println(d.getX() + d.getY() + d.getZ());
		
		// 2. Serialize d
		try (FileOutputStream fos = new FileOutputStream("dg.ser"); //$NON-NLS-1$
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 3. Deserialize d (to print 8)
		try (FileInputStream fis = new FileInputStream("dg.ser"); //$NON-NLS-1$
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			d = (DungeonGame) ois.readObject();
			System.out.println(d.getX() + d.getY() + d.getZ());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
