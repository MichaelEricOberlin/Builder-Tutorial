package oberlin.algebra.builder;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oberlin.builder.TerminalSpelling;
import oberlin.builder.Terminal;
import oberlin.builder.parser.ast.AST;

/*
 * Major changes:
 * For starters, if a chunk of the string matches a pattern, then that group
 * needs to be returned as the spelling of a token.
 * <Specific Atomic Type> ← Token ← Terminal ← AST
 * 
 * All tokens should contain a reference to their regular expression/grammar.
 * 
 */
public enum AlgebraicSpelling implements TerminalSpelling {
		//COMMENTS
		WHITESPACE(Pattern.compile("^\\s+"), GrammarType.COMMENT),
		BLOCK_COMMENT(Pattern.compile("^/\\*.*?\\*/"), GrammarType.COMMENT),
		LINE_COMMENT(Pattern.compile("^//.*+$"), GrammarType.COMMENT),
		
		//VALIDS
		NOMINAL(Pattern.compile("^\\w+"), GrammarType.KEEP),
		NUMERIC(Pattern.compile("^\\d+"), GrammarType.KEEP),
		OPERATOR(Pattern.compile("^[+-/\\\\÷\\*×\\^]"), GrammarType.KEEP),
		EQUATOR(Pattern.compile("^!?=?[=><]"), GrammarType.KEEP),
		
		//DELIMITERS
		LPAREN(Pattern.compile("^\\("), GrammarType.KEEP),
		RPAREN(Pattern.compile("^\\)"), GrammarType.KEEP)
	;
	
	//PRIVATE FIELDS
	private Logger logger = Logger.getLogger("AlgebraicSpelling");
	
	//ENUMERATIONS
	private enum GrammarType {
		KEEP,
		COMMENT;
	}
	
	//FIELDS
	private final Pattern pattern;
	private final GrammarType type;
	
	//CONSTRUCTORS
	/**
	 * The two underlying details for any syntax pattern are its regular expression, and its semantic meaning.
	 * The pattern is simply a compiled regular expression for matching the item. The type is a clue to its
	 * semantic meaning, which is checked in a switch-case statement when called.
	 * 
	 * @param pattern compiled regular expression identifying the token
	 * @param type clue as to what should be done with the token once found
	 */
	private AlgebraicSpelling(Pattern pattern, GrammarType type) {
		this.pattern = pattern;
		this.type = type;
	}
	
	//GETTERS/SETTERS
	@Override
	public Pattern getPattern() {
		return pattern;
	}

	@Override
	public List<AST> manageToken(Matcher matcher) {
		List<AST> ret = new LinkedList<>();
		switch(this.type) {
		case KEEP:
			ret.add(new Terminal(matcher.group()));
			break;
		case COMMENT:
			//Just ignore it
			break;
		}
		return ret;
	}

}
