package drow.spellchecker;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import drow.highlighter.DrowHighlightManager;
import drow.styles.DrowStyleActionManager;
import drow.styles.DrowStyles;

/**
 * <h1>DictionaryListener</h1>
 * <p>
 * This class is a listener that monitors a JTextPane and compares 
 * entered strings to words contained in a dictionary. If no match is found it will 
 * underline the word, indicating a spelling mistake. If Drow is in Developer mode 
 * language specific keywords will be highlighted using the same method.
 * </p>
 * @author Lee Mc Donald
 * @author Simon O'Neill
 */
public class DictionaryListener implements DocumentListener, CaretListener {

	/** The dictionary containing the words. */
	private Dictionary dictionary;
	
	/** The highlightManager takes care of underlining the text. */
	private DrowHighlightManager highlightManager;
	
	/** The actionManager for styling the text. */
	private DrowStyleActionManager actionManager;
	
	/** Stores the String found at the caret's position. */
	private String wordAtCaret;
	
	/** String to determine if the word should be highlighted or underlined. */
	private String highlightWhat;
	
	/** The textPane being listened to. */
	private JTextPane textPane;
	
	/** The position of the caret. */
	private int dot;
	
	/** The start position in the textPane of the word in question. */
	int wordStart = -1;
	
	/** The end position in the textPane of the word in question. */
	int wordEnd = -1;
	
	/** The event performed on the document. */
	DocumentEvent docEvent;
	
	/**
	 * <h1>Constructor</h1>
	 * @param textPane - the JTextPane to listen to.
	 * @param fileName - The file name of the dictionary to query.
	 */
	public DictionaryListener(JTextPane textPane, String fileName) {
		this(textPane, fileName, "underline", Color.red);
	}
	
	/**
	 * <h1>Constructor</h1>
	 * @param textPane - the JTextPane to listen to.
	 * @param fileName - The file name of the dictionary to query.
	 * @param highlightWhat - What to highlight, the word or and underline.
	 * @param highlightColor - The colour of the highlight
	 */
	public DictionaryListener(JTextPane textPane, String fileName, String highlightWhat, Color highlightColor) {
		textPane.addCaretListener(this);
		
		try {
			dictionary = new Dictionary(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		highlightManager = new DrowHighlightManager(textPane, highlightColor);
		wordAtCaret = "";
		
		this.actionManager = new DrowStyleActionManager(null);
		this.highlightWhat = highlightWhat;
		this.textPane = textPane;
	}
	
	/**
	 * Paints the word using highlightManager.getColour().
	 * @param offset - The position of the word in the JTextPane.
	 * @param length - The length of the word.
	 */
	void paintWord(int offset, int length) {
	    class OneShotTask implements Runnable {
	        int offset;
	        int length;
	        
	        private OneShotTask(int o, int l) {
	        	offset = o;
	        	length = l;
	        	}
	        public void run() {
	        	actionManager.styleText(textPane, DrowStyles.applyStyleForegroundColor(highlightManager.getColour()), offset, length);
	        }
	    }
	    Thread t = new Thread(new OneShotTask(offset, length));
	    t.start();
	}
	
	@Override
	public void changedUpdate(DocumentEvent e) {
	}

	/**
	 * Method called when something is inserted in the JTextPane.
	 * @param e - The document event.
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {
		
		docEvent = e;
		
		String typedChar = "";
		
		try {
			typedChar = e.getDocument().getText(e.getOffset(), 1);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		
		int ascii = (int)typedChar.toCharArray()[0];
		
		if((ascii >= 64 && ascii <= 90) || (ascii >= 97 && ascii <= 122)) {
			if(wordStart == -1) {
				wordStart = e.getOffset();
				wordEnd = -1;
			}
		}
		else {
			
			if(wordStart == -1) {
				wordStart = dot;
			}
			
			if(wordEnd == -1) {
				wordEnd = e.getOffset();
			}
			
			String word = "";
			
			try {
				word = e.getDocument().getText(wordStart, wordEnd - wordStart + 1);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
			
			word = word.toLowerCase().trim();
			word = word.replaceAll("[\\W]|_", "");
			wordEnd = wordStart + word.length() - 1;
			
			if(highlightWhat.equals("underline")) {
				if(!dictionary.isWord(word) && !word.equals("")) {
					highlightManager.highlight(wordStart, wordEnd - wordStart + 1);
				}
			}
			
			if(highlightWhat.equals("word")) {
				if(dictionary.isWord(word)) {
					int offset = wordStart;
					int length = (wordEnd - wordStart + 1);
					paintWord(offset, length);
				}
			}
			
			word = "";
			wordStart = -1;
			wordEnd = -1;
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
	}
	
	/**
	 * Method to keep the position of the caret updated.
	 * @param e - The caret event.
	 */
	@Override
	public void caretUpdate(CaretEvent e) {
		dot = e.getDot();
	}
	
	/**
	 * Gets the word at the caret's position.
	 * @return String - The word at the caret's position.
	 */
	public String getWordAtCaret() {
		return wordAtCaret;
	}
	
	/**
	 * Gets the dictionary.
	 * @return Dictionary - The Dictionary containing the words to check against.
	 */
	public Dictionary getDictionary() {
		return dictionary;
	}
}