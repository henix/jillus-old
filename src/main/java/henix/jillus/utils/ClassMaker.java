package henix.jillus.utils;

import henix.jillus.ValueMaker;

public class ClassMaker<T> implements ValueMaker<T> {

	private final Class<T> klass;

	public ClassMaker(Class<T> klass) {
		this.klass = klass;
	}

	public T newValue() {
		try {
			return klass.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
