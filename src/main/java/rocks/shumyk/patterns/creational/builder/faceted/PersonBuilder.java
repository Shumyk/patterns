package rocks.shumyk.patterns.creational.builder.faceted;

// builder facade
// allows to have different builder for same object
// which makes API flexible to switch from one builder to another
public class PersonBuilder {

	protected Person person = new Person();

	public PersonAddressBuilder lives() {
		return new PersonAddressBuilder(person);
	}

	public PersonJobBuilder works() {
		return new PersonJobBuilder(person);
	}

	public Person build() {
		return person;
	}
}
