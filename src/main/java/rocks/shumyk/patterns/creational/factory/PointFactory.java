package rocks.shumyk.patterns.creational.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PointFactory {
	public static PointWithFactoryMethods newCartesianPoint(double x, double y) {
		return new PointWithFactoryMethods(x, y);
	}

	public static PointWithFactoryMethods newPolarPoint(double rho, double theta) {
		return new PointWithFactoryMethods(
			rho * Math.cos(theta),
			rho * Math.sin(theta)
		);
	}

	public static void main(String[] args) {
		final var polarPointFromFactory = PointFactory.newPolarPoint(2, 3);
		System.out.println(polarPointFromFactory);
	}
}
