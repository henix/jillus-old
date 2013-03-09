package henix.jillus.pegs;

import henix.jillus.PegMatcher;
import henix.jillus.SettingMatcher;
import henix.jillus.Source;

/**
 * Convert a PegMatcher to a SettingMatcher, but set nothing.
 *
 * So that SettingSequence can take a list of SettingMatcher instead of PegMatcher
 *
 * @author henix
 */
public class FakeSettingMatcher extends SettingMatcher<Object> {

	private final PegMatcher e;

	public FakeSettingMatcher(PegMatcher e) {
		this.e = e;
	}

	@Override
	public boolean matchAndSet(Source src, Object parentObj) {
		return e.match(src);
	}
}
