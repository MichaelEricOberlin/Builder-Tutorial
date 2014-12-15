package oberlin.builder.parser;

import java.util.List;

import Triangle.SyntacticAnalyzer.SourcePosition;
import Triangle.SyntacticAnalyzer.SyntaxError;
import oberlin.builder.parser.ast.AST;

/**
 * Keep closer to the tradition. I'm not convinced that I
 * wasn't on to something with the last version of
 * Parser, but I am fairly certain that in my cognitive
 * overload, I lost it. So start with what I know works.
 * 
 * @author © Michael Eric Oberlin Dec 15, 2014
 *
 */
public class Parser2 {
	//Don't have a scanner, did that all at once in the beginning. So,
	//keep a List<AST> on hand instead.
	
	private final List<AST> astList;
	private ErrorReporter reporter = new ErrorReporter();
	private AST currentToken;
	private SourcePosition currentTokenPosition = new SourcePosition();
	private SourcePosition previousTokenPosition;
	
	public Parser2(List<AST> astList, ErrorReporter reporter) {
		//Do a little defensive programming
		if(astList.isEmpty()) throw new RuntimeException("AST list cannot begin at zero size");
		
		if(reporter != null)
			this.reporter = reporter;
		this.astList = astList;
		
		this.currentToken = astList.get(0);
	}
	
	/**
	 * Checks whether the current node is of the expected type; if so, increments the token; otherwise,
	 * throws a syntactic error.
	 * 
	 * @param astExpected the currently anticipated node type in the list
	 */
	private void accept(Class<? extends AST> astExpected) {
		if(astExpected.isAssignableFrom(currentToken.getClass())) {
			forceAccept();
		} else {
			reporter.error(new SyntaxException("Expected " + astExpected + ", got " + currentToken + " instead; "));
		}
	}
	
	private void forceAccept() {
		previousTokenPosition = currentTokenPosition;
		currentTokenPosition = currentTokenPosition.increment();
		currentToken = astList.get(currentTokenPosition.getStart());
	}
	
	/**
	 * Records the position of the beginning of a phrase.
	 * This is the first AST at the beginning of the phrase.
	 * @param position element to record the begin index into.
	 */
	private void start(SourcePosition position) {
		position.setStart(currentTokenPosition.getStart());
	}
	
	/**
	 * Finish records the position of the end of a phrase.
	 * This is the last AST at the end of the phrase.
	 * @param position element to record the end index into.
	 */
	private void finish(SourcePosition position) {
		position.setFinish(currentTokenPosition.getFinish());
	}
	
	void syntacticError(String messageTemplate, String tokenQuoted) {
		SourcePosition pos = currentTokenPosition;
		reporter.error(new SyntaxException(
				tokenQuoted + " " + messageTemplate + ": " + pos.getStart() + ".." + pos.getFinish()));
	}
	
	public AST parseProgram() {
		//TODO: MICK: I am really not liking having a null here. Look back through it, figure out how to remove
		//it comfortably.
		AST program = null;
		
		previousTokenPosition.setStart(0);
		previousTokenPosition.setFinish(0);
		currentToken = astList.get(0);	//NOTE: there is a serious redundancy here that a decision needs to be made on.
		
		try {
			AST command = parseCommand();
			
		}
		
		
	}
}
