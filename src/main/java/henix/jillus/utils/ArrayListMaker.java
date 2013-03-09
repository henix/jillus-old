package henix.jillus.utils;

import java.util.ArrayList;
import java.util.List;

import henix.jillus.ValueMaker;

public class ArrayListMaker<T> implements ValueMaker<List<T>> {

	public List<T> newValue() {
		return new ArrayList<T>();
	}
}
