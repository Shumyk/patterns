package rocks.shumyk.patterns.creational.builder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HtmlStringBuilderDemo {

	public static void main(String[] args) {
		final var hello = "hello";
		log.info("<p>" + hello + "</p>");

		final String[] words = { "hello", "world" };
		final var sb = new StringBuilder(System.lineSeparator());

		sb.append("<ul>\n");
		for (String word : words) {
			sb.append(String.format("  <li>%s</li>", word));
			sb.append(System.lineSeparator());
		}
		sb.append("</ul>");

		log.info(sb.toString());
	}
}
