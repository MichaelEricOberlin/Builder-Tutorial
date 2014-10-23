package oberlin.builder.scanner.lexeme;

import java.util.regex.Pattern;

import oberlin.builder.parser.MismatchException;

public abstract class AbstractLexeme implements Lexeme {
	
	private String token;
	
	protected AbstractLexeme() {
		//Any oddball initialization tasks go here
	}
	
	public AbstractLexeme(String sz) throws MismatchException {
		if(this.matches(sz)) setToken(sz);
		else throw new MismatchException();
	}
	
	@Override
	public int getCharacterCount() {
		return token.length();
	}
	
	//GETTERS/SETTERS
	protected String getToken() {
		return token;
	}

	protected void setToken(String token) {
		this.token = token;
	}

}
