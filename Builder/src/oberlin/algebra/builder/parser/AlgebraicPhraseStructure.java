package oberlin.algebra.builder.parser;

import java.util.List;
import java.util.function.Function;

import oberlin.builder.MismatchException;
import oberlin.builder.parser.PhraseStructure;
import oberlin.builder.parser.ast.AST;
import oberlin.algebra.builder.nodes.*;

public enum AlgebraicPhraseStructure implements PhraseStructure {
	PROGRAM(new Function<List<AST>, Boolean>() {

		@Override
		public Boolean apply(List<AST> t) {
			try {
				Program program = new Program(t);
				t = PhraseStructure.trim(program, t);
				return true;
			} catch(MismatchException ex) {
				return false;
			}
		}

	}),
	EQUALITY(new Function<List<AST>, Boolean>() {

		@Override
		public Boolean apply(List<AST> t) {
			try {
				Equality equality = new Equality(t);
				t = PhraseStructure.trim(equality, t);
				return true;
			} catch(MismatchException ex) {
				return false;
			}
		}

	}),
	EXPRESSION(new Function<List<AST>, Boolean>() {

		@Override
		public Boolean apply(List<AST> t) {
			try {
				Expression expression = new Expression(t);
				t = PhraseStructure.trim(expression, t);
				return true;
			} catch(MismatchException ex) {
				return false;
			}
		}

	}),
	IDENTIFIER(new Function<List<AST>, Boolean>() {
		@Override
		public Boolean apply(List<AST> t) {
			try {
				Identifier identifier = new Identifier(t);
				t = PhraseStructure.trim(identifier, t);
				return true;
			} catch(MismatchException ex) {
				return false;
			}
		}
	});

	private Function<List<AST>, Boolean> generator;
	
	private AlgebraicPhraseStructure(Function<List<AST>, Boolean> generator) {
		this.generator = generator;
	}
	
	@Override
	public boolean match(List<AST> treeList) {
		return this.generator.apply(treeList);
	}
	
	
	
}
