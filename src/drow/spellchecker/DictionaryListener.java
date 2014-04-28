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
 * @author Lee Mc Donald
 * @author Simon O'Neill
 */
public class DictionaryListener implements DocumentListener, CaretListener {

	private Dictionary dictionary;
	private DrowHighlightManager highlightManager;
	private DrowStyleActionManager actionManager;
	private String wordAtCaret;
	private String highlightWhat;
	private JTextPane textPane;
	
	private int dot;
	
	int wordStart = -1;
	int wordEnd = -1;
	
	DocumentEvent docEvent;
	
	public DictionaryListener(JTextPane textPane, String fileName) {
		this(textPane, fileName, "underline", Color.red);
	}
	
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
	
	void paintWord(int offset, int length) {
	    class OneShotTask implements Runnable {
	        int offset;
	        int length;
	        
	        private OneShotTask(int o, int l) {
	        	offset = o;
	        	length = l;
	        	}
	        public void run() {
	        	actionManager.styleText(textPane, DrowStyles.applyStyleForegroundColor(highlightManager.getColor()), offset, length);
	        }
	    }
	    Thread t = new Thread(new OneShotTask(offset, length));
	    t.start();
	}
	
	@Override
	public void changedUpdate(DocumentEvent e) {
	}

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
	
	@Override
	public void caretUpdate(CaretEvent e) {
		dot = e.getDot();
	}
	
	public String getWordAtCaret() {
		return wordAtCaret;
	}
	
	public Dictionary getDictionary() {
		return dictionary;
	}
}
