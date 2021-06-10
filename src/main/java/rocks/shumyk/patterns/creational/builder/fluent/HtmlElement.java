package rocks.shumyk.patterns.creational.builder.fluent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HtmlElement {

	private String name;
	private String text;

	private final List<HtmlElement> elements = new ArrayList<>();

	private static final int INDENT_SIZE = 2;
	private static final String NEW_LINE = System.lineSeparator();

	private String toStringImpl(final int indent) {
		final var builder = new StringBuilder();
		final var indentation = indent(indent);

		builder.append(String.format("%s<%s>%s", indentation, name, NEW_LINE));
		if (!Strings.isEmpty(text)) {
			builder.append(indent(indent + 1))
				.append(text)
				.append(NEW_LINE);
		}

		elements.forEach(element -> builder.append(element.toStringImpl(indent + 1)));

		builder.append(String.format("%s</%s>%s", indentation, name, NEW_LINE));
		return builder.toString();
	}

	private String indent(final int indent) {
		return String.join("", Collections.nCopies(INDENT_SIZE * indent, " "));
	}

	@Override
	public String toString() {
		return toStringImpl(0);
	}
}
