package oberlin.algebra.builder.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import oberlin.builder.MismatchException;
import oberlin.builder.parser.Parser2;
import oberlin.builder.parser.PhraseStructure;
import oberlin.builder.parser.SourcePosition;
import oberlin.builder.parser.ast.AST;
import oberlin.builder.parser.ast.EOT;
import oberlin.builder.visitor.VisitHandler;
import oberlin.algebra.builder.nodes.*;

public class AlgebraicPhraseStructure implements PhraseStructure {
	
	private Map<Class<? extends AST>, BiFunction<Parser2<?>, SourcePosition, ? extends AST>> map = new HashMap<>();
	{
		map.put(Program.class, new BiFunction<Parser2<?>, SourcePosition, AST>() {
			@Override
			public Program apply(Parser2<?> parser, SourcePosition position) {
				Program program = null;
				SourcePosition previous = parser.getPreviousTokenPosition();
				previous.setStart(0);
				previous.setFinish(0);
				AST currentToken = parser.getCurrentToken();
				
				System.out.println("In apply");
				
				//TODO: Left off here!
				//Roughly how it goes:
//				Command command = parser.getVisitor().visit(Command.class, parser, previous);
//				program = new Program(command, previous);
				if(!(currentToken instanceof EOT)) {
					parser.syntacticError("Expected end of program", currentToken.getClass().toString());
				}
				
				return program;
			}
		});
	}

//	PROGRAM(new Function<List<AST>, Boolean>() {
//
//		@Override
//		public Boolean apply(List<AST> t) {
//			try {
//				Program program = new Program(t);
//				t = PhraseStructure.trim(program, t);
//				return true;
//			} catch(MismatchException ex) {
//				return false;
//			}
//		}
//
//	}),
//	EQUALITY(new Function<List<AST>, Boolean>() {
//
//		@Override
//		public Boolean apply(List<AST> t) {
//			try {
//				Equality equality = new Equality(t);
//				t = PhraseStructure.trim(equality, t);
//				return true;
//			} catch(MismatchException ex) {
//				return false;
//			}
//		}
//
//	}),
//	EXPRESSION(new Function<List<AST>, Boolean>() {
//
//		@Override
//		public Boolean apply(List<AST> t) {
//			try {
//				Expression expression = new Expression(t);
//				t = PhraseStructure.trim(expression, t);
//				return true;
//			} catch(MismatchException ex) {
//				return false;
//			}
//		}
//
//	}),
//	IDENTIFIER(new Function<List<AST>, Boolean>() {
//		@Override
//		public Boolean apply(List<AST> t) {
//			try {
//				Identifier identifier = new Identifier(t);
//				t = PhraseStructure.trim(identifier, t);
//				return true;
//			} catch(MismatchException ex) {
//				return false;
//			}
//		}
//	});
//
//	private Function<List<AST>, Boolean> generator;
//	
//	private AlgebraicPhraseStructure(Function<List<AST>, Boolean> generator) {
//		this.generator = generator;
//	}
//	
//	@Override
//	public boolean match(List<AST> treeList) {
//		return this.generator.apply(treeList);
//	}
//
	
	@Override
	public Map<Class<? extends AST>, BiFunction<Parser2<?>, SourcePosition, ? extends AST>> getHandlerMap() {
		// TODO Auto-generated method stub
		return map;
	}
	
	
}
