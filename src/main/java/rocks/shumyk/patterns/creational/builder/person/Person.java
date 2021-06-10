package rocks.shumyk.patterns.creational.builder.person;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@ToString
public class Person {
	private String name;
	private String position;
}
