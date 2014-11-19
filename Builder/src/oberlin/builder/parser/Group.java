package oberlin.builder.parser;

import java.util.*;

import oberlin.builder.parser.ast.*;

public interface Group {
	public boolean match(List<AST> code);
}
