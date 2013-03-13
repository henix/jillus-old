package henix.jillus.pegs;

import henix.jillus.GettingMatcher;
import henix.jillus.SettingMatcher;
import henix.jillus.Source;
import henix.jillus.ValueMaker;

/**
 * Accumulate inner values to a new value
 *
 * @author henix
 *
 * @param <T> type of new value
 */
public class AccCapture<T> implements GettingMatcher<T> {

	private final SettingMatcher<? super T> e;
	private final ValueMaker<T> valueMaker;

	public AccCapture(ValueMaker<T> valueMaker, SettingMatcher<? super T> e) {
		this.e = e;
		this.valueMaker = valueMaker;
	}

	public T matchAndGet(Source src) {
		final T newObj = valueMaker.newValue();
		if (e.matchAndSet(src, newObj)) {
			return newObj;
		}
		return null;
	}
}
