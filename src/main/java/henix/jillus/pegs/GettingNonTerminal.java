package henix.jillus.pegs;

import henix.jillus.GettingMatcher;
import henix.jillus.Source;

public class GettingNonTerminal<T> extends GettingMatcher<T> {

	private GettingMatcher<? extends T> actual;

	public void set(GettingMatcher<? extends T> actual) {
		this.actual = actual;
	}

	@Override
	public T matchAndGet(Source src) {
		return actual.matchAndGet(src);
	}
}
