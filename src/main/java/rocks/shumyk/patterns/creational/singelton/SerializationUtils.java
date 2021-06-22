package rocks.shumyk.patterns.creational.singelton;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SerializationUtils {

	public static <T> void saveToFile(final T singleton, final String filename) throws IOException {
		try (var fileOut = new FileOutputStream(filename);
			 var out = new ObjectOutputStream(fileOut)) {
			out.writeObject(singleton);
		}
	}

	public static <T> T readFromFile(final String filename) throws IOException, ClassNotFoundException {
		try (var fileIn = new FileInputStream(filename);
			 var in = new ObjectInputStream(fileIn)) {
			return (T) in.readObject();
		}
	}
}
