package oberlin.algebra.builder.parser;

import java.util.ArrayList;
import java.util.List;

import oberlin.builder.PhraseStructure;
import oberlin.builder.parser.NullaryAST;
import oberlin.builder.parser.PhraseStructureHandler;
import oberlin.builder.parser.ast.AST;
import oberlin.algebra.builder.nodes.*;

public enum AlgebraicPhraseStructure implements PhraseStructure {
	EQUALITY(new ArrayList<Class<? extends AST>>()
			{{put(Expression.class); put(EqualityOperator.class); put(Expression.class);}}
		, Equality.class);
	
	private final List<Class<? extends AST>> typeList;
	private final PhraseStructureHandler handler;
	
	private AlgebraicPhraseStructure(List<Class<? extends AST>> typeList, PhraseStructureHandler handler) {
		this.typeList = typeList;
		this.handler = handler;
	}
	
	@Override
	public List<Class<? extends AST>> getASTTypeList() {
		return this.typeList;
	}

	@Override
	public AST formatElement(List<AST> code) {
		return new NullaryAST();
	}

}
