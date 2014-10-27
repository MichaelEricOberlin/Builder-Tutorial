package oberlin.builder.parser;

import java.util.*;

import oberlin.builder.AbstractSyntaxTree;
import oberlin.builder.scanner.lexeme.Lexeme;

public interface Parser {
	public AbstractSyntaxTree parse(List<Lexeme> lexemes);
	
	public List<Term<?>> identifyTerms(List<Lexeme> tokens);
}
