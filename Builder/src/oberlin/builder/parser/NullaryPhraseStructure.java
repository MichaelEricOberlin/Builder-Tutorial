package oberlin.builder.parser;

import java.util.ArrayList;
import java.util.List;

import oberlin.builder.PhraseStructure;
import oberlin.builder.parser.ast.AST;

public enum NullaryPhraseStructure implements PhraseStructure {
	NULLARY;

	@Override
	public List<Class<? extends AST>> getASTTypeList() {
		return new ArrayList<Class<? extends AST>>();
	}

	@Override
	public AST formatElement(List<AST> code) {
		return new NullaryAST();
	}

}
