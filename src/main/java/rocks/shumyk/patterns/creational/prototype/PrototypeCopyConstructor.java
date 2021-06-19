package rocks.shumyk.patterns.creational.prototype;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
class CityAddress {
	public String streetAddress;
	public String city;
	public String country;

	public CityAddress(final CityAddress cityAddress) {
		this(cityAddress.streetAddress, cityAddress.city, cityAddress.country);
	}
}

@ToString
@AllArgsConstructor
class Employee {
	public String name;
	public CityAddress address;

	public Employee(final Employee employee) {
		this(employee.name, new CityAddress(employee.address));
	}
}

// showcase of prototype pattern using copy constructors
public class PrototypeCopyConstructor {
	public static void main(String[] args) {
		final var john = new Employee("John", new CityAddress("123 Ivanychi Street", "Ivanychi", "UA"));

		final var chris = new Employee(john);
		chris.name = "Chris";
		chris.address.streetAddress = "12 Zavods'ka Street";
		System.out.println(john);
		System.out.println(chris);
	}
}
