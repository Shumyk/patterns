package rocks.shumyk.patterns.creational.singelton;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/*
 Implementing enum based singleton
 it avoids all problems with initiation only one instance and thread-safety
 but it not serialize value, so only actual singleton instance then returned
*/
@Slf4j
public enum EnumBasedSingleton {
	INSTANCE;

	EnumBasedSingleton() {
		value = 73;
	}

	@Getter @Setter	private int value;
}

@Slf4j
class Demo {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		var filename = "myfile.bin";
		var singleton = EnumBasedSingleton.INSTANCE;
		singleton.setValue(111);
		SerializationUtils.saveToFile(singleton, filename);

		EnumBasedSingleton deserializedSingleton = SerializationUtils.readFromFile(filename);
		log.info("initial and deserialized instances equal: {}", singleton == deserializedSingleton);
		log.info("deserialized value: {}", deserializedSingleton.getValue());
	}
}
