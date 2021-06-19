package rocks.shumyk.patterns.creational.singelton;

import lombok.Getter;
import lombok.Setter;

public class BasicSingleton {
	@Getter @Setter
	private int value = 0;

	public static final BasicSingleton INSTANCE = new BasicSingleton();

	private BasicSingleton() {}

	public static BasicSingleton instance() {
		return INSTANCE;
	}
}

class Main {
	public static void main(String[] args) {
		final BasicSingleton singleton = BasicSingleton.instance();
		singleton.setValue(123);

		System.out.println(singleton.getValue());
	}
}
