package oberlin.builder.scanner;

import java.util.*;

public class NullaryScanner implements Scanner<NullaryGrammar> {

	@Override
	public List<String> apply(String code) {
		List<String> list = new ArrayList<String>();
		return list;
	}

	@Override
	public Class<NullaryGrammar> getGrammar() {
		return NullaryGrammar.class;
	}

}
