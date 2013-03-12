package henix.jillus.pegs;

import henix.jillus.PegMatcher;
import henix.jillus.Source;

public class AtMost implements PegMatcher {

	private final int n;
	private final PegMatcher e;

	public AtMost(int n, PegMatcher e) {
		this.n = n;
		this.e = e;
	}

	public boolean match(Source src) {
		for (int i = 0; i < n; i++) {
			if (!e.match(src)) {
				return true;
			}
		}
		return true;
	}
}
