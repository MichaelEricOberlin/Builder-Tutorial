package oberlin.builder.parser;

import oberlin.builder.scanner.lexeme.Lexeme;

/*
 * The remaining concern for Symbol is whether it is terminal or nonterminal. Unfortunately, the symbol itself
 * does not contain the data to know this, it must be derived during the search.
 */
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
