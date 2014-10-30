package oberlin.builder;

/**
 * Honestly, the original design called for integer literals to be passed to each AST for the sake of its beginning
 * and its end within the code. Those are all that this class represents, and there is a strong temptation to remove
 * it, as it feels like overprogramming. However, for the sake of keeping the code readable, it exists, and does
 * exclusively just that—it denotes where an AST begins, and where it ends.
 * 
 * @author © Michael Eric Oberlin Oct 29, 2014
 *
 */
public class Position {
	
	private final int begin, end;
	
	public Position(int begin, int end) {
		this.begin = begin;
		this.end = end;
	}

	protected int getBegin() {
		return begin;
	}

	protected int getEnd() {
		return end;
	}

}
