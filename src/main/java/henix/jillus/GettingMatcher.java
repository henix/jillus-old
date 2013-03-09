package henix.jillus;

public abstract class GettingMatcher<T> implements PegMatcher {

	public boolean match(Source src) {
		return matchAndGet(src) != null;
	}

	public abstract T matchAndGet(Source src);
}
