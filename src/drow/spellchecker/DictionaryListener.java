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

public class DictionaryListener implements DocumentListener, CaretListener {

	private JTextPane textPane;
	private Dictionary dictionary;
	private DrowHighlightManager highlightManager;
	private String wordAtCaret;
	
	private int dot;
	
	int wordStart = -1;
	int wordEnd = -1;
	
	DocumentEvent docEvent;
	
	public DictionaryListener(JTextPane textPane) {
		this.textPane = textPane;
		this.textPane.addCaretListener(this);
		
		try {
			dictionary = new Dictionary();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		highlightManager = new DrowHighlightManager(textPane, Color.red);
		wordAtCaret = "";
	}
	
	@Override
	public void changedUpdate(DocumentEvent e) {
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		//determineWord(e, false);
		//cleanLists();
		
		docEvent = e;
		
		String typedChar = "";
		
		try {
			typedChar = e.getDocument().getText(e.getOffset(), 1);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		
		int ascii = (int)typedChar.toCharArray()[0];
		
		if((ascii >= 64 && ascii <= 90) || (ascii >= 97 && ascii <= 122)) {
			System.out.println("ascii is " + ascii);
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
			
			if(!dictionary.isWord(word) && !word.equals("")) {
				highlightManager.highlight(wordStart, wordEnd - wordStart + 1);
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
}
