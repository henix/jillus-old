package henix.jillus;

public interface FieldSetter<T, V> {

	/**
	 * Set one of obj's field to value
	 */
	public void setValue(T obj, V value);
}
