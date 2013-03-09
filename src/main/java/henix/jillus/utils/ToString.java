package henix.jillus.utils;

import henix.jillus.ValueCreator;

public class ToString implements ValueCreator<String> {

	public static final ToString instance = new ToString();

	public String create(String capture) {
		return capture;
	}
}
