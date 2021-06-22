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
		SerializationUtils.saveToFile(singleton, filename);

		singleton.setValue(222);

		final BasicSingleton deserializedSingleton = SerializationUtils.readFromFile(filename);
		System.out.println("equality of singletons: " + (singleton == deserializedSingleton));
		System.out.println("initial singleton value: " + singleton.getValue());
		System.out.println("deserialized singleton value: " + deserializedSingleton.getValue());
	}
}
