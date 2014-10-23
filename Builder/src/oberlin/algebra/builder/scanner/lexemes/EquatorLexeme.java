package oberlin.algebra.builder.scanner.lexemes;

import java.util.regex.Pattern;

import oberlin.builder.parser.MismatchException;
import oberlin.builder.scanner.lexeme.AbstractLexeme;

public class EquatorLexeme extends AbstractLexeme {
	
	private final Pattern pattern = Pattern.compile("^!?=?[=><]");
	
	public EquatorLexeme(String sz) throws MismatchException {
		super(sz);
	}

	@Override
	public Pattern getPattern() {
		return pattern;
	}

}
