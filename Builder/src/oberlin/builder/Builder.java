package oberlin.builder;

import java.util.List;

import oberlin.builder.scanner.*;
import oberlin.builder.scanner.lexeme.Lexeme;
import oberlin.builder.parser.*;

public abstract class Builder {
	private Scanner scanner;
	private Parser parser;
	
	public Object build(String code) {
		List<Lexeme> lexemes = getScanner().scan(code);
		
		Object obj = getParser().parse(lexemes);
		System.out.println(obj.getClass() + ": " + obj);
		
		StringBuilder szBuilder = new StringBuilder();
		for(Lexeme lexeme : lexemes) {
			szBuilder.append(lexeme).append("; ");
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
