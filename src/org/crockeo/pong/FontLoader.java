package org.crockeo.pong;

import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;

import java.awt.FontFormatException;
import java.awt.Color;
import java.awt.Font;

import java.io.IOException;
import java.io.File;

class FontLoaderRaw {
	public UnicodeFont loadFont(String s, Float pnt)
			throws SlickException, FontFormatException, IOException {
		UnicodeFont font = new UnicodeFont(Font.createFont(Font.TRUETYPE_FONT,
														   new File(s)).deriveFont(pnt));
		
		font.getEffects().add(new ColorEffect(Color.white));
		font.addAsciiGlyphs();
		font.loadGlyphs();
		
		return font;
	}
}