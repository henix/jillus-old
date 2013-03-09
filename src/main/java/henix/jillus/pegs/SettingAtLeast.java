package henix.jillus.pegs;

import henix.jillus.Mark;
import henix.jillus.SettingMatcher;
import henix.jillus.Source;

public class SettingAtLeast<T> extends SettingMatcher<T> {

	private final int n;
	private final SettingMatcher<? super T> e;

	public SettingAtLeast(int n, SettingMatcher<? super T> e) {
		this.n = n;
		this.e = e;
	}

	@Override
	public boolean matchAndSet(Source src, T parentObj) {
		final Mark mark = src.mark();
		for (int i = 0; i < n; i++) {
			if (!e.matchAndSet(src, parentObj)) {
				src.goback(mark);
				return false;
			}
		}
		while (e.matchAndSet(src, parentObj)) {
			;
		}
		src.cancel(mark);
		return true;
	}
}
