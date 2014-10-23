package oberlin.builder.parser;

import java.util.*;

import oberlin.builder.scanner.lexeme.Lexeme;

public interface Parser<E> {
	public E parse(List<Lexeme> lexemes);
	
	public List<Term<?>> identifyTerms(List<Lexeme> tokens);
}
