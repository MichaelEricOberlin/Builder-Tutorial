package oberlin.builder.scanner;

import java.util.*;

public class NullaryScanner extends Scanner {

	@Override
	public List<String> tokenize(String code) {
		List<String> list = new ArrayList<String>();
		list.add(code);
		return list;
	}

}
