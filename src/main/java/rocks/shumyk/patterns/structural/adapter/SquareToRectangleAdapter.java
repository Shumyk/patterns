package rocks.shumyk.patterns.structural.adapter;

class Square {
	public final int side;

	public Square(int side) {
		this.side = side;
	}
}

interface Rectangle {
	int getWidth();

	int getHeight();

	default int getArea() {
		return getWidth() * getHeight();
	}
}

public class SquareToRectangleAdapter implements Rectangle {

	private final int side;

	public SquareToRectangleAdapter(Square square) {
		this.side = square.side;
	}

	@Override
	public int getWidth() {
		return side;
	}

	@Override
	public int getHeight() {
		return getWidth();
	}
}
