package oberlin.algebra.builder.scanner.lexemes;

import java.util.regex.Pattern;

import oberlin.builder.parser.MismatchException;
import oberlin.builder.scanner.lexeme.AbstractLexeme;

public class WhitespaceLexeme extends AbstractLexeme {
	
	public WhitespaceLexeme(String sz) throws MismatchException {
		super(sz);
	}

	@Override
	public Pattern getPattern() {
		final Pattern pattern = Pattern.compile("^\\s+");
		return pattern;
	}

}
