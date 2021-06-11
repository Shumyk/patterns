package rocks.shumyk.patterns.creational.factory;

import lombok.Data;

@Data
public class PointWithFactoryMethods {
	private final double x;
	private final double y;

//	// instead of having complex constructor with additional enum to define with type of creation to use
// // it could be replaced with factory methods - @newCartesianPoint & @newPolarPoint
//	public enum CoordinateSystem { CARTESIAN, POLAR }
//	public PointWithFactoryMethods(double x, double y, CoordinateSystem cartesianSystem) {
//		switch (cartesianSystem) {
//			case CARTESIAN -> {
//				this.x = x;
//				this.y = y;
//			}
//			case POLAR -> {
//				this.x = x * Math.cos(y);
//				this.y = x * Math.sin(y);
//			}
//		}
//	}

	protected PointWithFactoryMethods(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// Two factory methods inside of data class
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
		final var polarPointFromFactoryMethod = PointWithFactoryMethods.newPolarPoint(2, 3);
		System.out.println(polarPointFromFactoryMethod);
	}
}
