package oberlin.algebra.builder.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import oberlin.builder.parser.Parser;
import oberlin.builder.parser.PhraseStructure;
import oberlin.builder.parser.SourcePosition;
import oberlin.builder.parser.ast.AST;
import oberlin.builder.parser.ast.EOT;
import oberlin.algebra.builder.nodes.*;

public class AlgebraicPhraseStructure implements PhraseStructure {
	
	private Map<Class<? extends AST>, BiFunction<Parser<?>, 
		SourcePosition, ? extends AST>> map = new HashMap<>();
	{
		map.put(Program.class, new BiFunction<Parser<?>, SourcePosition, AST>() {
			@Override
			public Program apply(Parser<?> parser, SourcePosition position) {
				//TODO: Have Program include a limitless number of equalities.
				
				Program program = null;
//				SourcePosition previous = parser.getPreviousTokenPosition();
//				AST currentToken = parser.getCurrentToken();
				List<AST> nodes = new ArrayList<>();
//				SourcePosition programPosition = new SourcePosition();
				
				parser.start(position);
				Command command = (Command) parser.getVisitor()
						.visit(Command.class, parser, position);
//				Equality equality = (Equality) parser.getVisitor()
//						.visit(Equality.class, parser, position/*previous*/);
				
				if(parser.getCurrentToken() instanceof NewLine) {
					System.err.println("NewLine found");
					parser.forceAccept();
					nodes.add(parser.getVisitor().visit(Equality.class, parser, position));
				}
				if(!(parser.getCurrentToken() instanceof EOT)) {
					parser.syntacticError("Expected end of program",
							parser.getCurrentToken().getClass().toString());
				}
				parser.finish(position);
				program = new Program(position, command);
				
				return program;
			}
		});
		map.put(Command.class, new BiFunction<Parser<?>, SourcePosition, AST>() {

			@Override
			public AST apply(Parser<?> parser, SourcePosition position) {
				Command command = null;
				List<AST> nodes = new ArrayList<>();
				SourcePosition commandPosition = new SourcePosition();
				
				parser.start(commandPosition);
				
				//parse first equality
				AST equality = parser.getVisitor()
						.visit(Equality.class, parser, position);
				nodes.add(equality);
				if(parser.getCurrentToken() instanceof NewLine) {
					nodes.add(parser.getCurrentToken());
					parser.forceAccept();
					if(!(parser.getCurrentToken() instanceof EOT)) {
						nodes.add(parser.getVisitor().visit(Command.class, parser, commandPosition));
					}
				}
				parser.finish(commandPosition);
				
				command = new Command(commandPosition, nodes);
				return command;
			}
			
		});
		map.put(Equality.class, new BiFunction<Parser<?>, SourcePosition, AST>() {

			@Override
			public AST apply(Parser<?> parser, SourcePosition position) {
				Equality equality = null;
				List<AST> nodes = new ArrayList<>();
				SourcePosition operationPosition = new SourcePosition();
				
				parser.start(operationPosition);
				//parse operation
				AST operation = parser.getVisitor().visit(Operation.class,
						parser, operationPosition);
				nodes.add(operation);
				if(parser.getCurrentToken() instanceof Equator) {
					nodes.add(parser.getCurrentToken());
					parser.forceAccept();
					nodes.add(parser.getVisitor().visit(Operation.class, parser,
							operationPosition));
				} else {
					parser.syntacticError("Expected: equator", Integer.toString(
							parser.getCurrentToken().getPosition().getStart()));
				}
				parser.finish(operationPosition);
				
				equality = new Equality(operationPosition, nodes);
				return equality;
			}
			
		});
		map.put(Operation.class, new BiFunction<Parser<?>, SourcePosition, AST>() {

			@Override
			public AST apply(Parser<?> parser, SourcePosition position) {
				
				Operation operation = null;
				List<AST> nodes = new ArrayList<>();
				SourcePosition operationPosition = new SourcePosition();
				
				parser.start(operationPosition);
				//parse identifier
				AST identifier = parser.getVisitor().visit(Identifier.class,
						parser, operationPosition);
				nodes.add(identifier);
				//look for operator
				if(parser.getCurrentToken() instanceof Operator) {
					nodes.add(parser.getCurrentToken());
					parser.forceAccept();
					nodes.add(parser.getVisitor().visit(Operation.class,
							parser, operationPosition));
				}
				parser.finish(operationPosition);
				
				operation = new Operation(operationPosition, nodes);
				return operation;
			}
			
		});
		map.put(Identifier.class, new BiFunction<Parser<?>, SourcePosition, AST>() {

			@Override
			public AST apply(Parser<?> parser, SourcePosition position) {
				
				Identifier identifier = null;
				List<AST> nodes = new ArrayList<>();
				SourcePosition identifierPosition = new SourcePosition();
				
				parser.start(identifierPosition);
				if(parser.getCurrentToken() instanceof LParen) {
					nodes.add(parser.getCurrentToken());
					parser.forceAccept();
					
					nodes.add(getHandlerMap().get(Operation.class)
							.apply(parser, identifierPosition));
					parser.accept(Operation.class);
					
					nodes.add(parser.getCurrentToken());
					parser.accept(RParen.class);
				} else if(parser.getCurrentToken() instanceof Nominal) {
					nodes.add(parser.getCurrentToken());
					parser.forceAccept();
				} else if(parser.getCurrentToken() instanceof Numeric) {
					nodes.add(parser.getCurrentToken());
					parser.forceAccept();
				} else {
					parser.syntacticError("Nominal or numeric token expected",
							parser.getCurrentToken().getClass().toString());
				}
				parser.finish(identifierPosition);
				identifier = new Identifier(identifierPosition, nodes);
				
				return identifier;
			}
			
		});
	}
	
	@Override
	public Map<Class<? extends AST>, BiFunction<Parser<?>, SourcePosition, ? extends AST>> getHandlerMap() {
		// TODO Auto-generated method stub
		return map;
	}
	
	
}
