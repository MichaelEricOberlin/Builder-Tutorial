package oberlin.builder.parser;

public class SourcePosition {
	private int start, finish;
	
	public SourcePosition() {
		setStart(0);
		setFinish(0);
	}
	
	public SourcePosition(int start, int finish) {
		setStart(start);
		setFinish(finish);
	}
	
	//OVERRIDES
	@Override
	public String toString() {
		return "(" + getStart() + ", " + getFinish() + ")";
	}
	//GETTERS/SETTERS
	
	//NOTE: setStart() and setFinish() may be superfluous. In
	//fact, the entire beat approach may be unnecessary.
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getFinish() {
		return finish;
	}

	public void setFinish(int finish) {
		this.finish = finish;
	}
	
	public SourcePosition increment() {
		return new SourcePosition(start + 1, finish + 1);
	}
	
}
