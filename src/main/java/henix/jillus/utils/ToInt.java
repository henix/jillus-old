package henix.jillus.utils;

import henix.jillus.ValueCreator;

public class ToInt implements ValueCreator<Integer> {

	public static final ToInt instance = new ToInt();

	public Integer create(String capture) {
		return Integer.valueOf(capture);
	}
}
