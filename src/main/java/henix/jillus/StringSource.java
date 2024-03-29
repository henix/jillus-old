package henix.jillus;

public class StringSource implements Source {

	private static class IntMark implements Mark {
		public final int pos;
		public IntMark(int pos) {
			this.pos = pos;
		}
	}

	private final String input;

	private int pos;
	private int endpos;

	public StringSource(String input) {
		this.input = input;

		this.pos = 0;
		this.endpos = input.length();
	}

	public boolean canGet() {
		return pos < endpos;
	}

	public boolean canGet(int len) {
		return pos + len <= endpos;
	}

	public char getchar() {
		return input.charAt(pos);
	}

	public String gets(int len) {
		return input.substring(pos, pos + len);
	}

	public void consume() {
		pos++;
	}

	public void consume(int n) {
		pos += n;
	}

	public Mark mark() {
		return new IntMark(pos);
	}

	public void goback(Mark mark) {
		final IntMark intMark = (IntMark)mark;
		if (intMark.pos > pos) {
			throw new IllegalArgumentException("goback can only goto previous pos: " + pos + " -> " + intMark.pos);
		}
		pos = intMark.pos;
	}

	public void cancel(Mark mark) {
	}

	public String tillNow(Mark mark) {
		final IntMark intMark = (IntMark)mark;
		return input.substring(intMark.pos, pos);
	}
}
