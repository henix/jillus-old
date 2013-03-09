package henix.jillus.pegs;

import henix.jillus.PegMatcher;
import henix.jillus.Source;

public class AnyChar implements PegMatcher {

	public static AnyChar instance = new AnyChar();

	public boolean match(Source src) {
		if (src.canGet()) {
			src.consume();
			return true;
		}
		return false;
	}
}
