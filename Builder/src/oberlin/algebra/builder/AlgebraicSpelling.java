package oberlin.algebra.builder;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oberlin.builder.TerminalSpelling;
import oberlin.builder.Terminal;
import oberlin.builder.TerminalSpellingHandler;
import oberlin.builder.parser.ast.AST;
import oberlin.algebra.builder.nodes.*;

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
		WHITESPACE(Pattern.compile("^\\s+"), GrammarType.COMMENT, new TerminalSpellingHandler<Whitespace>(){

			@Override
			public Whitespace getTerminal(String spelling) {
				return new Whitespace(spelling);
			}
			
		}),
		BLOCK_COMMENT(Pattern.compile("^/\\*.*?\\*/"), GrammarType.COMMENT, new TerminalSpellingHandler<BlockComment>(){

			@Override
			public BlockComment getTerminal(String spelling) {
				return new BlockComment(spelling);
			}
			
		}),
		LINE_COMMENT(Pattern.compile("^//.*+$"), GrammarType.COMMENT, new TerminalSpellingHandler<LineComment>(){

			@Override
			public LineComment getTerminal(String spelling) {
				return new LineComment(spelling);
			}
			
		}),
		
		//VALIDS
		NOMINAL(Pattern.compile("^\\w+"), GrammarType.KEEP, new TerminalSpellingHandler<Nominal>(){

			@Override
			public Nominal getTerminal(String spelling) {
				return new Nominal(spelling);
			}
			
		}),
		NUMERIC(Pattern.compile("^\\d+"), GrammarType.KEEP, new TerminalSpellingHandler<Numeric>(){

			@Override
			public Numeric getTerminal(String spelling) {
				return new Numeric(spelling);
			}
			
		}),
		OPERATOR(Pattern.compile("^[+-/\\\\÷\\*×\\^]"), GrammarType.KEEP, new TerminalSpellingHandler<Operator>(){

			@Override
			public Operator getTerminal(String spelling) {
				return new Operator(spelling);
			}
			
		}),
		EQUATOR(Pattern.compile("^!?=?[=><]"), GrammarType.KEEP, new TerminalSpellingHandler<Equator>(){

			@Override
			public Equator getTerminal(String spelling) {
				return new Equator(spelling);
			}
			
		}),
		
		//DELIMITERS
		LPAREN(Pattern.compile("^\\("), GrammarType.KEEP, new TerminalSpellingHandler<LParen>(){

			@Override
			public LParen getTerminal(String spelling) {
				return new LParen(spelling);
			}
			
		}),
		RPAREN(Pattern.compile("^\\)"), GrammarType.KEEP, new TerminalSpellingHandler<RParen>(){

			@Override
			public RParen getTerminal(String spelling) {
				return new RParen(spelling);
			}
			
		})
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
	private final TerminalSpellingHandler<?> handler;
	
	//CONSTRUCTORS
	/**
	 * The two underlying details for any syntax pattern are its regular expression, and its semantic meaning.
	 * The pattern is simply a compiled regular expression for matching the item. The type is a clue to its
	 * semantic meaning, which is checked in a switch-case statement when called.
	 * 
	 * @param pattern compiled regular expression identifying the token
	 * @param type clue as to what should be done with the token once found
	 */
	private AlgebraicSpelling(Pattern pattern, GrammarType type, TerminalSpellingHandler<?> handler) {
		this.pattern = pattern;
		this.type = type;
		this.handler = handler;
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
			ret.add(handler.getTerminal(matcher.group()));
			break;
		case COMMENT:
			//Just ignore it
			break;
		}
		return ret;
	}

}
