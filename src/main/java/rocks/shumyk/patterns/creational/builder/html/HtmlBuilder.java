package rocks.shumyk.patterns.creational.builder.html;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HtmlBuilder {

	private final String rootName;
	private HtmlElement root = new HtmlElement();

	public HtmlBuilder(String rootName) {
		this.rootName = rootName;
		root.setName(rootName);
	}

	public void addChild(final String childName, final String childText) {
		final var childElement = new HtmlElement(childName, childText);
		root.getElements().add(childElement);
	}

	public void clear() {
		root = new HtmlElement();
		root.setName(rootName);
	}

	@Override
	public String toString() {
		return root.toString();
	}

	public static void main(String[] args) {
		final var builder = new HtmlBuilder("ul");
		builder.addChild("li", "hello");
		builder.addChild("li", "world");
		log.info("\n{}", builder);
	}
}
