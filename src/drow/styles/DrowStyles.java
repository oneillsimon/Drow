package drow.styles;

import java.awt.Color;

import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 * Static class containing the style object to apply to the document.
 * <p>
 * @author Simon O'Neill
 * <p>
 */
public class DrowStyles {
	
	/** Global style context. */
	private static StyleContext styleContext = new StyleContext();
	
	/** Style context for the font family. */
	private static Style styleFontFamily	  = styleContext.addStyle("", null);
	
	/** Style context for the font size. */
	private static Style styleFontSize		  = styleContext.addStyle("", null);
	
	/** Style context for the left indent. */
	private static Style styleLeftIndent	  = styleContext.addStyle("", null);
	
	/** Style context for the right indent. */
	private static Style styleRightIndent	  = styleContext.addStyle("", null);
	
	/** Style context for the first line indent. */
	private static Style styleFirstLineIndent = styleContext.addStyle("", null);
	
	/** Style context for the space above the text. */
	private static Style styleSpaceAbove	  = styleContext.addStyle("", null);
	
	/** Style context for the space below the text. */
	private static Style styleSpaceBelow	  = styleContext.addStyle("", null);
	
	/** Style context for bold. */
	private static Style styleBold			  = styleContext.addStyle("", null);
	
	/** Style context for italic. */
	private static Style styleItalic		  = styleContext.addStyle("", null);
	
	/** Style context for underline. */
	private static Style styleUnderline		  = styleContext.addStyle("", null);
	
	/** Style context for strike through. */
	private static Style styleStrikeThrough	  = styleContext.addStyle("", null);
	
	/** Style context for superscript. */
	private static Style styleSuperscript	  = styleContext.addStyle("", null);
	
	/** Style context for subscript. */
	private static Style styleSubscript		  = styleContext.addStyle("", null);
	
	/** Style context for font color. */
	private static Style styleForegroundColor = styleContext.addStyle("", null);
	
	/** Style context for font highlight color. */
	private static Style styleBackgroundColor = styleContext.addStyle("", null);
	
	/**
	 * Apply font family style to text.
	 * @param s - The text to apply the style to.
	 * @return - The style.
	 */
	public static Style applyStyleFontFamily(String s) {
		DrowStyleConstants.FONT_FAMILY = s;
		StyleConstants.setFontFamily(styleFontFamily, s);
		
		return styleFontFamily;
	}
	
	/**
	 * Apply font size style to text.
	 * @param s - The text to apply the style to.
	 * @return - The style.
	 */
	public static Style applyStyleFontSize(int i) {
		DrowStyleConstants.FONT_SIZE = i;
		StyleConstants.setFontSize(styleFontSize, i);
		
		return styleFontSize;
	}
	
	/**
	 * Apply left indent style to text.
	 * @param s - The text to apply the style to.
	 * @return - The style.
	 */
	public static Style applyStyleLeftIndent(float f) {
		DrowStyleConstants.LEFT_INDENT = f;
		StyleConstants.setLeftIndent(styleLeftIndent, f);
		
		return styleLeftIndent;
	}
	
	/**
	 * Apply right indent style to text.
	 * @param s - The text to apply the style to.
	 * @return - The style.
	 */
	public static Style applyStyleRightIndent(float f) {
		DrowStyleConstants.RIGHT_INDENT = f;
		StyleConstants.setLeftIndent(styleRightIndent, f);
		
		return styleRightIndent;
	}
	
	/**
	 * Apply first line indent style to text.
	 * @param s - The text to apply the style to.
	 * @return - The style.
	 */
	public static Style applyStyleFirstLineIndent(float f) {
		DrowStyleConstants.FIRST_LINE_INDENT = f;
		StyleConstants.setLeftIndent(styleFirstLineIndent, f);
		
		return styleFirstLineIndent;
	}
	
	/**
	 * Apply space above style to text.
	 * @param s - The text to apply the style to.
	 * @return - The style.
	 */
	public static Style applyStyleSpaceAbove(float f) {
		DrowStyleConstants.SPACE_ABOVE = f;
		StyleConstants.setSpaceAbove(styleSpaceAbove, f);
		
		return styleSpaceAbove;
	}
	
	/**
	 * Apply space below style to text.
	 * @param s - The text to apply the style to.
	 * @return - The style.
	 */
	public static Style applyStyleSpaceBelow(float f) {
		DrowStyleConstants.SPACE_BELOW = f;
		StyleConstants.setSpaceBelow(styleSpaceBelow, f);
		
		return styleSpaceBelow;
	}
	
	/**
	 * Apply bold style to text.
	 * @param s - The text to apply the style to.
	 * @return - The style.
	 */
	public static Style applyStyleBold() {
		DrowStyleConstants.IS_BOLD = !DrowStyleConstants.IS_BOLD;
		StyleConstants.setBold(styleBold, DrowStyleConstants.IS_BOLD);
		
		return styleBold;
	}
	
	/**
	 * Apply italic style to text.
	 * @param s - The text to apply the style to.
	 * @return - The style.
	 */
	public static Style applyStyleItalic() {
		DrowStyleConstants.IS_ITALIC = !DrowStyleConstants.IS_ITALIC;
		StyleConstants.setItalic(styleItalic, DrowStyleConstants.IS_ITALIC);
		
		return styleItalic;
	}
	
	/**
	 * Apply underline style to text.
	 * @param s - The text to apply the style to.
	 * @return - The style.
	 */
	public static Style applyStyleUnderline() {
		DrowStyleConstants.IS_UNDERLINE = !DrowStyleConstants.IS_UNDERLINE;
		StyleConstants.setUnderline(styleUnderline, DrowStyleConstants.IS_UNDERLINE);
		
		return styleUnderline;
	}
	
	/**
	 * Apply strike through style to text.
	 * @param s - The text to apply the style to.
	 * @return - The style.
	 */
	public static Style applyStyleStrikeThrough() {
		DrowStyleConstants.IS_STRIKETHROUGH = !DrowStyleConstants.IS_STRIKETHROUGH;
		StyleConstants.setStrikeThrough(styleStrikeThrough, DrowStyleConstants.IS_STRIKETHROUGH);
		
		return styleStrikeThrough;
	}
	
	/**
	 * Apply superscript style to text.
	 * @param s - The text to apply the style to.
	 * @return - The style.
	 */
	public static Style applyStyleSuperScript() {
		DrowStyleConstants.IS_SUPERSCRIPT = !DrowStyleConstants.IS_SUPERSCRIPT;
		StyleConstants.setSuperscript(styleSuperscript, DrowStyleConstants.IS_SUPERSCRIPT);
		
		return styleSuperscript;
	}
	
	/**
	 * Apply subscript style to text.
	 * @param s - The text to apply the style to.
	 * @return - The style.
	 */
	public static Style applyStyleSubScript() {
		DrowStyleConstants.IS_SUBSCRIPT = !DrowStyleConstants.IS_SUBSCRIPT;
		StyleConstants.setSubscript(styleSubscript, DrowStyleConstants.IS_SUBSCRIPT);
		
		return styleSubscript;
	}
	
	/**
	 * Apply font color to text.
	 * @param s - The text to apply the style to.
	 * @return - The style.
	 */
	public static Style applyStyleForegroundColor(Color c) {
		DrowStyleConstants.FOREGROUND_COLOR = c;
		StyleConstants.setForeground(styleForegroundColor, c);
		
		return styleForegroundColor;
	}
	
	/**
	 * Apply highlight color to text.
	 * @param s - The text to apply the style to.
	 * @return - The style.
	 */
	public static Style applyStyleBackgroundColor(Color c) {
		DrowStyleConstants.BACKGROUND_COLOR = c;
		StyleConstants.setBackground(styleBackgroundColor, c);
		
		return styleBackgroundColor;
	}
}