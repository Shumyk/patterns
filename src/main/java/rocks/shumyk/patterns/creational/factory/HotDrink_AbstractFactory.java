package rocks.shumyk.patterns.creational.factory;

import com.google.common.collect.Maps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public interface HotDrink_AbstractFactory {
	void consume();
}
class Tea implements HotDrink_AbstractFactory {
	@Override public void consume() {
		System.out.println("This tea is delicious");
	}
}
class Coffee implements HotDrink_AbstractFactory {
	@Override public void consume() {
		System.out.println("This coffee is delicious");
	}
}

interface HotDrinkFactory {
	HotDrink_AbstractFactory prepare(int amount);
}
class TeaFactory implements HotDrinkFactory {
	@Override public HotDrink_AbstractFactory prepare(int amount) {
		System.out.printf("Put in tea bag, boil water, pour %s ml, add lemon, enjoy!%n", amount);
		return new Tea();
	}
}
class CoffeeFactory implements HotDrinkFactory {
	@Override public HotDrink_AbstractFactory prepare(int amount) {
		System.out.printf("Grind some beans, boil water, pour %s ml, add cream and sugar, enjoy!%n", amount);
		return new Coffee();
	}
}

class HotDrinkMachine {

	private final List<Entry<String, HotDrinkFactory>> namedFactories = new ArrayList<>();

	public HotDrinkMachine() {
		namedFactories.add(Maps.immutableEntry("Tea", new TeaFactory()));
		namedFactories.add(Maps.immutableEntry("Coffee", new CoffeeFactory()));
	}

	public HotDrink_AbstractFactory makeDrink() throws IOException {
		System.out.println("Available drinks:");
		for (var i = 0; i < namedFactories.size(); i++) {
			final Entry<String, HotDrinkFactory> factoryByName = namedFactories.get(i);
			System.out.println(i + ": " + factoryByName.getKey());
		}

		final var reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String s;
			int i;
			int amount;
			if ((s = reader.readLine()) != null
				&& (i = Integer.parseInt(s)) >= 0
				&& i < namedFactories.size()) {
				System.out.println("Specify amount:");
				s = reader.readLine();
				if (s != null && (amount = Integer.parseInt(s)) > 0) {
					return namedFactories.get(i).getValue().prepare(amount);
				}
			}
			System.out.println("Incorrect input, try again.");
		}
	}
}

class Demo {
	public static void main(String[] args) throws Exception {
		final var machine = new HotDrinkMachine();
		final var hotDrink = machine.makeDrink();
		hotDrink.consume();
	}
}