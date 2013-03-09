package henix.jillus.pegs;

import henix.jillus.PegMatcher;
import henix.jillus.Source;

public class AlwaysSuccess implements PegMatcher {

	public static final AlwaysSuccess instance = new AlwaysSuccess();

	public boolean match(Source src) {
		return true;
	}
}
