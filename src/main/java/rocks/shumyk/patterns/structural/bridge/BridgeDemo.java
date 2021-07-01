package rocks.shumyk.patterns.structural.bridge;


import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

// Shape -> Circle, Square
// Rendering -> Vector, Raster

interface Renderer {
	void renderCircle(float radius);
}

@Slf4j
class VectorRenderer implements Renderer {
	@Override public void renderCircle(final float radius) {
		log.info("Drawing a circle of radius: {}", radius);
	}
}

@Slf4j
class RasterRenderer implements Renderer {
	@Override public void renderCircle(final float radius) {
		log.info("Drawing pixels for a circle of radius {}", radius);
	}
}

@AllArgsConstructor
abstract class Shape {
	protected Renderer renderer;

	public abstract void draw();
	public abstract void resize(float factor);
}

class Circle extends Shape {

	@Setter @Getter	private float radius;

	@Inject
	public Circle(final Renderer renderer) {
		super(renderer);
	}

	public Circle(final Renderer renderer, final float radius) {
		super(renderer);
		this.radius = radius;
	}

	@Override
	public void draw() {
		renderer.renderCircle(radius);
	}

	@Override
	public void resize(final float factor) {
		radius *= factor;
	}
}

class ShapeModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(Renderer.class).to(VectorRenderer.class);
	}
}

public class BridgeDemo {
	public static void main(String[] args) {
		final var injector = Guice.createInjector(new ShapeModule());
		final var instance = injector.getInstance(Circle.class);
		instance.setRadius(3);
		instance.draw();
		instance.resize(2);
		instance.draw();
	}

	private static void manualInjectingOfBridge() {
		final var vector = new VectorRenderer();

		final var circle = new Circle(vector, 5);
		circle.draw();
		circle.resize(2);
		circle.draw();
	}
}
