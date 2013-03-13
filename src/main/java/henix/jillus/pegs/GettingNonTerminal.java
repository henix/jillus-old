package henix.jillus.pegs;

import henix.jillus.GettingMatcher;
import henix.jillus.Source;

public class GettingNonTerminal<T> implements GettingMatcher<T> {

	private GettingMatcher<? extends T> actual;

	public void set(GettingMatcher<? extends T> actual) {
		this.actual = actual;
	}

	public T matchAndGet(Source src) {
		return actual.matchAndGet(src);
	}
}
