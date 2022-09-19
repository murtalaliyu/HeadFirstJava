package playground;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadAFile {

	public static void main(String[] args) {
		ReadAFile.go();
	}
	
	private static void go() {
		try {
			Files
			.lines(Path.of("Foo.txt")) //$NON-NLS-1$
			.forEach(line -> System.out.println(line));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
