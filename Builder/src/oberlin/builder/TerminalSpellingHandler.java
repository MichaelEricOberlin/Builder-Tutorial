package oberlin.builder;

public interface TerminalSpellingHandler<E extends Terminal> {
	public E getTerminal(String spelling);
}
