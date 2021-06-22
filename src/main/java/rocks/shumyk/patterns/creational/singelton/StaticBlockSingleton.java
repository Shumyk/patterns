package rocks.shumyk.patterns.creational.singelton;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public class StaticBlockSingleton {

	private static StaticBlockSingleton instance;

	private StaticBlockSingleton() throws IOException {
		log.info("singleton is initializing");
		File.createTempFile(".", ".");
	}

	static {
		try {
			instance = new StaticBlockSingleton();
		} catch (IOException e) {
			log.error("failed to create singleton");
		}
	}

	public static StaticBlockSingleton getInstance() {
		return instance;
	}
}
