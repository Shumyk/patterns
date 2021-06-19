package rocks.shumyk.patterns.creational.prototype;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
class Point {
	public int x;
	public int y;
}

@ToString
@AllArgsConstructor
class Line {
	public Point start;
	public Point end;

	public Line deepCopy() {
		return new Line(
			new Point(start.x, start.y),
			new Point(end.x, end.y)
		);
	}
}

/* Given the following class definitions, you are asked to implement 'Line.deepCopy()'
to perform a deep copy of the current 'Line' object */
public class PrototypeCodingExercise {
	public static void main(String[] args) {
		final var line = new Line(new Point(4, 6), new Point(8, 12));
		final var deepCopyLine = line.deepCopy();

		deepCopyLine.start.x = 2;
		deepCopyLine.end.y = 10;

		System.out.println(line);
		System.out.println(deepCopyLine);
	}
}
