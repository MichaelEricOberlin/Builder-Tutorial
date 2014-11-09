package oberlin.builder;

import java.util.*;

import oberlin.builder.parser.ast.AST;

public abstract class NonTerminal implements AST {
	private final List<AST> astList;
	
	public NonTerminal(List<AST> astList) throws MismatchException {
		if(!checkTypes(astList)) throw new MismatchException("Nonterminal class " + this.getClass() + " does not match " +
				"expression.");
		this.astList = astList;
	}
	
	public abstract List<Class<? extends AST>> getExpectedASTTypes();
	
	/*
	 * Check to see that all provided ASTs are some extension of the expected class of AST.
	 */
	private boolean checkTypes(List<AST> astList) {
		List<Class<? extends AST>> astTypes = getExpectedASTTypes();
		
		for(int i = 0; i < astTypes.size(); i++) {
			Class<? extends AST> provided, expected;
			provided = astList.get(i).getClass();
			expected = astTypes.get(i);
			if(!provided.equals(expected)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int getElementCount() {
		return astList.size();
	}
}
