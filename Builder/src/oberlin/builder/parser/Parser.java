package oberlin.builder.parser;

import java.util.List;

import oberlin.builder.parser.ast.AST;
import oberlin.builder.parser.ast.EOT;
import oberlin.builder.reporter.ErrorReporter;
import oberlin.builder.visitor.Visitor;

/**
 * Keep closer to the tradition. I'm not convinced that I
 * wasn't on to something with the last version of
 * Parser, but I am fairly certain that in my cognitive
 * overload, I lost it. So start with what I know works.
 * 
 * @author © Michael Eric Oberlin Dec 15, 2014
 *
 * @param <V> the visitor type that the parser uses for creating nonterminal nodes
 * @param <P> the target class for the parsing, intended to be the root of the produced syntax tree
 */
public abstract class Parser<V extends Visitor> {
	//Don't have a scanner, did that all at once in the beginning. So,
	//keep a List<AST> on hand instead.
	
	private final List<AST> astList;
	private ErrorReporter reporter = new ErrorReporter();
	private AST currentToken;
	private SourcePosition currentTokenPosition = new SourcePosition();
	private SourcePosition previousTokenPosition = new SourcePosition();
	protected V visitor;
	
	public Parser(V visitor, List<AST> astList, ErrorReporter reporter) {
		this.visitor = visitor;
		
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
	public void accept(Class<? extends AST> astExpected) {
		if(astExpected.isAssignableFrom(currentToken.getClass())) {
			forceAccept();
		} else {
			reporter.error(new SyntaxException("Expected " + astExpected + ", got " + currentToken + " instead; "));
		}
	}
	
	public void forceAccept() {
		previousTokenPosition = currentTokenPosition;
		currentTokenPosition = currentTokenPosition.increment();
		try {
			currentToken = astList.get(currentTokenPosition.getStart());
		} catch(IndexOutOfBoundsException ex) {
			currentToken = new EOT();
		}
	}
	
	/**
	 * Records the position of the beginning of a phrase.
	 * This is the first AST at the beginning of the phrase.
	 * @param position element to record the begin index into.
	 */
	public void start(SourcePosition position) {
		position.setStart(currentTokenPosition.getStart());
	}
	
	/**
	 * Finish records the position of the end of a phrase.
	 * This is the last AST at the end of the phrase.
	 * @param position element to record the end index into.
	 */
	public void finish(SourcePosition position) {
		position.setFinish(currentTokenPosition.getFinish());
	}
	
	public void syntacticError(String messageTemplate, String tokenQuoted) {
		SourcePosition pos = currentTokenPosition;
		reporter.error(new SyntaxException(
				tokenQuoted + " " + messageTemplate + ": " + pos.getStart() + ".." + pos.getFinish()));
	}
	
	/**
	 * Begin parsing, aiming to create the provided class as a root class for the abstract syntax tree.
	 * 
	 * @param rootClass Class of object which should, provided no exceptions, be a tree root.
	 * @return complete tree, stemming from class rootClass, expressing program.
	 */
	public AST parse(Class<? extends AST> rootClass) {
		return visitor.visit(rootClass, this, currentTokenPosition);
	}

	public SourcePosition getPreviousTokenPosition() {
		return this.previousTokenPosition;
	}
	
	public SourcePosition getCurrentTokenPosition() {
		return this.currentTokenPosition;
	}

	public AST getCurrentToken() {
		return currentToken;
	}
	
	public V getVisitor() {
		return visitor;
	}
	
	public ErrorReporter getErrorReporter() {
		return reporter;
	}

}
