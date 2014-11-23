package oberlin.builder.parser.ast.pattern;

import java.util.*;
import java.util.regex.*;

import oberlin.builder.parser.ast.*;

import com.google.common.collect.*;

public class ASTPattern {
	/**Original pattern for class names*/
	private final Pattern pattern;
	
	/**Mapping of String names to AST classes*/
	/* Start with an empty BiMap. */
	/* This should probably be a Singleton, instead of a static. */
	private static volatile ImmutableBiMap<String, Class<? extends AST>> typeNameMap
		= (new ImmutableBiMap.Builder<String, Class<? extends AST>>()).build();
//	private Map typeNameMap = new HashMap<String, Class<? extends AST>>();
	
	public ASTPattern(Pattern pattern) {
		StringBuilder regex = new StringBuilder(pattern.pattern());
		
		//First, clip through the passed regex, finding each expression type
		ImmutableList<String> list = (new ImmutableList.Builder()).addAll(typeNameMap.keySet().iterator()).build();
		int offset = 0;
		for(int i = 0; i < typeNameMap.keySet().size(); i++) {
			String name = list.get(i);
			
			Matcher matcher = Pattern.compile(name).matcher(regex.toString().substring(offset));
			if(matcher.find()) {
				regex.insert(offset + matcher.start(), "(");
				regex.insert(offset + matcher.end() + 1, ")");	//+1 is to count the "(" that we just inserted
				offset += matcher.end() + 1;
				i = 0;
			}
		}
		//if block ensures that pattern always checks the beginning of the String,
		//even if the original pattern did not start with a ^
//		if(regex.toString().startsWith("^")) {
//			this.pattern = pattern;
//		} else {
			this.pattern = Pattern.compile("^" + regex);
//		}
			//Note: It is highly unlikely that a "^" will appear in a syntax declaration
	}
	
	public ASTPattern(String pattern) {
		this(Pattern.compile(pattern));
	}
	
	/**
	 * Checks the provided code for a match to the internal regular expression of types.
	 * If a type is found, then the code list will be altered, in that the constituent
	 * nodes will be removed from the beginning of the list, and the resultant node
	 * will be injected in their place.
	 * 
	 * @param code view of AST list, which starts at the very point where the expression
	 * is expected to begin.
	 * @return true if a match was found, false otherwise
	 */
	public boolean match(List<AST> code) {
		//first, create a string matching the code list
		String szCode = makeString(code);
		
		//second, check this string against the internal regex
		if(pattern.matcher(szCode).find()) {
			
			return true;
		} else {
			//Just a placeholder for now, for the sake of compilation;
			//in the future, create a view of the list starting at the
			//next token, via List.subList(…), and call recursively.
			//
			//This may have to happen externally.
			return false;
		}
		
		
		
		/*
		 * This is good, but not technically enough. We need to not only
		 * be able to match the sequence to the regular expression;
		 * we need to be able to pull out each type and create an AST
		 * to match.
		 * 
		 * One way to do this is to preprocess the regex. Box each
		 * recognized token in parentheses, and feed the token to
		 * the AST.
		 */
	}
	
	public String makeString(List<AST> code) {
		BiMap<String, Class<? extends AST>> nameMap = getTypeNameMap();
		BiMap<Class<? extends AST>, String> classMap = nameMap.inverse();
		
		StringBuilder result = new StringBuilder();
		for(AST ast : code) {
			Class<? extends AST> cls = ast.getClass();
			result.append(classMap.get(cls)).append(" ");
		}
		
		return result.toString();
	}
	
	//GETTERS/SETTERS
	
	public static BiMap<String, Class<? extends AST>> getTypeNameMap() {
		return typeNameMap;
	}

	public static void setTypeNameMap(Map<String, Class<? extends AST>> typeNameMap) {
		/*
		 * We don't expect the map to change during parsing, so make it an immutable map. If
		 * the language does require its own structure to change (potential example: Ruby),
		 * then this can still be overrided.
		 */
		ASTPattern.typeNameMap = ImmutableBiMap.copyOf(typeNameMap);
	}

	public Pattern getPattern() {
		return pattern;
	}
	
	
}
