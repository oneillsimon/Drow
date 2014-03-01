package drow.styles;

import java.awt.Color;

import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class DrowStyles {
	
	private static StyleContext styleContext;
	
	private static Style styleFontFamily;
	private static Style styleFontSize;
	private static Style styleLeftIndent;
	private static Style styleRightIndent;
	private static Style styleFirstLineIndent;
	private static Style styleSpaceAbove;
	private static Style styleSpaceBelow;
	public static Style styleBold;
	private static Style styleItalic;
	private static Style styleUnderline;
	private static Style styleStrikeThrough;
	private static Style styleSuperscript;
	private static Style styleSubscript;
	private static Style styleForegroundColor;
	private static Style styleBackgroundColor;
	
	public DrowStyles() {
		
		styleContext = new StyleContext();
		
		styleFontFamily = styleContext.addStyle("", null);
		styleFontSize = styleContext.addStyle("", null);
		styleLeftIndent = styleContext.addStyle("", null);
		styleRightIndent = styleContext.addStyle("", null);
		styleFirstLineIndent = styleContext.addStyle("", null);
		styleSpaceAbove = styleContext.addStyle("", null);;
		styleSpaceBelow = styleContext.addStyle("", null);;
		styleBold = styleContext.addStyle("bold", null);
		styleItalic = styleContext.addStyle("", null);
		styleUnderline = styleContext.addStyle("", null);
		styleStrikeThrough = styleContext.addStyle("", null);
		styleSuperscript = styleContext.addStyle("", null);
		styleSubscript = styleContext.addStyle("", null);
		styleForegroundColor = styleContext.addStyle("", null);
		styleBackgroundColor = styleContext.addStyle("", null);
	}
	
	public static Style applyStyleFontFamily(String s) {
		DrowStyleConstants.fontFamily = s;
		StyleConstants.setFontFamily(styleFontFamily, s);
		
		return styleFontFamily;
	}
	
	public static Style applyStyleFontSize(int i) {
		DrowStyleConstants.fontSize = i;
		StyleConstants.setFontSize(styleFontSize, i);
		
		return styleFontSize;
	}
	
	public static Style applyStyleLeftIndent(float f) {
		DrowStyleConstants.leftIndent = f;
		StyleConstants.setLeftIndent(styleLeftIndent, f);
		
		return styleLeftIndent;
	}
	
	public static Style applyStyleRightIndent(float f) {
		DrowStyleConstants.rightIndent = f;
		StyleConstants.setLeftIndent(styleRightIndent, f);
		
		return styleRightIndent;
	}
	
	public static Style applyStyleFirstLineIndent(float f) {
		DrowStyleConstants.firstLineIndent = f;
		StyleConstants.setLeftIndent(styleFirstLineIndent, f);
		
		return styleFirstLineIndent;
	}
	
	public static Style applyStyleSpaceAbove(float f) {
		DrowStyleConstants.spaceAbove = f;
		StyleConstants.setSpaceAbove(styleSpaceAbove, f);
		
		return styleSpaceAbove;
	}
	
	public static Style applyStyleSpaceBelow(float f) {
		DrowStyleConstants.spaceBelow = f;
		StyleConstants.setSpaceBelow(styleSpaceBelow, f);
		
		return styleSpaceBelow;
	}
	
	public static Style applyStyleBold(boolean b) {
		DrowStyleConstants.isBold = b;
		StyleConstants.setBold(styleBold, b);
		
		return styleBold;
	}
	
	public static Style applyStyleItalic(boolean b) {
		DrowStyleConstants.isItalic = b;
		StyleConstants.setItalic(styleItalic, b);
		
		return styleItalic;
	}
	
	public static Style applyStyleUnderline(boolean b) {
		DrowStyleConstants.isUnderlined = b;
		StyleConstants.setUnderline(styleUnderline, b);
		
		return styleUnderline;
	}
	
	public static Style applyStyleStrikeThrough(boolean b) {
		DrowStyleConstants.isStrikeThrough = b;
		StyleConstants.setStrikeThrough(styleStrikeThrough, b);
		
		return styleStrikeThrough;
	}
	
	public static Style applyStyleSuperScript(boolean b) {
		DrowStyleConstants.isSuperscript = b;
		StyleConstants.setSuperscript(styleSuperscript, b);
		
		return styleSuperscript;
	}
	
	public static Style applyStyleSubScript(boolean b) {
		DrowStyleConstants.isSubscript = b;
		StyleConstants.setSubscript(styleSubscript, b);
		
		return styleSubscript;
	}
	
	public static Style applyStyleForegroundColor(Color c) {
		DrowStyleConstants.foregroundColor = c;
		StyleConstants.setForeground(styleForegroundColor, c);
		
		return styleForegroundColor;
	}
	
	public static Style applyStyleBackgroundColor(Color c) {
		DrowStyleConstants.backgroundColor = c;
		StyleConstants.setBackground(styleBackgroundColor, c);
		
		return styleBackgroundColor;
	}
}
