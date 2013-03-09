package henix.jillus.pegs;

import henix.jillus.Mark;
import henix.jillus.PegMatcher;
import henix.jillus.Source;

public class Sequence implements PegMatcher {

	private final PegMatcher[] patts;

	public Sequence(PegMatcher... patts) {
		this.patts = patts;
	}

	public boolean match(Source src) {
		final Mark mark = src.mark();
		for (PegMatcher patt : patts) {
			if (!patt.match(src)) {
				src.goback(mark);
				return false;
			}
		}
		src.cancel(mark);
		return true;
	}
}
