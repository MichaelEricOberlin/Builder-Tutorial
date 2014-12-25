package oberlin.builder.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import oberlin.builder.parser.ast.AST;

public class NullaryPhraseStructure implements PhraseStructure {

	@Override
	public Map<Class<? extends AST>, BiFunction<Parser<?>, SourcePosition, ? extends AST>> getHandlerMap() {
		final Map<Class<? extends AST>, BiFunction<Parser<?>, SourcePosition, ? extends AST>> map = new HashMap<>();
		return map;
	}

}
