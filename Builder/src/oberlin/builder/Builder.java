package oberlin.builder;

import java.util.List;

import oberlin.builder.scanner.*;
import oberlin.builder.parser.*;

public abstract class Builder {
	private Scanner scanner;
	private Parser parser;
	
	public Object build(String code) {
		List<String> tokens = getScanner().tokenize(code);
		
		Object obj = getParser().parse(tokens);
		System.out.println(obj.getClass() + ": " + obj);
		
		StringBuilder szBuilder = new StringBuilder();
		for(String token : tokens) {
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
