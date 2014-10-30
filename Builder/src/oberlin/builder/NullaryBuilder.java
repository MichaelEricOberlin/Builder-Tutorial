package oberlin.builder;

import java.util.*;

import oberlin.builder.scanner.NullaryScanner;
import oberlin.builder.scanner.lexeme.Lexeme;

public class NullaryBuilder extends Builder {
	
	public NullaryBuilder() {
		this.setScanner(new NullaryScanner());
	}
	
	@Override
	public Object build(String code) {
//		List<String> scanned = getScanner().scan(code);
		return code;
	}

}
