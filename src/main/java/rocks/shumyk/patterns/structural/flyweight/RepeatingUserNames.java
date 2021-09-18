package rocks.shumyk.patterns.structural.flyweight;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
class User {
	private final String fullName;
}

/*
 User with flyweight pattern.
 on construction full name is split and via special function maps name and surname to names cache.
 then user object contains only reference to actual string and then can be concat.
 */
@Slf4j
class User2 {
	private static final String DELIMITER = " ";

	private static final List<String> strings = new ArrayList<>();
	private final int[] names;

	public User2(final String fullName) {
		final ToIntFunction<String> getOrAdd = s -> {
			final int idx = strings.indexOf(s);
			if (idx != -1) return idx;
			else {
				strings.add(s);
				return strings.size() - 1;
			}
		};

		names = Arrays.stream(fullName.split(DELIMITER))
			.mapToInt(getOrAdd)
			.toArray();
		log.info("strings so far: [{}]", strings);
	}

	public String getFullName() {
		return Arrays.stream(names)
			.mapToObj(strings::get)
			.collect(Collectors.joining(DELIMITER));
	}
}

@Slf4j
public class RepeatingUserNames {
	public static void main(String[] args) {
		final User johnSmith = new User("John Smith");
		final User janeSmith = new User("Jane Smith");

		final User2 johnSmith2 = new User2("John Smith");
		final User2 janeSmith2 = new User2("Jane Smith");

		log.info("comparing default user vs user with flyweight: [{}] - [{}]", johnSmith.getFullName(), johnSmith2.getFullName());
		log.info("comparing default user vs user with flyweight: [{}] - [{}]", janeSmith.getFullName(), janeSmith2.getFullName());
	}
}
