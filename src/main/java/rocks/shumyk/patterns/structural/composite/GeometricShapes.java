package rocks.shumyk.patterns.structural.composite;

import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class GraphicObject {

	@Getter @Setter	protected String name = "Group";
	protected String color;
	public final List<GraphicObject> children = new ArrayList<>();

	@Override
	public String toString() {
		final var builder = new StringBuilder();
		print(builder, 0);
		return builder.toString();
	}

	private void print(final StringBuilder stringBuilder, final int depth) {
		stringBuilder.append(String.join("", Collections.nCopies(depth, "*")))
			.append(depth > 0 ? " " : "")
			.append(Strings.isNullOrEmpty(color) ? "" : color + "")
			.append(getName())
			.append(System.lineSeparator());
		for (GraphicObject child : children) {
			child.print(stringBuilder, depth + 1);
		}
	}
}
class Circle extends GraphicObject {
	public Circle(final String color) {
		name = "Circle";
		this.color = color;
	}
}
class Square extends GraphicObject {
	public Square(final String color) {
		name = "Square";
		this.color = color;
	}
}

public class GeometricShapes {
	public static void main(String[] args) {
		final var drawing = new GraphicObject();
		drawing.setName("My Drawing");
		drawing.children.add(new Square("Red"));
		drawing.children.add(new Circle("Yellow"));

		final var group = new GraphicObject();
		group.children.add(new Circle("Blue"));
		group.children.add(new Square("Blue"));

		drawing.children.add(group);

		System.out.println(drawing);
	}
}
