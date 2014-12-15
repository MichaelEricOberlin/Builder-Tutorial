package oberlin.builder;

import oberlin.builder.parser.SourcePosition;

public interface TerminalSpellingHandler<E extends Terminal> {
	public E getTerminal(String spelling, SourcePosition position);
}
