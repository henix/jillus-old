package henix.jillus.pegs;

import henix.jillus.GettingMatcher;
import henix.jillus.Mark;
import henix.jillus.PegMatcher;
import henix.jillus.Source;

/**
 * Pass a capture from a sequence of matchers
 */
public class PassCaptureSequence<T> extends GettingMatcher<T> {

	private final PegMatcher before;
	private final GettingMatcher<? extends T> e;
	private final PegMatcher after;

	public PassCaptureSequence(PegMatcher before, GettingMatcher<? extends T> e, PegMatcher after) {
		this.before = before;
		this.e = e;
		this.after = after;
	}

	public PassCaptureSequence(PegMatcher before, GettingMatcher<? extends T> e) {
		this(before, e, null);
	}

	public PassCaptureSequence(GettingMatcher<? extends T> e, PegMatcher after) {
		this(null, e, after);
	}

	@Override
	public T matchAndGet(Source src) {
		final Mark mark = src.mark();
		T ret = null;
		if (before != null && !before.match(src)) {
			src.goback(mark);
			return null;
		}
		ret = e.matchAndGet(src);
		if (ret == null) {
			src.goback(mark);
			return null;
		}
		if (after != null && !after.match(src)) {
			src.goback(mark);
			return null;
		}
		src.cancel(mark);
		return ret;
	}
}
