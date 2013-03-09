package henix.jillus.pegs;

import henix.jillus.PegMatcher;
import henix.jillus.Source;

public class OrderChoice implements PegMatcher {

	private final PegMatcher[] patts;

	public OrderChoice(PegMatcher... patts) {
		this.patts = patts;
	}

	public boolean match(Source src) {
		for (PegMatcher patt : patts) {
			if (patt.match(src)) {
				return true;
			}
		}
		return false;
	}
}
