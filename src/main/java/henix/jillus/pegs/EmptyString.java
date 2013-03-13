package henix.jillus.pegs;

import henix.jillus.PegMatcher;
import henix.jillus.Source;

public class EmptyString implements PegMatcher {

	public static final EmptyString instance = new EmptyString();

	public boolean match(Source src) {
		return true;
	}
}
