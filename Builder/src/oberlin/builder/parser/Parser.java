package oberlin.builder.parser;

import java.util.*;

public interface Parser<E> {
	public E parse(List<String> tokens);
	
	public List<Term<?>> identifyTerms(List<String> tokens);
}
