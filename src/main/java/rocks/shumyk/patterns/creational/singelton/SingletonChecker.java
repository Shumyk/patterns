package rocks.shumyk.patterns.creational.singelton;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SingletonChecker {

	// assignment to find a way to check if provided supplier gives exactly singleton
	public static boolean isSingleton(final Supplier<Object> func) {
		final var singleton = func.get();
		final var singleton2 = func.get();
		return singleton == singleton2;
	}
}
