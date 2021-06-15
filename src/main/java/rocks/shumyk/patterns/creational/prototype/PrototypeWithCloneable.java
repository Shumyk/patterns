package rocks.shumyk.patterns.creational.prototype;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
class Address implements Cloneable {
	public String streetName;
	public int houseNumber;

	//	returns new instance with existing fields
	@Override
	public Address clone() {
		return new Address(streetName, houseNumber);
	}
}

@ToString
@AllArgsConstructor
class Person implements Cloneable {
	public String[] names;
	public Address address;

	// returns new instance with cloned array of strings and cloned address
	@Override
	public Person clone() {
		return new Person(
			names.clone(),
			address.clone()
		);
	}
}

// showcase of using cloneable approach, while it is not recommended to use (potential bugs as a cause of trickiness of implementation)
// instead use copy constructor, copy factory, builder copy
public class PrototypeWithCloneable {
	public static void main(String[] args) throws CloneNotSupportedException {
		final var john = new Person(new String[]{"John", "Smith"}, new Address("London Road", 123));

		final var jane = john.clone();
		jane.names[0] = "Jane";
		jane.address.houseNumber = 124;

		System.out.println(john);
		System.out.println(jane);
	}
}