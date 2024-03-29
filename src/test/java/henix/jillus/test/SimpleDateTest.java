package henix.jillus.test;

import static henix.jillus.Pegs.*;

import org.junit.Assert;
import org.junit.Test;

import henix.jillus.GettingMatcher;
import henix.jillus.PegMatcher;
import henix.jillus.StringSource;
import henix.jillus.utils.ToInt;

public class SimpleDateTest {

	public static class MyDate {
		public int year;
		public int month;
		public int day;
	}

	static final PegMatcher digit = charInRange('0', '9');
	static final GettingMatcher<Integer> aint = capture(ToInt.instance, atLeast(1, digit));

	static final GettingMatcher<MyDate> mydate = asStruct(MyDate.class,
		bindField(MyDate.class, Integer.class, "year", aint),
		bindNothing("-"),
		bindField(MyDate.class, Integer.class, "month", aint),
		bindNothing("-"),
		bindField(MyDate.class, Integer.class, "day", aint)
	);

	@Test
	public void main() {
		MyDate result = mydate.matchAndGet(new StringSource("3001-4-1"));
		Assert.assertEquals(3001, result.year);
		Assert.assertEquals(4, result.month);
		Assert.assertEquals(1, result.day);
	}
}
