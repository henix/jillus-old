package henix.jillus.pegs;

import henix.jillus.PegMatcher;
import henix.jillus.Source;

public class Literal implements PegMatcher {

	private final String s;
	private final int len;

	public Literal(String s) {
		this.s = s;
		this.len = s.length();
	}

	public boolean match(Source src) {
		if (src.canGet(len)) {
			if (src.gets(len).equals(s)) {
				src.consume(len);
				return true;
			}
		}
		return false;
	}
}
