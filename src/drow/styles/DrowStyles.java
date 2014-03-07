package drow.styles;

import java.awt.Color;

import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class DrowStyles {
	
	//styledDocument.setCharacterAttributes(0, 10, DrowStyles.applyStyleBold(), false);
	
	private static StyleContext styleContext;
	
	private static Style styleFontFamily;
	private static Style styleFontSize;
	private static Style styleLeftIndent;
	private static Style styleRightIndent;
	private static Style styleFirstLineIndent;
	private static Style styleSpaceAbove;
	private static Style styleSpaceBelow;
	private static Style styleBold;
	private static Style styleItalic;
	private static Style styleUnderline;
	private static Style styleStrikeThrough;
	private static Style styleSuperscript;
	private static Style styleSubscript;
	private static Style styleForegroundColor;
	private static Style styleBackgroundColor;
	
	public DrowStyles() {
		
		styleContext = new StyleContext();
		
		styleFontFamily 	 = styleContext.addStyle("", null);
		styleFontSize 	 	 = styleContext.addStyle("", null);
		styleLeftIndent 	 = styleContext.addStyle("", null);
		styleRightIndent 	 = styleContext.addStyle("", null);
		styleFirstLineIndent = styleContext.addStyle("", null);
		styleSpaceAbove 	 = styleContext.addStyle("", null);
		styleSpaceBelow 	 = styleContext.addStyle("", null);
		styleBold 			 = styleContext.addStyle("", null);
		styleItalic 		 = styleContext.addStyle("", null);
		styleUnderline 		 = styleContext.addStyle("", null);
		styleStrikeThrough 	 = styleContext.addStyle("", null);
		styleSuperscript 	 = styleContext.addStyle("", null);
		styleSubscript 		 = styleContext.addStyle("", null);
		styleForegroundColor = styleContext.addStyle("", null);
		styleBackgroundColor = styleContext.addStyle("", null);
	}
	
	public static Style applyStyleFontFamily(String s) {
		DrowStyleConstants.FONT_FAMILY = s;
		StyleConstants.setFontFamily(styleFontFamily, s);
		
		return styleFontFamily;
	}
	
	public static Style applyStyleFontSize(int i) {
		DrowStyleConstants.FONT_SIZE = i;
		StyleConstants.setFontSize(styleFontSize, i);
		
		return styleFontSize;
	}
	
	public static Style applyStyleLeftIndent(float f) {
		DrowStyleConstants.LEFT_INDENT = f;
		StyleConstants.setLeftIndent(styleLeftIndent, f);
		
		return styleLeftIndent;
	}
	
	public static Style applyStyleRightIndent(float f) {
		DrowStyleConstants.RIGHT_INDENT = f;
		StyleConstants.setLeftIndent(styleRightIndent, f);
		
		return styleRightIndent;
	}
	
	public static Style applyStyleFirstLineIndent(float f) {
		DrowStyleConstants.FIRST_LINE_INDENT = f;
		StyleConstants.setLeftIndent(styleFirstLineIndent, f);
		
		return styleFirstLineIndent;
	}
	
	public static Style applyStyleSpaceAbove(float f) {
		DrowStyleConstants.SPACE_ABOVE = f;
		StyleConstants.setSpaceAbove(styleSpaceAbove, f);
		
		return styleSpaceAbove;
	}
	
	public static Style applyStyleSpaceBelow(float f) {
		DrowStyleConstants.SPACE_BELOW = f;
		StyleConstants.setSpaceBelow(styleSpaceBelow, f);
		
		return styleSpaceBelow;
	}
	
	public static Style applyStyleBold() {
		DrowStyleConstants.IS_BOLD = !DrowStyleConstants.IS_BOLD;
		StyleConstants.setBold(styleBold, DrowStyleConstants.IS_BOLD);
		
		return styleBold;
	}
	
	public static Style applyStyleItalic() {
		DrowStyleConstants.IS_ITALIC = !DrowStyleConstants.IS_ITALIC;
		StyleConstants.setItalic(styleItalic, DrowStyleConstants.IS_ITALIC);
		
		return styleItalic;
	}
	
	public static Style applyStyleUnderline() {
		DrowStyleConstants.IS_UNDERLINE = !DrowStyleConstants.IS_UNDERLINE;
		StyleConstants.setUnderline(styleUnderline, DrowStyleConstants.IS_UNDERLINE);
		
		return styleUnderline;
	}
	
	public static Style applyStyleStrikeThrough() {
		DrowStyleConstants.IS_STRIKETHROUGH = !DrowStyleConstants.IS_STRIKETHROUGH;
		StyleConstants.setStrikeThrough(styleStrikeThrough, DrowStyleConstants.IS_STRIKETHROUGH);
		
		return styleStrikeThrough;
	}
	
	public static Style applyStyleSuperScript() {
		DrowStyleConstants.IS_SUPERSCRIPT = !DrowStyleConstants.IS_SUPERSCRIPT;
		StyleConstants.setSuperscript(styleSuperscript, DrowStyleConstants.IS_SUPERSCRIPT);
		
		return styleSuperscript;
	}
	
	public static Style applyStyleSubScript() {
		DrowStyleConstants.IS_SUBSCRIPT = !DrowStyleConstants.IS_SUBSCRIPT;
		StyleConstants.setSubscript(styleSubscript, DrowStyleConstants.IS_SUBSCRIPT);
		
		return styleSubscript;
	}
	
	public static Style applyStyleForegroundColor(Color c) {
		DrowStyleConstants.FOREGROUND_COLOR = c;
		StyleConstants.setForeground(styleForegroundColor, c);
		
		return styleForegroundColor;
	}
	
	public static Style applyStyleBackgroundColor(Color c) {
		DrowStyleConstants.BACKGROUND_COLOR = c;
		StyleConstants.setBackground(styleBackgroundColor, c);
		
		return styleBackgroundColor;
	}
}
