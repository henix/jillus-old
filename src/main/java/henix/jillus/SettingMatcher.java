package henix.jillus;

public interface SettingMatcher<T> {
	boolean matchAndSet(Source src, T parentObj);
}
