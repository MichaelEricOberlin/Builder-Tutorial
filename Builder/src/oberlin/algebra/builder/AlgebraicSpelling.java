package oberlin.algebra.builder;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oberlin.builder.TerminalSpelling;
import oberlin.builder.TerminalSpellingHandler;
import oberlin.builder.parser.SourcePosition;
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
		WHITESPACE(Pattern.compile("[\\s&&[^\\r\\n]]+"/*"^\\s+"*/), GrammarType.COMMENT, new TerminalSpellingHandler<Whitespace>(){
			//NOTE: We are looking for all whitespace other than newlines! Thus, \r and \n must be ignored.
			//Hence, the strange regex above. (To look for the same item in two places has undefined and
			//generally unpleasant behavior.)
			@Override
			public Whitespace getTerminal(String spelling, SourcePosition position) {
				return new Whitespace(spelling, position);
			}
			
		}),
		BLOCK_COMMENT(Pattern.compile("^/\\*.*?\\*/"), GrammarType.COMMENT, new TerminalSpellingHandler<BlockComment>(){

			@Override
			public BlockComment getTerminal(String spelling, SourcePosition position) {
				return new BlockComment(spelling, position);
			}
			
		}),
		LINE_COMMENT(Pattern.compile("^//.*+$"), GrammarType.COMMENT, new TerminalSpellingHandler<LineComment>(){

			@Override
			public LineComment getTerminal(String spelling, SourcePosition position) {
				return new LineComment(spelling, position);
			}
			
		}),
		
		//VALIDS
		NOMINAL(Pattern.compile("^[\\D&&\\w]\\w+"), GrammarType.KEEP, new TerminalSpellingHandler<Nominal>(){

			@Override
			public Nominal getTerminal(String spelling, SourcePosition position) {
				System.out.println("New nominal");
				return new Nominal(spelling, position);
			}
			
		}),
		NUMERIC(Pattern.compile("^\\d+"), GrammarType.KEEP, new TerminalSpellingHandler<Numeric>(){

			@Override
			public Numeric getTerminal(String spelling, SourcePosition position) {
				System.out.println("New numeric");
				return new Numeric(spelling, position);
			}
			
		}),
		OPERATOR(Pattern.compile("^[+-/\\\\÷\\*×\\^]"), GrammarType.KEEP, new TerminalSpellingHandler<Operator>(){

			@Override
			public Operator getTerminal(String spelling, SourcePosition position) {
				return new Operator(spelling, position);
			}
			
		}),
		EQUATOR(Pattern.compile("^!?=?[=><]"), GrammarType.KEEP, new TerminalSpellingHandler<Equator>(){

			@Override
			public Equator getTerminal(String spelling, SourcePosition position) {
				return new Equator(spelling, position);
			}
			
		}),
		
		//DELIMITERS
		LPAREN(Pattern.compile("^\\("), GrammarType.KEEP, new TerminalSpellingHandler<LParen>(){

			@Override
			public LParen getTerminal(String spelling, SourcePosition position) {
				return new LParen(spelling, position);
			}
			
		}),
		RPAREN(Pattern.compile("^\\)"), GrammarType.KEEP, new TerminalSpellingHandler<RParen>(){

			@Override
			public RParen getTerminal(String spelling, SourcePosition position) {
				return new RParen(spelling, position);
			}
			
		}),
		//NOTE: "\\r?\\n" also qualifies as whitespace! Remove it from the whitespace search!
		NEWLINE(Pattern.compile("\\n"), GrammarType.KEEP, new TerminalSpellingHandler<NewLine>(){
			@Override
			public NewLine getTerminal(String spelling, SourcePosition position) {
				return new NewLine(spelling, position);
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
	public List<AST> manageToken(Matcher matcher, SourcePosition position) {
		List<AST> ret = new LinkedList<>();
		switch(this.type) {
		case KEEP:
			ret.add(handler.getTerminal(matcher.group(), position));
			break;
		case COMMENT:
			//Just ignore it
			break;
		}
		return ret;
	}

}
