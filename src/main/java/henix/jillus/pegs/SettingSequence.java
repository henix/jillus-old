package henix.jillus.pegs;

import henix.jillus.Mark;
import henix.jillus.SettingMatcher;
import henix.jillus.Source;

public class SettingSequence<T> implements SettingMatcher<T> {

	private final SettingMatcher<? super T>[] patts;

	public SettingSequence(SettingMatcher<? super T>... patts) {
		this.patts = patts;
	}

	public boolean matchAndSet(Source src, T parentObj) {
		final Mark mark = src.mark();
		for (SettingMatcher<? super T> patt : patts) {
			if (!patt.matchAndSet(src, parentObj)) {
				src.goback(mark);
				return false;
			}
		}
		src.cancel(mark);
		return true;
	}
}
