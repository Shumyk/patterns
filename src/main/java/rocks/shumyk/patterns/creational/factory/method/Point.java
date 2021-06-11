package rocks.shumyk.patterns.creational.factory.method;

import lombok.Data;

@Data
public class Point {
	private double x;
	private double y;

//	// instead of having complex constructor with additional enum to define with type of creation to use
// // it could be replaced with factory methods - @newCartesianPoint & @newPolarPoint
//	public enum CoordinateSystem { CARTESIAN, POLAR }
//	public Point(double x, double y, CoordinateSystem cartesianSystem) {
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

	private Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public static Point newCartesianPoint(double x, double y) {
		return new Point(x, y);
	}

	public static Point newPolarPoint(double rho, double theta) {
		return new Point(
			rho * Math.cos(theta),
			rho * Math.sin(theta)
		);
	}

	public static void main(String[] args) {
		final var point = Point.newPolarPoint(2, 3);
		System.out.println(point);
	}
}
