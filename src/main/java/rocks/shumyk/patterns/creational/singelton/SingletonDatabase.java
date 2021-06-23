package rocks.shumyk.patterns.creational.singelton;

import com.google.common.collect.Iterables;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

interface Database {
	int getPopulation(String name);
}

@Slf4j
public class SingletonDatabase implements Database {

	private static final SingletonDatabase INSTANCE = new SingletonDatabase();

	private final Dictionary<String, Integer> capitals = new Hashtable<>();
	private static int instanceCount = 0;

	private SingletonDatabase() {
		instanceCount++;
		log.info("Initializing database");
		try {
			final var file = new File(
				SingletonDatabase.class.getProtectionDomain().getCodeSource().getLocation().getPath()
			);
			final var path = Paths.get(file.getPath(), "capitals.txt");
			final var lines = Files.readAllLines(path);

			Iterables.partition(lines, 2)
				.forEach(line -> capitals.put(line.get(0).trim(), Integer.valueOf(line.get(1))));
		} catch (Exception e) {
			log.error("unexpected exception occurred: {}", e.getMessage(), e);
		}
	}

	public static SingletonDatabase getInstance() {
		return INSTANCE;
	}

	public static int getCount() {
		return instanceCount;
	}

	public int getPopulation(final String name) {
		return capitals.get(name);
	}
}

// dummy database to be injected for test
class DummyDatabase implements Database {

	private final Dictionary<String, Integer> data = new Hashtable<>();

	public DummyDatabase() {
		data.put("alpha", 1);
		data.put("beta", 2);
		data.put("gamma", 3);
	}

	@Override
	public int getPopulation(String name) {
		return data.get(name);
	}
}


class SingletonRecordFinder {
	public int getTotalPopulation(final List<String> names) {
		return names.stream()
			.map(name -> SingletonDatabase.getInstance().getPopulation(name))
			.reduce(0, Integer::sum);
	}
}

/* Here dependency injection implemented, so we can pass dummy database for test */
@RequiredArgsConstructor
class ConfigurableRecordFinder {
	private final Database database;

	public int getTotalPopulation(final List<String> names) {
		return names.stream()
			.map(database::getPopulation)
			.reduce(0, Integer::sum);
	}
}

class SingletonDatabaseTest {

	@Test // not a unit test, it's integration test, cause real DB loads for this
	public void singletonTotalPopulationTest() {
		final var finder = new SingletonRecordFinder();
		final var cities = List.of("Seoul", "Mexico City");
		final var totalPopulation = finder.getTotalPopulation(cities);
		assertEquals(17500000 + 17400000, totalPopulation);
	}

	@Test // proper unit test with dummy database injected
	public void dependentPopulationTest() {
		final var dummyDatabase = new DummyDatabase();
		final var finder = new ConfigurableRecordFinder(dummyDatabase);
		assertEquals(4, finder.getTotalPopulation(List.of("alpha", "gamma")));
	}
}