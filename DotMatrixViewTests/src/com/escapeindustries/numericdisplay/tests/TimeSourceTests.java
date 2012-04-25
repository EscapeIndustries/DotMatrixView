package com.escapeindustries.numericdisplay.tests;

import com.escapeindustries.numericdisplay.FormattedTime;
import junit.framework.TestCase;

public class TimeSourceTests extends TestCase {

	private TestTimeSource source;
	private FormattedTime formatter;
	
	@Override
	protected void setUp() throws Exception {
		source = new TestTimeSource();
		formatter = new FormattedTime(source);
	}
	
	public void testGetNow() {
		// Arrange
		source.setTime(16, 7, 58);
		String expectedTime = "16:07:58";
		// Act
		String result = formatter.getNow();
		// Assert
		assertEquals(expectedTime, result);
	}
}
