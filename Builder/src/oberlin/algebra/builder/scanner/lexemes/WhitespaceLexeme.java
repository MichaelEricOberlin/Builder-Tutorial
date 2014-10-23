package oberlin.algebra.builder.scanner.lexemes;

import java.util.regex.Pattern;

import oberlin.builder.parser.MismatchException;
import oberlin.builder.scanner.lexeme.AbstractLexeme;

public class WhitespaceLexeme extends AbstractLexeme {
	private final Pattern pattern = Pattern.compile("^\\s+");
	
	public WhitespaceLexeme(String sz) throws MismatchException {
		super(sz);
	}

	@Override
	public Pattern getPattern() {
		return pattern;
	}

}
