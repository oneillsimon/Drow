package drow.spellchecker;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import drow.highlighter.DrowHighlightManager;

public class MyDictionaryListener implements DocumentListener, CaretListener {

	private JTextPane textPane;
	private Dictionary dictionary;
	private DrowHighlightManager highlightManager;
	private String wordAtCaret;
	
	private int dot;
	private int mark;
	
	int wordStart = -1;
	int wordEnd = -1;
	
	public MyDictionaryListener(JTextPane textPane) {
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
				wordStart = e.getOffset();
			}
			
			if(wordEnd == -1) {
				wordEnd = e.getOffset();
			}
			
			System.out.println("wordStart: " + wordStart + ", wordEnd: " + wordEnd);
			
			String word = "";
			
			try {
				word = e.getDocument().getText(wordStart, wordEnd - wordStart + 1);
				System.out.println("word: " + word);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
				System.out.println(e1.getMessage());
			}
			
			word = word.toLowerCase().trim();
			word = word.replaceAll("[\\W]|_", "");
			System.out.println("word len is " + word.length());
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
		/*String wordBeingChanged = wordAtCaret;

		for(int i = 0; i < words.size(); i++) {
			if(words.get(i).equals(wordBeingChanged)) {
				words.remove(words.get(i));
				wordOffsets.remove(wordOffsets.get(i));
				System.out.println("Removed: " + wordBeingChanged);
			}
		}*/
		
		//determineWord(e, true);
		//cleanLists();
	}

	@Override
	public void caretUpdate(CaretEvent e) {
		
		dot = e.getDot();
		mark = e.getMark();
	}
	
	public String getWordAtCaret() {
		return wordAtCaret;
	}
}
