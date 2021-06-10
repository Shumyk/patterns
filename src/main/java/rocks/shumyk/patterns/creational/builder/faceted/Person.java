package rocks.shumyk.patterns.creational.builder.faceted;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@ToString
public class Person {

	// address
	private String streetAddress;
	private String postcode;
	private String city;

	// employment
	private String companyName;
	private String position;
	private int annualIncome;

	public static void main(String[] args) {
		final var personBuilder = new PersonBuilder();
		final var person = personBuilder
			.lives()
				.at("123 Zavods'ka")
				.in("Ivanychi")
				.withPostcode("7932934")
			.works()
				.at("PP Europe Sugar")
				.asA("Engineer")
				.earning(40000)
			.build();

		 log.info("\n{}", person);
	}
}
