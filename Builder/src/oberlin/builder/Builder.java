package oberlin.builder;

import java.util.List;

import oberlin.builder.scanner.*;
import oberlin.builder.parser.*;

public abstract class Builder {
	private Scanner scanner;
	private Parser parser;
	
	public Object build(String code) {
		List<String> tokens = getScanner().tokenize(code);
		
		
		
		StringBuilder builder = new StringBuilder();
		for(String token : tokens) {
			builder.append(token).append("; ");
		}
		return builder.toString();		
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
