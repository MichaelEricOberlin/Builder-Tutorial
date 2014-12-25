package oberlin.builder;

import java.util.*;

import oberlin.builder.parser.SourcePosition;
import oberlin.builder.parser.ast.AST;

public abstract class NonTerminal implements AST {
	private final List<AST> astList;
	private final SourcePosition position;
	
	public NonTerminal(SourcePosition position, AST... astList) throws MismatchException {
		this.position = position;
		
//		try {
//			resolveTypes(astList);
//		} catch(BuilderException ex) {
//			throw new MismatchException(ex);
//		}
//		if(!resolveTypes(astList)) throw new MismatchException("Nonterminal class " + this.getClass() + " does not match " +
//				"expression.");
		
		List<AST> list = new ArrayList<>();
		
		for(AST ast : astList) {
			list.add(ast);
		}
		
		this.astList = list; 
	}
	
	public NonTerminal(SourcePosition position, List<AST> astList) throws MismatchException {
		this.position = position;
		
		this.astList = astList;
		
	}
	
	@Override
	public int getElementCount() {
		return astList.size();
	}
	
	@Override
	public SourcePosition getPosition() {
		return this.position;
	}
	
	//DEBUG
	@Override
	public String getContainedNodeNames() {
		StringBuilder builder = new StringBuilder();
		
		for(AST ast : astList) {
			builder.append(ast.getClass().getSimpleName()).append(" ");
		}
		builder.append("\n");
		
		return builder.toString();
	}
	
	@Override
	public List<AST> getContainedNodes() {
		return astList;
	}
}
