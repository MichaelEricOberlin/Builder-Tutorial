package oberlin.algebra.builder.scanner.lexemes;

import java.util.regex.*;

import oberlin.builder.parser.MismatchException;
import oberlin.builder.scanner.lexeme.AbstractLexeme;

public class OperatorLexeme extends AbstractLexeme {
	private final Pattern pattern = Pattern.compile("^[+-/\\\\\\*\\^]");
	
	public OperatorLexeme() {
	}

	public OperatorLexeme(String sz) throws MismatchException {
		super(sz);
	}

	@Override
	public Pattern getPattern() {
		return pattern;
	}

}
