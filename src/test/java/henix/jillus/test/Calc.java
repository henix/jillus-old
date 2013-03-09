package henix.jillus.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import static henix.jillus.Pegs.*;

import henix.jillus.*;
import henix.jillus.pegs.*;
import henix.jillus.test.Calc.BinaryExpr.PartExp;
import henix.jillus.utils.ToString;

public class Calc {

	// ASTs
	public static abstract class Expr {
	}

	public static class IntLiteral extends Expr {
		public final int value;
		public IntLiteral(int value) {
			this.value = value;
		}
	}

	public static class BinaryExpr extends Expr {
		public static class PartExp {
			public String op;
			public Expr expr;
		}
		public Expr first;
		public List<PartExp> others;
	}

	static final PegMatcher digit = charInRange('0', '9');

	static final PegMatcher space = charInSet(" \t");

	static final PegMatcher spaces = atLeast(0, space);

	/**
	 * convenient function for creating punctuations
	 */
	static PegMatcher punct(PegMatcher e) {
		return sequence(spaces, e, spaces);
	}

	static PegMatcher punct(String s) {
		return punct(new Literal(s));
	}

	static <E> PassCaptureSequence<E> punct(GettingMatcher<E> e) {
		return passCapture(spaces, e, spaces);
	}

	static final ValueCreator<IntLiteral> toIntLiteral = new ValueCreator<IntLiteral>() {
		public IntLiteral create(String capture) {
			return new IntLiteral(Integer.parseInt(capture));
		}
	};

	static final GettingCapture<IntLiteral> IntLiteral = capture(toIntLiteral, atLeast(1, digit));

	static final GettingNonTerminal<Expr> TermExpr = new GettingNonTerminal<Expr>();

	static final GettingNonTerminal<BinaryExpr> MulExpr = new GettingNonTerminal<BinaryExpr>();

	static final GettingNonTerminal<BinaryExpr> AddExpr = new GettingNonTerminal<BinaryExpr>();

	static {
		// TermExpr := IntLiteral / "(" AddExpr ")"
		TermExpr.set(orderChoice(IntLiteral, passCapture(punct("("), AddExpr, punct(")"))));

		// MulExpr := TermExpr ( [*/] TermExpr )*
		MulExpr.set(asStruct(BinaryExpr.class,
			bindField(BinaryExpr.class, Expr.class, "first", TermExpr),
			bindField(BinaryExpr.class, List.class, "others", asList(0, asStruct(PartExp.class,
				bindNothing(spaces),
				bindField(PartExp.class, String.class, "op", capture(ToString.instance, charInSet("*/"))),
				bindNothing(spaces),
				bindField(PartExp.class, Expr.class, "expr", TermExpr)
			)))
		));

		// AddExpr := MulExpr ( [+-] MulExpr )*
		AddExpr.set(asStruct(BinaryExpr.class,
			bindField(BinaryExpr.class, Expr.class, "first", MulExpr),
			bindField(BinaryExpr.class, List.class, "others", asList(0, asStruct(PartExp.class,
				bindField(PartExp.class, String.class, "op", punct(capture(ToString.instance, charInSet("+-")))),
				bindField(PartExp.class, Expr.class, "expr", MulExpr)
			)))
		));
	}

	// allow spaces at start and end of input
	static final GettingMatcher<BinaryExpr> all = passCapture(spaces, AddExpr, sequence(spaces, eof()));

	static int eval(Expr expr) {
		if (expr instanceof IntLiteral) {
			IntLiteral literal = (IntLiteral)expr;
			return literal.value;
		} else if (expr instanceof BinaryExpr) {
			BinaryExpr binaryExpr = (BinaryExpr)expr;
			int v = eval(binaryExpr.first);
			for (PartExp partExp : binaryExpr.others) {
				int v2 = eval(partExp.expr);
				if (partExp.op.equals("+")) {
					v += v2;
				} else if (partExp.op.equals("-")) {
					v -= v2;
				} else if (partExp.op.equals("*")) {
					v *= v2;
				} else if (partExp.op.equals("/")) {
					v /= v2;
				}
			}
			return v;
		} else {
			throw new IllegalArgumentException("Unknown type: " + expr.getClass().getName());
		}
	}

	static int calculate(String str) {
		return eval(all.matchAndGet(new StringSource(str)));
	}

	@Test
	public void main() {
		Assert.assertEquals(3, calculate(" 1+ 2 "));
		Assert.assertEquals(7, calculate("1+ 2 * 3"));
		Assert.assertEquals(42, calculate("(1 + 5) * (3 + 4) "));
	}
}
