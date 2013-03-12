package henix.jillus.pegs;

import henix.jillus.SettingMatcher;
import henix.jillus.Source;

public class SettingOrderChoice<T> extends SettingMatcher<T> {

	private final SettingMatcher<? super T>[] patts;

	public SettingOrderChoice(SettingMatcher<? super T>... patts) {
		this.patts = patts;
	}

	public boolean matchAndSet(Source src, T parentObj) {
		for (SettingMatcher<? super T> patt : patts) {
			if (patt.matchAndSet(src, parentObj)) {
				return true;
			}
		}
		return false;
	}
}
