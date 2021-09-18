package rocks.shumyk.patterns.structural.flyweight;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Objects.nonNull;

class Sentence {
	private static final List<String> words = new ArrayList<>();

	private final int[] references;
	private final Map<Integer, WordToken> tokenMap = new HashMap<>();

	public Sentence(String plainText) {
		final ToIntFunction<String> getOrAdd = s -> {
			final int index = words.indexOf(s);
			if (index != -1) return index;
			words.add(s);
			return words.size() - 1;
		};

		references = Arrays.stream(plainText.split(" "))
			.mapToInt(getOrAdd)
			.toArray();
	}

	public WordToken getWord(int index) {
		return tokenMap.computeIfAbsent(index, i -> new WordToken());
	}

	@Override
	public String toString() {
		return IntStream.range(0, references.length)
			.mapToObj(i -> nonNull(tokenMap.get(i)) && tokenMap.get(i).capitalize ? words.get(i).toUpperCase() : words.get(i))
			.collect(Collectors.joining(" "));
	}

	class WordToken {
		public boolean capitalize;
	}
}

@Slf4j
public class CodingExercise {
	public static void main(String[] args) {
		final var sentence = new Sentence("hello world");
		sentence.getWord(1).capitalize = true;
		log.info("{}", sentence);
	}
}
