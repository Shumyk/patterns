package rocks.shumyk.patterns.creational.singelton;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Monostate {
	public static void main(String[] args) {
		var ceo = new ChiefExecutiveOfficer();
		ceo.setName("Adam Havdyk");
		ceo.setAge(25);

		var ceo2 = new ChiefExecutiveOfficer();
		log.info("{}", ceo2);
	}
}

// implementing mono-state object
// it shares singleton info about this object via static fields
// anyway it is not the best idea cause it can be confusing to use this global variables
// and when you create new instance - you receive object with already defined variables
@Getter
class ChiefExecutiveOfficer {
	private static String name;
	private static int age;

	public void setName(final String name) {
		ChiefExecutiveOfficer.name = name;
	}

	public void setAge(final int age) {
		ChiefExecutiveOfficer.age = age;
	}

	@Override
	public String toString() {
		return "ChiefExecutiveOfficer(name=" + ChiefExecutiveOfficer.name
			+ ", age=" + age + ")";
	}
}
