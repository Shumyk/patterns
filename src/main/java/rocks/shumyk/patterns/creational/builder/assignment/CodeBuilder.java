package rocks.shumyk.patterns.creational.builder.assignment;

import java.util.Collections;

public class CodeBuilder {

	private final StringBuilder builder = new StringBuilder();

	private static final String INDENTATION = String.join("", Collections.nCopies(2, " "));
	private static final String NEW_LINE = System.lineSeparator();
	private static final String OPEN_BRACKET = "{";
	private static final String CLOSE_BRACKET = "}";
	private static final String SPACE = " ";
	private static final String SEMICOLON = ";";

	public CodeBuilder(final String className) {
		builder.append("public class ")
			.append(className)
			.append(NEW_LINE)
			.append(OPEN_BRACKET)
			.append(NEW_LINE);
	}

	public CodeBuilder addField(final String name, final String type) {
		builder.append(INDENTATION)
			.append("public ")
			.append(type)
			.append(SPACE)
			.append(name)
			.append(SEMICOLON)
			.append(NEW_LINE);

		return this;
	}

	@Override
	public String toString() {
		builder.append(CLOSE_BRACKET);
		return builder.toString();
	}

	public static void main(String[] args) {
		CodeBuilder builder = new CodeBuilder("Person")
			.addField("name", "String")
			.addField("age", "int");
		System.out.println(builder);
	}
}
