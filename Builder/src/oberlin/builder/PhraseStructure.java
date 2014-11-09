package oberlin.builder;

import java.util.*;

import oberlin.builder.parser.ast.*;

/**
 * Analogue to TerminalSpelling for Non-terminal ASTs. Each PhraseStructure contains data for
 * matching specific sequences of terminals, or throwing a MismatchException on the failure
 * to match.
 * 
 * @author © Michael Eric Oberlin Nov 8, 2014
 * 
 */
public interface PhraseStructure {
	public default AST match(List<AST> code) throws MismatchException {
		AST match = null;
		List<Class<? extends AST>> typeList = this.getASTTypeList();
		for(int i = 0; i < typeList.size(); i++) {
			Class<? extends AST> item, expected;
			item = code.get(i).getClass();
			expected = typeList.get(i);
			if(!item.equals(expected)) {
				throw new MismatchException("Phrase " + this.getClass() + " does not match code sequence");
			}
		}
		
		match = formatElement(code);
		return match;
	}
	
	/**
	 * 
	 * @return the ordered list of terminal classes that make up a
	 * properly formatted phrase of this kind.
	 */
	public List<Class<? extends AST>> getASTTypeList();
	
	/**
	 * <p>Code meant to establish an abstract syntaxt tree of a specific class, custom to the
	 * PhraseStructure, using data in a code list.</p>
	 * 
	 * <p>Restrictions on the Java language prevent this method from returning anything more
	 * specific than an AST; as it is meant to be integrated into an enumeration for a language.
	 * Since it is a rare exception for a language to actively modify itself during compilation
	 * (example: Ruby), it is still possible to generate additional implementations on the fly.</p>
	 * 
	 * @param code list of ASTs beginning at the point of insertion. Note that no change occurs, here, in code.
	 * @return an AST of a specific kind, formatted to match the trees available in code.
	 */
	public AST formatElement(final List<AST> code);
}
