package oberlin.builder;

import java.util.*;

import oberlin.builder.parser.ast.AST;

public abstract class NonTerminal implements AST {
	private final List<AST> astList;
	
	public NonTerminal(AST... astList) throws MismatchException {
		if(!checkTypes(astList)) throw new MismatchException("Nonterminal class " + this.getClass() + " does not match " +
				"expression.");
		
		List<AST> list = new ArrayList<>();
		for(AST ast : astList) {
			list.add(ast);
		}
		this.astList = list; 
	}
	
	public NonTerminal(List<AST> astList) throws MismatchException {
		try {
			this.astList = resolveTypes(astList);
		} catch(BuilderException ex) {
			throw new MismatchException(ex);
		}
	}
	
	public abstract List<Class<? extends AST>> getExpectedASTTypes();
	
	/**
	 * Check to see that all provided ASTs are some extension of the expected class of AST,
	 * and create the internal list of ASTs from it if possible.
	 * 
	 * @param astList current list of program ASTs 
	 * @return true if the first ASTs match the expected ones, false otherwise
	 */
	private List<AST> resolveTypes(List<AST> astList) throws BuilderException {
		List<AST> ownASTs = new ArrayList<>();
		List<Class<? extends AST>> astTypes = getExpectedASTTypes();
		System.out.println(this.getClass() + " types size: " + astTypes.size());
		
		for(int i = 0; i < astTypes.size(); i++) {
			Class<? extends AST> provided, expected;
			provided = astList.get(i).getClass();
			expected = astTypes.get(i);
			if(!expected.isAssignableFrom(provided)) {
				throw new BuilderException("Cannot get " + expected + " from " + provided.getClass());
			}
			ownASTs.add(astList.get(i));
		}
		return ownASTs;
	}
	
	/**
	 * Check to see that all provided ASTs are some extension of the expected class of AST.
	 * 
	 * @param astList current array of program ASTs 
	 * @return true if the first ASTs match the expected ones, false otherwise
	 */
	private boolean checkTypes(AST... astList) {
		//This should be an ASTPatternMatcher.
		List<Class<? extends AST>> astTypes = getExpectedASTTypes();
		
		for(int i = 0; i < astList.length; i++) {
			Class<? extends AST> provided, expected;
			provided = astList[i].getClass();
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
