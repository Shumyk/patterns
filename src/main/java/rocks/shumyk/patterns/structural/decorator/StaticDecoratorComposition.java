package rocks.shumyk.patterns.structural.decorator;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

class StaticColoredShape<T extends Shape> implements Shape {
	private final Shape shape;
	private final String color;

	public StaticColoredShape(final Supplier<? extends T> shapeSupplier, final String color) {
		this.shape = shapeSupplier.get();
		this.color = color;
	}

	@Override
	public String info() {
		return shape.info() + " has the color " + color;
	}
}

class StaticTransparentShape<T extends Shape> implements Shape {
	private final Shape shape;
	private final int transparency;

	public StaticTransparentShape(final Supplier<? extends T> shapeSupplier, final int transparency) {
		this.shape = shapeSupplier.get();
		this.transparency = transparency;
	}

	@Override
	public String info() {
		return shape.info() + " with " + transparency + "% transparency";
	}
}

@Slf4j
public class StaticDecoratorComposition {
	public static void main(String[] args) {
		final StaticColoredShape<Square> blueSquare = new StaticColoredShape<>(() -> new Square(20f), "blue");
		log.info(blueSquare.info());

		final StaticTransparentShape<StaticColoredShape<Circle>> greenTransparentCircle = new StaticTransparentShape<>(
			() -> new StaticColoredShape<>(() -> new Circle(5f), "green"),
			50
		);
		log.info(greenTransparentCircle.info());
	}
}
