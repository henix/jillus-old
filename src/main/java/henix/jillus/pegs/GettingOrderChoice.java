package henix.jillus.pegs;

import henix.jillus.GettingMatcher;
import henix.jillus.Source;

public class GettingOrderChoice<T> extends GettingMatcher<T> {

	private final GettingMatcher<? extends T>[] patts;

	public GettingOrderChoice(GettingMatcher<? extends T>... patts) {
		this.patts = patts;
	}

	public T matchAndGet(Source src) {
		for (GettingMatcher<? extends T> patt : patts) {
			final T ret = patt.matchAndGet(src);
			if (ret != null) {
				return ret;
			}
		}
		return null;
	}
}
