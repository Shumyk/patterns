package rocks.shumyk.patterns.creational.singelton;

import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
public class SingletonDatabase {

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

class SingletonRecordFinder {
	public int getTotalPopulation(final List<String> names) {
		return names.stream()
			.map(name -> SingletonDatabase.getInstance().getPopulation(name))
			.reduce(0, Integer::sum);
	}
}

class SingletonDatabaseTest {

	@Test
	public void singletonTotalPopulationTest() {
		final var finder = new SingletonRecordFinder();
		final List<String> cities = List.of("Seoul", "Mexico City");
		final int totalPopulation = finder.getTotalPopulation(cities);
		assertEquals(17500000 + 17400000, totalPopulation);
	}
}