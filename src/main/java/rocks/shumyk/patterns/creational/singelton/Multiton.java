package rocks.shumyk.patterns.creational.singelton;

import lombok.extern.slf4j.Slf4j;

import java.util.EnumMap;
import java.util.Map;

@Slf4j
public class Multiton {
	public static void main(String[] args) {
		final var main = Printer.get(Subsystem.PRIMARY);
		final var aux = Printer.get(Subsystem.AUXILIARY);
		final var aux2 = Printer.get(Subsystem.AUXILIARY);
		log.info("instances is same: {}", aux == aux2);
	}
}

/*
Multiton - allows only few instances of class
 */
@Slf4j
class Printer {

	private static final Map<Subsystem, Printer> instances = new EnumMap<>(Subsystem.class);

	private Printer() {
		log.info("A total of {} instances created so far", instances.size() + 1);
	}

	public static Printer get(final Subsystem sys) {
		if (instances.containsKey(sys)) {
			return instances.get(sys);
		}
		final var instance = new Printer();
		instances.put(sys, instance);
		return instance;
	}
}

enum Subsystem {
	PRIMARY,
	AUXILIARY,
	FALLBACK
}