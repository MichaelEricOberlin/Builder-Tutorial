package oberlin.builder;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.*;

import oberlin.builder.parser.ast.*;

public interface TerminalSpelling {
	
	public default List<AST> matchToken(AST ast) throws MismatchException {
		List<AST> returnable = new LinkedList<>();
		
		Pattern pattern = getPattern();
		Matcher matcher = pattern.matcher(ast.toString());
		if(matcher.find()) {
			returnable.addAll(manageToken(matcher));
			returnable.add(new Terminal(ast.toString().substring(matcher.end())));
			return returnable;
		} else throw new MismatchException("String \"" + ast + "\" does not match grammar pattern + \"" + pattern + "\"");
	}
	
	public Pattern getPattern();
	
	/**
	 * Determines, often through enumeration self-inspection, what should be done with a passed token.
	 * 
	 * @param token the original token removed from the complete String by matchToken
	 * @return appropriate value given the circumstances and implementation of TerminalSpelling.
	 */
	public List<AST> manageToken(Matcher matcher);
}
