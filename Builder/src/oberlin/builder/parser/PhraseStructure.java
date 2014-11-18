package oberlin.builder.parser;

import oberlin.builder.*;
import oberlin.builder.parser.ast.AST;

import java.util.*;

public interface PhraseStructure {
	/**
	 * Accepts a list of nodes and returns the list slightly altered so that
	 * recognized pattern matches are condensed into nonterminals.
	 * 
	 * @param treeList List of recognized Abstract Syntax Tree nodes
	 * @return condensed list of recognized Abstract Syntax Tree nodes
	 */
//	public default List<AST> getPhrase(List<AST> treeList) throws BuilderException {
//		
//		//get iterator for enumeration
//		PhraseStructure[] enums = this.getClass().getEnumConstants();
//		List<? extends PhraseStructure> list = Arrays.asList(enums);
		
//		while(treeList.size() > 1) {
//			Iterator<? extends PhraseStructure> iterator = list.iterator();
//			boolean matched = false;
//			
//			while(iterator.hasNext()) {
//				PhraseStructure ps = iterator.next();
//				if(ps.match(treeList)) {
//					//match found and alteration made!
//					matched = true;
//					break;
//				}
//				//else continue.
//			}
//			if(!matched) {
//				//TODO: Give more data on this
//				throw new BuilderException("Syntax incomplete");
//			}
//		}
//		
//		return treeList;
//	}
	
	/**
	 * Determines whether or not the current PhraseStructure matches the
	 * list of syntax trees. Presumably alters the structure of the tree
	 * list to reflect this if it does.
	 * @param treeList list of (potentially partially parsed) abstract syntax trees
	 * @return true if the match was successful and the AST list altered, false otherwise
	 */
	public abstract boolean match(List<AST> treeList);
	
	/**
	 * Removes an AST's total element count from the beginning of a treeList, and then
	 * replaces it with the AST itself.
	 * 
	 * @param ast the parameter to properly append to the beginning of the list
	 * @param treeList list of abstract syntax trees to operate on
	 * @return altered treeList, prepending the passed AST and removing its contents
	 */
	public static List<AST> trim(AST ast, List<AST> treeList) {
		System.out.println("ast element count: " + ast.getElementCount());
		for(int i = 0; i < ast.getElementCount(); i++) {
			treeList.remove(0);
			System.out.println("treeList after size-one truncation at index " + i + ": ");
			treeList.stream().forEach(t -> System.out.print(t + ", "));
			System.out.println();
			System.out.println("getElementCount() == " + ast.getElementCount());
		}
		System.out.println("Post-truncation element count: " + treeList.size());
		treeList.add(0, ast);
		return treeList;
	}
}
