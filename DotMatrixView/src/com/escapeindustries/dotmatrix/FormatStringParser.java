package com.escapeindustries.dotmatrix;

import java.util.ArrayList;
import java.util.List;

public class FormatStringParser {

	private GlyphFactory factory;

	public FormatStringParser(GlyphFactory factory) {
		super();
		this.factory = factory;
	}

	public Glyph[] parse(String format) {
		List<Glyph> glyphs = new ArrayList<Glyph>();
		for (int i = 0; i < format.length(); i++) {
			char c = format.charAt(i);
			if (c == '0') {
				glyphs.add(factory.createDigit());
			}
			if (c == ':') {
				glyphs.add(factory.createSeperator());
			}
			if (c == ' ') {
				glyphs.add(factory.createSpace());
			}
		}
		Glyph[] results = new Glyph[glyphs.size()];
		results = glyphs.toArray(results);
		return results;
	}

}
