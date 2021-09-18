package rocks.shumyk.patterns.structural.proxy;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

interface Drivable {
	void drive();
}

@Slf4j
@RequiredArgsConstructor
class Car implements Drivable {
	protected final Driver driver;

	@Override
	public void drive() {
		log.info("car being driven");
	}
}

@Data
class Driver {
	public final int age;
}

@Slf4j
class CarProxy extends Car {
	public CarProxy(final Driver driver) {
		super(driver);
	}

	@Override
	public void drive() {
		if (driver.age >= 16)
			super.drive();
		else
			log.error("Driver too young!");
	}
}

public class ProtectionProxy {
	public static void main(String[] args) {
		final Drivable carWithTooYoungDriver = new Car(new Driver(12));
		carWithTooYoungDriver.drive(); // this is a problem - cause driver too young

		// and here using protection proxy car wouldn't be driven, cause driver is too young
		// and for application CarProxy can be configured to be injected everywhere
		// since it inherits from Car
		final Drivable carProxy = new CarProxy(new Driver(12));
		carProxy.drive();
	}
}
