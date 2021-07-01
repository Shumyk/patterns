package rocks.shumyk.patterns.structural.bridge;

interface BasicRenderer {
	String whatToRenderAs();
}
class BasicVectorRenderer implements BasicRenderer {
	@Override public String whatToRenderAs() {
		return "lines";
	}
}
class BasicRasterRenderer implements BasicRenderer {
	@Override public String whatToRenderAs() {
		return "pixels";
	}
}

abstract class ShapeWithRenderer {
	protected final BasicRenderer renderer;

	ShapeWithRenderer(BasicRenderer renderer) {
		this.renderer = renderer;
	}

	public abstract String getName();
}

class Triangle extends ShapeWithRenderer {
	public Triangle(BasicRenderer renderer) {
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

class Square extends ShapeWithRenderer {
	public Square(BasicRenderer renderer) {
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
	public VectorSquare(BasicRenderer renderer) {
		super(renderer);
	}
}

class RasterSquare extends Square {
	public RasterSquare(BasicRenderer renderer) {
		super(renderer);
	}
}
