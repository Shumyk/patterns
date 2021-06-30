package rocks.shumyk.patterns.structural.adapter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// simple point
@Data(staticConstructor = "of")
class Point {
	private final int x;
	private final int y;
}

// line which consists of two points
@Data(staticConstructor = "of")
class Line {
	private final Point start;
	private final Point end;
}

class VectorObject extends ArrayList<Line> {}

// vector rectangle which consists of 4 lines
class VectorRectangle extends VectorObject {

	public VectorRectangle(final int x, final int y, final int width, final int height) {
		add(Line.of(Point.of(x, y), Point.of(x + width, y)));
		add(Line.of(Point.of(x + width, y), Point.of(x + width, y + height)));
		add(Line.of(Point.of(x, y), Point.of(x, y + height)));
		add(Line.of(Point.of(x, y + height), Point.of(x + width, y + height)));
	}

	public static VectorRectangle of(final int x, final int y, final int width, final int height) {
		return new VectorRectangle(x, y, width, height);
	}
}

// out adapter for line -> point transformation
@Slf4j
class LineToPointAdapter extends ArrayList<Point> {

	private static int count = 0;

	public LineToPointAdapter(final Line line) {
		log.info("{}: generating point for line [{},{}]-[{},{}] (no caching)", ++count, line.getStart().getX(), line.getStart().getY(), line.getEnd().getX(), line.getEnd().getY());

		final int left = Math.min(line.getStart().getX(), line.getEnd().getX());
		final int right = Math.max(line.getStart().getX(), line.getEnd().getX());
		final int top = Math.min(line.getStart().getY(), line.getEnd().getY());
		final int bottom = Math.max(line.getStart().getY(), line.getEnd().getY());
		final int dx = right - left;
		final int dy = line.getEnd().getY() - line.getStart().getY();

		if (0 == dx) {
			for (int y = top; y <= bottom; ++y) {
				add(Point.of(left, y));
			}
		} else if (0 == dy) {
			for (int x = left; x <= right; ++x) {
				add(Point.of(x, top));
			}
		}
	}
}

@Slf4j
public class VectorRasterDemo {

	private static final List<VectorObject> vectorObject = Arrays.asList(
		VectorRectangle.of(1, 1,10, 10),
		VectorRectangle.of(3, 3,6, 6)
	);

	// [!!!] our API has only method to draw points
	public static void drawPoint(final Point p) {
		log.info("[{}, {}]", p.getX(), p.getY());
	}

	// but what we do posses is bunch of lines -> @vectorObjects (rectangle is lines)
	public static void draw() {
		for (VectorObject vector : vectorObject) {
			for (Line line : vector) {
				// hence we need additional adapter which transform lines to bunch of points
				final LineToPointAdapter adapter = new LineToPointAdapter(line);
				adapter.forEach(VectorRasterDemo::drawPoint);
			}
		}
	}

	public static void main(String[] args) {
		draw();
	}
}
