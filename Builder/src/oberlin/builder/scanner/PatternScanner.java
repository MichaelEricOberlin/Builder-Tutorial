package oberlin.builder.scanner;

import java.util.*;

@FunctionalInterface
interface PatternScanner<E, F> {
	public E scan(F code);
}
