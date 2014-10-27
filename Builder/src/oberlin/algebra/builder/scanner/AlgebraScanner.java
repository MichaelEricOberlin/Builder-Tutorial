package oberlin.algebra.builder.scanner;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;

import oberlin.algebra.builder.scanner.lexemes.EquatorLexeme;
import oberlin.algebra.builder.scanner.lexemes.LParenLexeme;
import oberlin.algebra.builder.scanner.lexemes.OperatorLexeme;
import oberlin.algebra.builder.scanner.lexemes.RParenLexeme;
import oberlin.algebra.builder.scanner.lexemes.WhitespaceLexeme;
import oberlin.algebra.builder.scanner.lexemes.WordLexeme;
import oberlin.builder.*;
import oberlin.builder.scanner.Scanner;
import oberlin.builder.scanner.lexeme.AbstractLexeme;
import oberlin.builder.scanner.lexeme.Lexeme;

/**
 * Simple scanner meant to parse a basic algebraic equation, and simplify it.
 * 
 * @author © Michael Eric Oberlin Oct 11, 2014
 *
 */
public class AlgebraScanner extends Scanner {
	
	public AlgebraScanner() {
		
		//Patterns for a string (not that you can really use one in normal algebra) show up more than one time in the code;
		//so our best bet is to simply have a final carrying it, and only alter the final.
		//
		//if you guessed it, you guessed it — "final" is semantically equivalent to C's "const", and is often taken advantage of by
		//the Java optimizer. Therefore, this is very low risk, as long as the regex string is complete in itself.
		final String str = "\".*[^\\\\]\"";
		
		//Add comment patterns
		List<Pattern> comments = getComments();
		comments.add(Pattern.compile("//.*+$"));	//double forward slash
		comments.add(Pattern.compile("/\\*.*?\\*/"));	// /**/ comments
		
		//add delimiter patterns
		List<Pattern> order = getDelimiters();
		order.add(Pattern.compile("(\\s*)(" + str + "|\\S+)(\\s*)"));	//find what's inside the whitespace, in group 1 (escape sequence: \")
		order.add(Pattern.compile("(\\()(.+?)(\\))"));		//find what's inside the parentheses, in group 1
		
		//add clearables (non-relevant) patterns
		List<Pattern> whitespace = getClearables();
		whitespace.add(Pattern.compile("^\\s*$"));	//just clear all-whitespace strings, and empty strings
		
		//add lexemes grammar patterns
		List<Class<? extends Lexeme>> lexemes = getLexemes();
		lexemes.add(OperatorLexeme.class);
		lexemes.add(EquatorLexeme.class);
		lexemes.add(WordLexeme.class);
		
		lexemes.add(LParenLexeme.class);
		lexemes.add(RParenLexeme.class);
		
		//Purely for the sake of example:
		lexemes.add(WhitespaceLexeme.class);
		
		//Again, purely for the sake of example (of how you would capture a string):
		lexemes.add(new AbstractLexeme(){

			@Override
			public Pattern getPattern() {
				return Pattern.compile(str);
			}}.getClass());
	}
}
