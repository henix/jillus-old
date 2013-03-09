package henix.jillus.pegs;

import henix.jillus.PegMatcher;
import henix.jillus.Source;

public class NonTerminal implements PegMatcher {

	private PegMatcher actual;

	public void set(PegMatcher actual) {
		this.actual = actual;
	}

	public boolean match(Source src) {
		return actual.match(src);
	}
}
