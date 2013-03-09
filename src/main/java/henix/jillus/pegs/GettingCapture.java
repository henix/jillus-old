package henix.jillus.pegs;

import henix.jillus.GettingMatcher;
import henix.jillus.Mark;
import henix.jillus.PegMatcher;
import henix.jillus.Source;
import henix.jillus.ValueCreator;

public class GettingCapture<T> extends GettingMatcher<T> {

	private final PegMatcher e;
	private final ValueCreator<T> valueCreator;

	public GettingCapture(ValueCreator<T> valueCreator, PegMatcher e) {
		this.valueCreator = valueCreator;
		this.e = e;
	}

	@Override
	public T matchAndGet(Source src) {
		final Mark mark = src.mark();
		if (e.match(src)) {
			return valueCreator.create(src.tillNow(mark));
		}
		src.cancel(mark);
		return null;
	}
}
