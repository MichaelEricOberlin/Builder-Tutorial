package oberlin.builder.parser;

import java.util.ArrayList;
import java.util.List;

import oberlin.builder.parser.ast.AST;

public enum NullaryPhraseStructure implements PhraseStructure {
	NULLARY;

	/**
	 * Always returns true, on account of Nullary nature of class
	 */
	@Override
	public boolean match(List<AST> treeList) {
		return true;
	}

}
