package henix.jillus.pegs;

import henix.jillus.PegMatcher;
import henix.jillus.Source;

public class CharInSet implements PegMatcher {

	private final String s;

	public CharInSet(String s) {
		this.s = s;
	}

	public boolean match(Source src) {
		if (src.canGet()) {
			char c = src.getchar();
			if (s.indexOf(c) != -1) {
				src.consume();
				return true;
			}
		}
		return false;
	}
}
