package oberlin.algebra.builder.parser;

import java.util.List;
import java.util.function.Function;

import oberlin.builder.BuilderException;
import oberlin.builder.MismatchException;
import oberlin.builder.parser.Phrase;
import oberlin.builder.parser.PhraseStructure;
import oberlin.builder.parser.ast.AST;
import oberlin.algebra.builder.nodes.*;

/*
 * TODO: Next up: Establish rules for each type of branch node in the following enumeration.
 */
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
				System.out.println(t.size() + " elements in original list");
				System.out.println("Expression element count: " + expression.getElementCount());
				t = PhraseStructure.trim(expression, t);
				System.out.println(t.size() + " final elements");
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
