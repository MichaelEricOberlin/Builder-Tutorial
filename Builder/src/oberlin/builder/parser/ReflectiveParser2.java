package oberlin.builder.parser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import oberlin.builder.AbstractSyntaxTree;
import oberlin.builder.scanner.lexeme.Lexeme;

public class ReflectiveParser2 implements Parser {
	
	private List<Class<? extends Symbol>> symbolClasses = new ArrayList<>();
	
	public ReflectiveParser2() {
		
	}

	@Override
	public AbstractSyntaxTree parse(List<String> tokens) {
		AbstractSyntaxTree ast = null;
		
		List<Symbol> symbols = this.buildSymbolList(tokens);
		
		for(Symbol symbol : symbols) {
			/*
			 * TODO
			 * Compare each symbol to grammar structures, find matches,
			 * and attempt to create a complete AST for the program.
			 * 
			 * Set ast to equal the topmost AST extension.
			 */
		}
		
		return ast;
	}

	@Override
	public List<Class<? extends Symbol>> getSymbolClasses() {
		return this.symbolClasses;
	}
	
	public void setSymbolClasses(List<Class<? extends Symbol>> symbolClasses) {
		this.symbolClasses = symbolClasses;
	}
	
	public void addSymbolClass(Class<? extends Symbol> cls) {
		this.getSymbolClasses().add(cls);
	}
}
