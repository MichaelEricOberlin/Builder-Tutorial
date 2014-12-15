package oberlin.builder;

import java.util.*;

import oberlin.builder.parser.SourcePosition;
import oberlin.builder.parser.ast.AST;
import oberlin.builder.parser.ast.pattern.ASTPattern;

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
		
//		try {
//			this.astList = resolveTypes(astList);
//		} catch(BuilderException ex) {
//			throw new MismatchException(ex);
//		}
	}
	
	public abstract List<Class<? extends AST>> getExpectedASTTypes();
	
	/**
	 * Check to see that all provided ASTs are some extension of the expected class of AST,
	 * and create the internal list of ASTs from it if possible.
	 * 
	 * @param astList current list of program ASTs 
	 * @return true if the first ASTs match the expected ones, false otherwise
	 */
//	private List<AST> resolveTypes(List<AST> astList) throws BuilderException {
//		List<AST> ownASTs = new ArrayList<>();
//		List<Class<? extends AST>> astTypes = getExpectedASTTypes();
////		System.out.println(this.getClass() + " types size: " + astTypes.size());
//		
//		for(int i = 0; i < astTypes.size(); i++) {
//			Class<? extends AST> provided, expected;
//			provided = astList.get(i).getClass();
//			expected = astTypes.get(i);
//			if(!expected.isAssignableFrom(provided)) {
//				throw new BuilderException("Cannot get " + expected + " from " + provided.getClass());
//			}
//			ownASTs.add(astList.get(i));
//		}
//		return ownASTs;
//	}
	
//	/**
//	 * Check to see that all provided ASTs are some extension of the expected class of AST.
//	 * 
//	 * @param astList current array of program ASTs 
//	 * @return true if the first ASTs match the expected ones, false otherwise
//	 */
//	private boolean checkTypes(AST... astList) {
//		//This should be an ASTPatternMatcher.
//		List<Class<? extends AST>> astTypes = getExpectedASTTypes();
//		
//		for(int i = 0; i < astList.length; i++) {
//			Class<? extends AST> provided, expected;
//			provided = astList[i].getClass();
//			expected = astTypes.get(i);
//			if(!provided.equals(expected)) {
//				return false;
//			}
//		}
//		return true;
//	}
	
//	private List<AST> resolveTypes(AST...astList) throws BuilderException {
//		return resolveTypes(Arrays.asList(astList));
//	}
//	
//	private List<AST> resolveTypes(List<AST> astList) throws BuilderException {
//		ASTPattern pattern = getASTPattern();
//		System.out.println("Matching " + pattern + " against " + astList);
//		System.out.println("Pattern: " + pattern.getPattern().pattern());
//		try {
//			astList = pattern.match(astList);
//		} catch(MismatchException ex) {
//			throw new BuilderException("Cannot get " + this.getClass() + " from provided nodes");
//		}
////		if(pattern.match(astList)) {
////			/*
////			 * TODO: Have pattern memorize the group that matched, and return it as an
////			 * ordered sequence of nodes
////			 */
////			//DEBUG: just a method breaker.
////			return astList;
////		} else {
//////			System.out.println("NO MATCH");
//////			throw new BuilderException("Cannot get " + this.getClass() + " from provided nodes");
////		}
//		return astList;
//	}
	
	/**
	 * @return syntax restriction on this particular variety of NonTerminal
	 */
	protected abstract ASTPattern getASTPattern();
	
	@Override
	public int getElementCount() {
		return astList.size();
	}
	
	@Override
	public SourcePosition getPosition() {
		return this.position;
	}
}
