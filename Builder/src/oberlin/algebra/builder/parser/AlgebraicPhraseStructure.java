package oberlin.algebra.builder.parser;

import java.util.ArrayList;
import java.util.List;

import oberlin.builder.PhraseStructure;
import oberlin.builder.parser.NullaryAST;
import oberlin.builder.parser.ast.AST;

public enum AlgebraicPhraseStructure implements PhraseStructure {
	UNIMPLEMENTED;

	@Override
	public List<Class<? extends AST>> getASTTypeList() {
		return new ArrayList<>();
	}

	@Override
	public AST formatElement(List<AST> code) {
		return new NullaryAST();
	}

}
