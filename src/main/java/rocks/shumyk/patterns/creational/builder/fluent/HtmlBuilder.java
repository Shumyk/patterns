package rocks.shumyk.patterns.creational.builder.fluent;

import lombok.extern.slf4j.Slf4j;

/*
	This builder is called fluent cause methods responsive for adding new info into the main object
	return reference for the builder reference, hence user can operate on Fluent Interface.
 */
@Slf4j
public class HtmlBuilder {

	private final String rootName;
	private HtmlElement root = new HtmlElement();

	public HtmlBuilder(String rootName) {
		this.rootName = rootName;
		root.setName(rootName);
	}

	public HtmlBuilder addChild(final String childName, final String childText) {
		final var childElement = new HtmlElement(childName, childText);
		root.getElements().add(childElement);
		return this;
	}

	public HtmlBuilder clear() {
		root = new HtmlElement();
		root.setName(rootName);
		return this;
	}

	@Override
	public String toString() {
		return root.toString();
	}

	public static void main(String[] args) {
		final var builder = new HtmlBuilder("ul")
			.addChild("li", "i")
			.addChild("li", "wanna")
			.addChild("li", "die")
			.clear()
			.addChild("li", "i'm")
			.addChild("li", "good");
		log.info("\n{}", builder);
	}
}
