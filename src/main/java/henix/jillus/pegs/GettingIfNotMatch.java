package henix.jillus.pegs;

import henix.jillus.GettingMatcher;
import henix.jillus.Mark;
import henix.jillus.PegMatcher;
import henix.jillus.Source;

public class GettingIfNotMatch<T> extends GettingMatcher<T> {

	private final PegMatcher cond;
	private final GettingMatcher<? extends T> e;

	public GettingIfNotMatch(PegMatcher cond, GettingMatcher<? extends T> e) {
		this.cond = cond;
		this.e = e;
	}

	@Override
	public T matchAndGet(Source src) {
		final Mark mark = src.mark();
		if (!cond.match(src)) {
			src.cancel(mark);
			return e.matchAndGet(src);
		}
		src.goback(mark);
		return null;
	}
}
