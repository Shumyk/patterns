package rocks.shumyk.patterns.structural.bridge;

interface Renderer {
	String whatToRenderAs();
}
class VectorRenderer implements Renderer {
	@Override public String whatToRenderAs() {
		return "lines";
	}
}
class RasterRenderer implements Renderer {
	@Override public String whatToRenderAs() {
		return "pixels";
	}
}

abstract class Shape {
	protected final Renderer renderer;

	Shape(Renderer renderer) {
		this.renderer = renderer;
	}

	public abstract String getName();
}

class Triangle extends Shape {
	public Triangle(Renderer renderer) {
		super(renderer);
	}

	@Override public String getName() {
		return "Triangle";
	}

	@Override public String toString() {
		return String.format("Drawing %s as %s", getName(), renderer.whatToRenderAs());
	}
}
// imagine VectorTriangle and RasterTriangle are here too

class Square extends Shape {
	public Square(Renderer renderer) {
		super(renderer);
	}

	@Override public String getName() {
		return "Square";
	}

	@Override public String toString() {
		return String.format("Drawing %s as %s", getName(), renderer.whatToRenderAs());
	}
}

class VectorSquare extends Square {
	public VectorSquare(Renderer renderer) {
		super(renderer);
	}
}

class RasterSquare extends Square {
	public RasterSquare(Renderer renderer) {
		super(renderer);
	}
}
