package rocks.shumyk.patterns.structural.flyweight;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
class FormattedText {
	private final String plainText;
	private final boolean[] capitalized;

	public FormattedText(final String plainText) {
		this.plainText = plainText;
		this.capitalized = new boolean[plainText.length()];
	}

	public void capitalize(int start, int end) {
		for (int i = start; i <= end; i++) {
			capitalized[i] = true;
		}
	}

	@Override
	public String toString() {
		final StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < plainText.length(); i++) {
			final char c = plainText.charAt(i);
			stringBuilder.append(capitalized[i] ? Character.toUpperCase(c) : c);
		}
		return stringBuilder.toString();
	}
}

@Data
class BetterFormattedText {
	private final String plainText;
	private final List<TextRange> formatting = new ArrayList<>();

	public TextRange getRange(int start, int end) {
		final TextRange textRange = new TextRange(start, end);
		formatting.add(textRange);
		return textRange;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < plainText.length(); i++) {
			char c = plainText.charAt(i);
			for (TextRange range : formatting) {
				if (range.covers(i) && range.capitalize) {
					c = Character.toUpperCase(c);
				}
			}
			sb.append(c);
		}
		return sb.toString();
	}
}

/*
Flyweight is hidden here. Instead of heaving array for each formatting option we have just ranges that could be indicating
which way it should be formatted - capitalized, bold, italic, etc.
 */
@Data
class TextRange {
	public final int start;
	public final int end;
	public boolean capitalize, bold, italic;

	public boolean covers(int position) {
		return position >= start && position <= end;
	}
}

@Slf4j
public class TextFormatting {
	public static void main(String[] args) {
		final FormattedText formattedText = new FormattedText("this is a brave new world");
		formattedText.capitalize(10, 15);
		log.info("{}", formattedText);

		final BetterFormattedText bft = new BetterFormattedText("make america great again");
		bft.getRange(13, 18).capitalize = true;
		log.info("{}", bft);
	}
}
