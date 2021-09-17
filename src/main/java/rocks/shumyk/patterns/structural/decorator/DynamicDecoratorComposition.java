package rocks.shumyk.patterns.structural.decorator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

interface Shape {
	String info();
}
@NoArgsConstructor
@AllArgsConstructor
final class Circle implements Shape {
	private float radius;

	public void resize(final float factor) {
		radius *= factor;
	}

	@Override
	public String info() {
		return String.format("A circle of radius %f", radius);
	}
}
@NoArgsConstructor
@AllArgsConstructor
final class Square implements Shape {
	private float side;

	@Override
	public String info() {
		return String.format("A square with side %f", side);
	}
}

/*
  Let's imagine that we have Circle and Square classed that we want to extend with Color state and functionality.
  But it happens that these classes are final, or we don't want or can't change them.
  Therefore, Decorator pattern comes in use.
 */
@RequiredArgsConstructor
class ColoredShape implements Shape {
	private final Shape shape;
	private final String color;

	@Override
	public String info() {
		return shape.info() + " has the color " + color;
	}
}
@RequiredArgsConstructor
class TransparentShape implements Shape {
	private final Shape shape;
	private final int transparency;

	@Override
	public String info() {
		return shape.info() + " has " + transparency + "% transparency";
	}
}

/*
 So, here constructed decorator pattern implementing common interface with adding new functionality or state.
 For this it needs to inject decorated object and then add new functionality.
 Notice, that functionality of injected class won't be accessible from outer decorator. For example, you inject Circle into TransparentShape ->
 Circle.resize() won't be available at TransparentShape object.
 */
@Slf4j
public class DynamicDecoratorComposition {
	public static void main(String[] args) {
		final Circle circle = new Circle(10f);
		log.info(circle.info());

		final ColoredShape greenSquare = new ColoredShape(new Square(20f), "green");
		log.info(greenSquare.info());


		final TransparentShape yellowHalfTransparentCircle = new TransparentShape(
			new ColoredShape(new Circle(5f), "yellow"),
			50
		);
		log.info(yellowHalfTransparentCircle.info());
	}
}
