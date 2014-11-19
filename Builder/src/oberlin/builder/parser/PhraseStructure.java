package oberlin.builder.parser;

import oberlin.builder.*;
import oberlin.builder.parser.ast.AST;

import java.util.*;

public interface PhraseStructure {

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
