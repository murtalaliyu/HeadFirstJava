package playground;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Install {
	
	public static void main(String[] args) {
		Install.go();
	}
	
	private static void go() {
		try {
			
			Path appDir = Paths.get("MyApp"); //$NON-NLS-1$
			Files.createDirectories(appDir);
			
			Path mediaDir = Paths.get("MyApp", "media"); //$NON-NLS-1$ //$NON-NLS-2$
			Files.createDirectories(mediaDir);
			
			Path sourceDir = Paths.get("MyApp", "source"); //$NON-NLS-1$ //$NON-NLS-2$
			Files.createDirectories(sourceDir);
			
			Path mySource = Paths.get("MyApp.class"); //$NON-NLS-1$
			Path myMedia = Paths.get("MyMedia.jpeg"); //$NON-NLS-1$
			
			Files.move(mySource, sourceDir.resolve(mySource.getFileName()));
			Files.move(myMedia, mediaDir.resolve(myMedia.getFileName()));
			
		} catch (IOException e) {
			System.out.println(":("); //$NON-NLS-1$
			e.printStackTrace();
		}
		
	}

}
