package rocks.shumyk.patterns.creational.builder.person;

public class PersonBuilder<S extends PersonBuilder<S>> {

	protected Person person = new Person();

	public S withName(final String name) {
		person.setName(name);
		return self();
	}

	public Person build() {
		return person;
	}

	protected S self() {
		return (S) this;
	}
}
