package henix.jillus.pegs;

import henix.jillus.PegMatcher;
import henix.jillus.Source;

public class CharInRange implements PegMatcher {

	private final char a;
	private final char b;

	public CharInRange(char a, char b) {
		this.a = a;
		this.b = b;
	}

	public boolean match(Source src) {
		if (src.canGet()) {
			char c = src.getchar();
			if (c >= a && c <= b) {
				src.consume();
				return true;
			}
		}
		return false;
	}
}
