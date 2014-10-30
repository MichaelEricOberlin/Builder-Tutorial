package oberlin.builder.parser;

import oberlin.builder.scanner.lexeme.Lexeme;

public abstract class Symbol {
	private String token;
	
	public Symbol(String token) throws MismatchException {
		if(getLexeme().matches(token)) {
			this.setToken(token);
		} else throw new MismatchException("\"" + token + "\" is not a member of " + this.getClass());
	}
	
	//GETTERS/SETTERS
	private void setToken(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}
	
	protected abstract Lexeme getLexeme();
}
