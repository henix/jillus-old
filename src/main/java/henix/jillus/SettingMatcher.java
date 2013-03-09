package henix.jillus;

public abstract class SettingMatcher<T> implements PegMatcher {

	public boolean match(Source src) {
		return matchAndSet(src, null);
	}

	public abstract boolean matchAndSet(Source src, T parentObj);
}
