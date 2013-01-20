package com.escapeindustries.dotmatrix;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class to parse format strings.
 * 
 * @author Mark Roberts
 * 
 */
public class FormatStringParser {

	private GlyphFactory factory;

	/**
	 * Construct an instance, configuring it to use a specific
	 * {@link GlyphFactory}.
	 * 
	 * @param factory
	 *            The {@link GlyphFactory} that will be used when parsing format
	 *            strings into sets of {@link Glyph}s
	 */
	public FormatStringParser(GlyphFactory factory) {
		super();
		this.factory = factory;
	}

	/**
	 * Parse a format string into a set of {@link Glyph}s
	 * 
	 * @param format
	 *            The format to be parsed. The format can be made up of '0' (a
	 *            digit), ':" (a colon) and ' ' (space - a gap between other
	 *            {@link Glyph}s)
	 * @return All the {@link Glyph}s that were found, working from left to
	 *         right.
	 */
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
