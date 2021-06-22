package rocks.shumyk.patterns.creational.singelton;

import lombok.Getter;
import lombok.Setter;

import java.io.*;

public class BasicSingleton implements Serializable {
	@Getter @Setter
	private int value = 0;

	public static final BasicSingleton INSTANCE = new BasicSingleton();

	private BasicSingleton() {}

	public static BasicSingleton instance() {
		return INSTANCE;
	}

	@Serial
	protected Object readResolve() {
		return INSTANCE;
	}
}

class Main {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// 1. reflection
		// 2. serialization

		var singleton = BasicSingleton.instance();
		singleton.setValue(111);

		var filename = "singleton.bin";
		saveToFile(singleton, filename);

		singleton.setValue(222);

		final BasicSingleton deserializedSingleton = readFromFile(filename);
		System.out.println("equality of singletons: " + (singleton == deserializedSingleton));
		System.out.println("initial singleton value: " + singleton.getValue());
		System.out.println("deserialized singleton value: " + deserializedSingleton.getValue());
	}

	static void saveToFile(final BasicSingleton singleton, final String filename) throws IOException {
		try (var fileOut = new FileOutputStream(filename);
			 var out = new ObjectOutputStream(fileOut)) {
			out.writeObject(singleton);
		}
	}

	static BasicSingleton readFromFile(final String filename) throws IOException, ClassNotFoundException {
		try (var fileIn = new FileInputStream(filename);
			 var in = new ObjectInputStream(fileIn)) {
			return (BasicSingleton) in.readObject();
		}
	}
}
