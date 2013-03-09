package henix.jillus.pegs;

import henix.jillus.Mark;
import henix.jillus.PegMatcher;
import henix.jillus.SettingMatcher;
import henix.jillus.Source;

public class SettingIfNotMatch<T> extends SettingMatcher<T> {

	private final PegMatcher cond;
	private final SettingMatcher<? super T> e;

	public SettingIfNotMatch(PegMatcher cond, SettingMatcher<? super T> e) {
		this.cond = cond;
		this.e = e;
	}

	@Override
	public boolean matchAndSet(Source src, T parentObj) {
		final Mark mark = src.mark();
		if (!cond.match(src)) {
			src.cancel(mark);
			return e.matchAndSet(src, parentObj);
		}
		src.goback(mark);
		return false;
	}
}
