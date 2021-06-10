package rocks.shumyk.patterns.creational.builder.faceted;

public class PersonAddressBuilder extends PersonBuilder {

	public PersonAddressBuilder(final Person person) {
		this.person = person;
	}

	public PersonAddressBuilder at(final String streetAddress) {
		person.setStreetAddress(streetAddress);
		return this;
	}

	public PersonAddressBuilder withPostcode(final String postcode) {
		person.setPostcode(postcode);
		return this;
	}

	public PersonAddressBuilder in(final String city) {
		person.setCity(city);
		return this;
	}
}
