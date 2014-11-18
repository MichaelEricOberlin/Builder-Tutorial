package oberlin.builder;

import java.util.List;
import java.util.logging.*;

import oberlin.builder.scanner.*;
import oberlin.builder.parser.*;
import oberlin.builder.parser.ast.*;

public abstract class Builder {
	private Logger logger = Logger.getLogger("Builder");
	private Scanner<?> scanner = new NullaryScanner();
	private Parser<?> parser = new NullaryParser();
	
	public Object build(String code) throws BuilderException {
		List<AST> tokens = (List<AST>) scanner.apply(new Terminal(code));
		
//		//NOTE: EXCLUSIVELY FOR DEBUGGING
//		tokens.stream().forEach(tk -> System.out.println(tk.getClass()));
		
		//We now have a list of Terminals, ready to be constructed into an AST via a Parser.
		/*
		 * Note that scanner uses a TerminalSpelling enumeration. Scanner is only interested in terminals.
		 * The complete grammar of the language goes beyond that, and is also interested in the order
		 * of the terminals. This is where PhraseStructure comes in to play.
		 */
		
		/*
		 * Possible Issue: Parser is destroying all of the contents of code. However, this may ultimately
		 * be irrelevant.
		 */
		AST program = parser.apply(tokens);
		System.out.println(program.getClass());
		
		//TODO: Generate AST with Parser
		
		//TODO: Generate Object Program with code generator
		//TODO: Return Object Program
		
		//TODO: At this point, objectProgram should be our completed and runnable binary. The following code is more for
		//embedded testing than anything else, and should be removed before release.
		
		StringBuilder szBuilder = new StringBuilder();
		for(AST token : tokens) {
			szBuilder.append(token).append("; ");
		}
		return szBuilder.toString();
		
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	protected Parser getParser() {
		return parser;
	}

	protected void setParser(Parser parser) {
		this.parser = parser;
	}
	
}
