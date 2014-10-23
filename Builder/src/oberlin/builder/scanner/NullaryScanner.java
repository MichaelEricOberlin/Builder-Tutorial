package oberlin.builder.scanner;

import java.util.*;

import oberlin.builder.scanner.lexeme.Lexeme;

public class NullaryScanner extends Scanner {

	@Override
	public List<Lexeme> scan(String code) {
		List<Lexeme> list = new ArrayList<Lexeme>();
		return list;
	}

}
