package rocks.shumyk.patterns.structural.decorator;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

interface StringExcludes {
	String stripIndent();
	String translateEscapes();
	String formatted(Object... args);
}

/**
 * Implementing Decorator pattern with final class String.
 * Firstly, generating all delegates method to String object and then adding new functionality - vowelsNumber()
 */
@RequiredArgsConstructor
class MagicString {
	private static final String VOWELS = "aeiou";

	@Delegate(excludes = StringExcludes.class) private final String string;

	public long vowelsNumber() {
		return Arrays.stream(string.split(""))
			.filter(VOWELS::contains)
			.count();
	}

	@Override
	public String toString() {
		return string;
	}
}

@Slf4j
public class StringDecorator {
	public static void main(String[] args) {
		final var hello = new MagicString("hello");
		log.info("{} has {} vowels", hello, hello.vowelsNumber());
	}
}
