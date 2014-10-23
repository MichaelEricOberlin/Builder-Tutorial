package oberlin.algebra.builder.scanner.lexemes;

import java.util.regex.Pattern;

import oberlin.builder.parser.MismatchException;
import oberlin.builder.scanner.lexeme.AbstractLexeme;

public class LParenLexeme extends AbstractLexeme {
	private final Pattern pattern = Pattern.compile("^\\(");
	
	public LParenLexeme(String sz) throws MismatchException {
		super(sz);
	}

	@Override
	public Pattern getPattern() {
		return pattern;
	}

}
