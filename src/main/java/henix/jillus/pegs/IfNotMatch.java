package henix.jillus.pegs;

import henix.jillus.Mark;
import henix.jillus.PegMatcher;
import henix.jillus.Source;

public class IfNotMatch implements PegMatcher {

	private final PegMatcher e1;
	private final PegMatcher e2;

	public IfNotMatch(PegMatcher e1, PegMatcher e2) {
		this.e1 = e1;
		this.e2 = e2;
	}

	public boolean match(Source src) {
		Mark mark = src.mark();
		if (!e1.match(src)) {
			src.cancel(mark);
			return e2.match(src);
		}
		src.goback(mark);
		return false;
	}
}
