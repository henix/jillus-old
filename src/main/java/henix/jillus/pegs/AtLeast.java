package henix.jillus.pegs;

import henix.jillus.Mark;
import henix.jillus.PegMatcher;
import henix.jillus.Source;

public class AtLeast implements PegMatcher {

	private final int n;
	private final PegMatcher e;

	public AtLeast(int n, PegMatcher e) {
		this.n = n;
		this.e = e;
	}

	public boolean match(Source src) {
		final Mark mark = src.mark();
		for (int i = 0; i < n; i++) {
			if (!e.match(src)) {
				src.goback(mark);
				return false;
			}
		}
		while (e.match(src)) {
			;
		}
		src.cancel(mark);
		return true;
	}
}
