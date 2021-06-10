package rocks.shumyk.patterns.creational.builder.person;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> {

	public EmployeeBuilder worksAs(final String position) {
		person.setPosition(position);
		return self();
	}

	@Override
	protected EmployeeBuilder self() {
		return this;
	}

	public static void main(String[] args) {
		final var personBuilder = new EmployeeBuilder();
		final Person arnold = personBuilder.withName("Arnold")
			.worksAs("baker")
			.build();
		log.info(arnold.toString());
	}
}
