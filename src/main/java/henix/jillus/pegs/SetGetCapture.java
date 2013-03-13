package henix.jillus.pegs;

import henix.jillus.FieldSetter;
import henix.jillus.GettingMatcher;
import henix.jillus.SettingMatcher;
import henix.jillus.Source;

/**
 * Set value from inner GettingMatcher to parent
 *
 * @author henix
 *
 * @param <T> type of parent
 * @param <TI> type of inner value
 */
public class SetGetCapture<T, TI> implements SettingMatcher<T> {

	private final GettingMatcher<? extends TI> e;
	private final FieldSetter<T, TI> fieldSetter;

	public SetGetCapture(FieldSetter<T, TI> fieldSetter, GettingMatcher<? extends TI> e) {
		this.e = e;
		this.fieldSetter = fieldSetter;
	}

	public boolean matchAndSet(Source src, T parentObj) {
		final TI inner = e.matchAndGet(src);
		if (inner != null) {
			fieldSetter.setValue(parentObj, inner);
			return true;
		}
		return false;
	}
}
