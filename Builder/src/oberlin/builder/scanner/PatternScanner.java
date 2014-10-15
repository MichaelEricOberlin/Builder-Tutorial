package oberlin.builder.scanner;

import java.util.*;

@FunctionalInterface
interface PatternScanner<E> {
	public E scan(E code);
}
