package oberlin.builder.scanner;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import java.util.stream.Collectors;

import oberlin.builder.parser.MismatchException;
import oberlin.builder.scanner.lexeme.*;
import java.util.logging.*;

public abstract class Scanner {
	
	private Logger logger = Logger.getLogger("Scanner");
	
	//PROTECTED FIELDS
	//list of comment-identifying patterns, to be removed before parsing
	protected ObservableList<Pattern> comments = new ObservableList<Pattern>(new LinkedList<>());
	//list of delimiter-specifying patterns, to be read within
	protected ObservableList<Pattern> delimiters = new ObservableList<Pattern>(new LinkedList<>());
	//list of items to be completely removed from the list, should they show up (example: usually all-whitespace values)
	protected ObservableList<Pattern> clearables = new ObservableList<Pattern>(new LinkedList<>());
	//list of structural parameters for singular items, to pull them apart when everything else is said and done (example: a+, b becomes a, +, b)
	protected ObservableList<Class<? extends Lexeme>> lexemes
		= new ObservableList<Class<? extends Lexeme>>(new LinkedList<>());
	
	//PRIVATE FIELDS
	private ListChangeObserver delimiterObserver = new ListChangeObserver(){
		@Override
		public void hook() {
			delimiter = generate(getDelimiters());
		}};
//	private ListChangeObserver validObserver = new ListChangeObserver(){
//		@Override
//		public void hook() {
//			/*
//			 * This is a little different, as lexemes are not simply patterns.
//			 * Lexemes must independently test for their relevance to a token.
//			 */
//			valid = generate(getLexemes());
//		}};
	private ListChangeObserver commentObserver = new ListChangeObserver(){
		@Override
		public void hook() {
			comment = generate(getComments());
		}};
	private ListChangeObserver clearableObserver = new ListChangeObserver(){
		@Override
		public void hook() {
			clearable = generate(getClearables());
		}};
		
	protected AbstractPatternScanner<List<String>> commentScanner = new AbstractPatternScanner<List<String>>() {

		/**
		 * Finds all declared comment patterns in parameter string, and removes them
		 * 
		 * @param code
		 *            the unaltered code string
		 * @return the original string, minus any comment patterns
		 */
		@Override
		public List<String> scan(List<String> code) {
			List<String> uncommented = new ArrayList<>();
			
			for(String sz : code) {
				Matcher matcher = comment.matcher(sz);
				
				while(matcher.find()) {
					sz = matcher.replaceAll("");
				}
				
				uncommented.add(sz);
			}
			
			return uncommented;
		}

	};
	protected AbstractPatternScanner<List<String>>	delimiterScanner = new AbstractPatternScanner<List<String>>() {

		/**
		 * Splits code into a new list, with divisions added for each declared
		 * delimiter. As an example, if parentheses are delimiters, and spaces are
		 * delimiters, then {a, +, b, -, (c * d)} would become {a, +, b, -, (, c, *,
		 * d, )}.
		 * 
		 * @param code
		 *            original list
		 * @return new list, properly divided along each delimiter.
		 */
		@Override
		public List<String> scan(List<String> code) {
			// current/active collection of tokens
			List<String> tokens = new ArrayList<>();
			
			for(String sz : code) {
				Matcher matcher = delimiter.matcher(sz);
				
				int start = 0;
				int end = 0;
				while(matcher.find()) {
					
					//handle anything between the delimiter sets first
					end = matcher.start();
					String between = sz.substring(start, end);
					
					if(between.length() > 0)
						tokens.add(between);
					
					//next, add the internals
					tokens.add(matcher.group(1));
					tokens.add(matcher.group(2));
					tokens.add(matcher.group(3));
					start = matcher.end();
				}
				
			}
			
			code.clear();
			code.addAll(tokens);
			tokens.clear();

			return code;
		}
		
	},
		clearableScanner = new AbstractPatternScanner<List<String>>(){

			@Override
			public List<String> scan(List<String> code) {
				/*
				 * If you haven't seen this logic style before:
				 * 
				 * The issue with most loops is that after an item is removed from a
				 * List, every item following it has its location automatically
				 * decremented. Thus, this solution is to page through it explicitly
				 * by index, not just by value. When a match is found, remove it
				 * from the list, then DECREMENT the index, to account for the shift
				 * of every value after that point.
				 * 
				 * Eventually, index and size catch up to each other, and the
				 * program pointer breaks from the loop.
				 */
				for (int i = 0; i < code.size(); i++) {
					String sz = code.get(i);
					Matcher matcher = clearable.matcher(sz);
					if (matcher.find()) {
						code.remove(i);
						i--;
					}
				}
				
				return code;
			}};
	protected AbstractPatternScanner<List<Lexeme>> validScanner = new AbstractPatternScanner<List<Lexeme>>(){

			@Override
			public List<Lexeme> scan(List<String> code) {
				// Our final list of properly separated lexemes strings
				List<Lexeme> separated = new ArrayList<>();

				// for(String sz : code) {
				String sz;
				Iterator<String> iterator = code.iterator();

				try {
					// list of each lexemes found for this entry
					List<Lexeme> lexem = new ArrayList<>();

					while (iterator.hasNext()) {
						//get the next entry in the list; if it's empty, just continue and grab the one after that
						sz = iterator.next();
						if(sz.length() == 0) continue;
						
						do {
							// for the sake of catching items that simply don't match
							// our specified grammar,
							// keep a boolean around to test after the for loop breaks.
							// Set it to true if
							// an item is found, leave it as false if one is not; use
							// its value to determine
							// the throwing of an exception.
							boolean matched = false;
							
							Iterator<Class<? extends Lexeme>> lexIterator = getLexemes().iterator();
							while(lexIterator.hasNext()) {
								Class<? extends Lexeme> lexType = null;	//Just to keep a tag on what the lexeme type is
								//(Mostly for the sake of exception handling)
								
								try {
									Lexeme lex;
									lexType = lexIterator.next();
									
									lex = lexType.getConstructor(String.class).newInstance(sz);
									//An InvocationTargetException will interrupt us here if the lexeme can't be
									//instantiated; otherwise, we have a match.
									
									//Next, cut it out of the string
									sz = sz.substring(lex.getCharacterCount());
									
									separated.add(lex);
									matched = true;
									break;
								} catch(InvocationTargetException ex) {
									//Just a mismatch, so continue
									if(ex.getCause() instanceof MismatchException)
										continue;
									else
										//or not.
										logger.log(Level.SEVERE, "Invocation exception outside of"
												+ " expectations occurred.", ex);
								//If any of these happen, you have a real problem:
								} catch (InstantiationException | IllegalAccessException | IllegalArgumentException ex) {
									logger.log(Level.SEVERE, "Reflection exception: ", ex);
								} catch (NoSuchMethodException ex) {
									logger.log(Level.SEVERE, "Constructor for Lexeme couldn't be fetched,"
											+ " check code syntax", ex);
								} catch(SecurityException ex) {
									logger.log(Level.SEVERE, "Construction of Lexeme " + lexType + " triggered a " +
											"SecurityException; probable cause: check to insure that at least one"
											+ " class loader is shared.", ex);
								}

							}
							
							// If sz is now empty,
							// the do loop will
							// escape; if it isn't, the pattern check will
							// begin from the top
							// and apply to the new (truncated) string.
							if (!matched) {
								Logger.getLogger("Builder").log(Level.WARNING, "Syntax error: " + sz);
								return separated;
							}

						} while (!sz.isEmpty()); // do this until nothing is left in the
													// string
					}

					separated.addAll(lexem);

				} catch (NoSuchElementException ex) {
					// Just return the empty set.
				}

				return separated;
			}};
	
	
	
	/*
	 * These guys are interesting. Delimiter is a constructed pattern from every item in delimiters; so that the scan can be done with a single
	 * regular expression instead of an iteration over a collection of them. For single-file builders that aren't frequently in use, this might
	 * seem small; but if you're dealing with interpreting something like HTTP, being able to complete many builds per second is of great
	 * import, and this shortcut will come in handy.
	 * 
	 * Note that it is a private field; there is never any occasion for the outside world to change or even look at its contents. It is
	 * managed entirely internally, by the Scanner. See setDelimiters(). 
	 */
	private Pattern delimiter, comment, clearable; 
	
	//CONSTRUCTORS
	public Scanner() {
		delimiter = generate(getDelimiters());
//		valid = generate(getLexemes());
		comment = generate(getComments());
		clearable = generate(getClearables());
		
		this.delimiters.addObserver(delimiterObserver);
//		this.lexemes.addObserver(validObserver);
		this.comments.addObserver(commentObserver);
		this.clearables.addObserver(clearableObserver);
	}
	
	protected List<Pattern> getComments() {
		return comments;
	}

	protected void setComments(List<Pattern> comments) {
		this.comments = new ObservableList<Pattern>(comments);
		((ObservableList<Pattern>)comments).addObserver(commentObserver);
	}

	protected List<Pattern> getDelimiters() {
		return delimiters;
	}

	protected void setDelimiters(List<Pattern> delimiters) {
		this.delimiters = new ObservableList<Pattern>(delimiters);
		((ObservableList<Pattern>)delimiters).addObserver(delimiterObserver);
	}

	protected List<Pattern> getClearables() {
		return clearables;
	}

	protected void setClearables(List<Pattern> clearables) {
		this.clearables = new ObservableList<Pattern>(clearables);
		((ObservableList<Pattern>)clearables).addObserver(clearableObserver);
	}

	protected List<Class<? extends Lexeme>> getLexemes() {
		return lexemes;
	}

	protected void setLexemes(List<Class<? extends Lexeme>> lexemes) {
		this.lexemes = new ObservableList<Class<? extends Lexeme>>(lexemes);
//		((ObservableList<Lexeme>)lexemes).addObserver(validObserver);
	}

	// INTRINSIC METHODS
	public List<Lexeme> scan(String code) {
		//final collection of tokens (after scanning)
		List<String> ultimate = new ArrayList<>();
		
		ultimate.add(code);
		
		//This little block is on the border of deserving its own method.
		List<PatternScanner<List<String>, List<String>>> scanners = new LinkedList<>();
		scanners.add(commentScanner);
		scanners.add(delimiterScanner);
		scanners.add(clearableScanner);
		
		for(PatternScanner<List<String>, List<String>> p : scanners) {
			ultimate = p.scan(ultimate);
		}
		
		return validScanner.scan(ultimate);
	}
	
	private Pattern generate(Collection<Pattern> patterns) {
		StringBuilder builder = new StringBuilder();
		
		Iterator<Pattern> iterator = patterns.iterator();
		
		while(iterator.hasNext()) {
			builder.append(iterator.next().pattern());
			
			if(iterator.hasNext()) builder.append("|");
		}
		
		return Pattern.compile(builder.toString());
	}

}
