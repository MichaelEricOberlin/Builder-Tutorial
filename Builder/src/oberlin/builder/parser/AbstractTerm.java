package oberlin.builder.parser;

public abstract class AbstractTerm<E> implements Term<E> {

	protected AbstractTerm() {
		// TODO Auto-generated constructor stub
	}
	
	public AbstractTerm(String token) throws MismatchException {
		this();
		if(!check(token)) {
			throw new MismatchException(this.getClass() + " cannot be instantiated for \"" + token + "\"");
		}
		this.setData(derive(token));
	}
	
	/**
	 * Derives the coded data from the provided token.
	 * 
	 * @param token the encoded semantic
	 * @return the semantic itself
	 * @throws MismatchException if the token is improperly formatted for this data
	 */
	protected abstract E derive(String token) throws MismatchException;
	
	protected abstract void setData(E data);

}
