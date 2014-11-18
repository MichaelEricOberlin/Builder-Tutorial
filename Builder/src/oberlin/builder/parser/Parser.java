package oberlin.builder.parser;

import java.util.*;
import java.util.function.*;
import java.util.logging.*;

import oberlin.builder.*;
import oberlin.builder.parser.ast.*;
import oberlin.builder.visitor.Element;
import oberlin.builder.visitor.Visitor;

public abstract class Parser<E extends Enum<E> & PhraseStructure> implements Function<List<AST>, AST>, Element {

	// PRIVATE FIELDS
	private Logger logger = Logger.getLogger("Parser");

	private AST currentToken;
	private Class<AST> expectedToken;
	private volatile Iterator<AST> code;

	// GETTERS/SETTERS
	private AST getCurrentToken() {
		return currentToken;
	}

	private Class<AST> getExpectedToken() {
		return expectedToken;
	}

	private Iterator<AST> getCode() {
		return code;
	}

	private void setCode(Iterator<AST> code) {
		this.code = code;
	}

	/**
	 * Note that the given list must not be immutable; in its reduction to a
	 * single AST (for a properly formatted program), Parser modifies and
	 * reduces the contents of code until only a single value remains.
	 */
	public AST apply(List<AST> code) {
		// Make iterator available for visit method
		setCode(code.iterator());

		AST program = null;
		
		/*
		 * Keep an Enumeration of NonTerminals. Iterate over them, until a nonterminal matches
		 * the first items of the list.
		 */
		
		//get iterator for enumeration
		PhraseStructure[] enums = getPhraseStructure().getEnumConstants();
		List<? extends PhraseStructure> list = Arrays.asList(enums);
		
		while(code.size() > 1) {
			
			//DEBUG
			System.out.println("Code size: " + code.size());
			System.out.print("Code: ");
			code.stream().forEach(sz -> System.out.println(sz + " "));
			System.out.println();
			
			Iterator<? extends PhraseStructure> iterator = list.iterator();
			boolean matched = false;
			
			while(iterator.hasNext()) {
				PhraseStructure ps = iterator.next();
				if(ps.match(code)) {
					//match found and alteration made!
					matched = true;
					System.out.println(ps + ": " + matched);
					break;
				}
				//else continue.
			}
			
			if(!matched) {
				//TODO: Give more data on this
				System.err.println();
				System.err.println("Tree list on exception: ");
				code.stream().forEach(c -> System.err.print(c.getClass() + ", "));
				System.err.println();
				throw new BuilderException("Syntax incomplete");
			}

		}

//		code = getPhraseStructure().getPhrase(code);
		
		//Expired:
		/*
		 * Strategy to modernize Watt's code: Keep an Enumeration of Phrase
		 * functions, each mapped to a specific label. For each label in the
		 * enumeration, attempt to run the Phrase function. Should throw a
		 * MismatchException if it doesn't match; if it does match, modify the
		 * underlying List by removing the matching tokens and prepending the
		 * complete AST.
		 */
//		try {
//			Command cAST = parseCommand();
//			programAST = new Program(cAST, previousTokenPosition);
//			if (currentToken.kind != Token.EOT) {
//				syntacticError("\"%\" not expected after end of program", currentToken.spelling);
//			}
//		} catch (SyntaxError s) {
//			return null;
//		}

		return code.get(0);
	}

	/**
	 * Checks whether current AST in index matches the expected AST type. If so,
	 * fetches the next token; if not, reports a syntax error.
	 */
	public void accept(Visitor visitor) {
		if (this.getCurrentToken().getClass().isInstance(getExpectedToken())) {
			currentToken = getCode().next();
		} else {
			logger.log(Level.WARNING, "\"%\" expected here" + getExpectedToken());
		}
	}
	
	public abstract Class<E> getPhraseStructure();
}
