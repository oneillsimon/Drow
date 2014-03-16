package drow.spellchecker;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

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
	private ArrayList<String> words;
	private ArrayList<Integer> wordOffsets;
	private String word;
	private String wordAtCaret;
	private boolean wordFinished;
	
	public MyDictionaryListener(JTextPane textPane) {
		this.textPane = textPane;
		this.textPane.addCaretListener(this);
		
		try {
			dictionary = new Dictionary();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		highlightManager = new DrowHighlightManager(textPane, Color.red);
		words = new ArrayList<String>();
		wordOffsets = new ArrayList<Integer>();
		word = "";
		wordAtCaret = "";
		wordFinished = false;
	}
	
	@Override
	public void changedUpdate(DocumentEvent e) {
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		determineWord(e, false);
		cleanLists();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		String wordBeingChanged = wordAtCaret;

		for(int i = 0; i < words.size(); i++) {
			if(words.get(i).equals(wordBeingChanged)) {
				words.remove(words.get(i));
				wordOffsets.remove(wordOffsets.get(i));
			}
		}
		
		determineWord(e, true);
		cleanLists();
	}

	@Override
	public void caretUpdate(CaretEvent e) {
		for(int i = 0; i < words.size(); i++) {
			if(e.getDot() >= wordOffsets.get(i) && e.getDot() <= wordOffsets.get(i) + words.get(i).length()) {
				wordAtCaret = words.get(i);
				System.out.println("Caret is at word: " + wordAtCaret);
			}
		}
	}
	
	private void determineWord(DocumentEvent e, boolean removing) {
		try {
			if(e.getDocument().getText(e.getOffset(), 1).equals(" ")) {
				word = word.toLowerCase();
				word = word.trim();
				
				if(!dictionary.isWord(word) && !word.equals(" ")) {
					highlightManager.highlight(e.getOffset() - word.length(), word.length());
				}
				words.add(word);
				wordOffsets.add(e.getOffset() - word.length());
				wordFinished = true;
				word = "";
				
			} else {
				
				if (!removing) {
					if (!wordFinished) {
						word += e.getDocument().getText(e.getOffset(), 1);
					} else {
						word = "";
						word += e.getDocument().getText(e.getOffset(), 1);
						wordFinished = false;
					}
				} else {
					if (!wordFinished) {
						word = word.substring(0, word.length() - 1);
					} else {
						word = "";
						word = word.substring(0, word.length() - 1);
						wordFinished = false;
					}
				}
			}
			
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}
	
	private void cleanLists() {
		for(int i = 0; i < words.size(); i++) {
			if(words.get(i).equals("")) {
				words.remove(words.get(i));
				wordOffsets.remove(wordOffsets.get(i));
			}
		}
	}
	
	public String getWordAtCaret() {
		return wordAtCaret;
	}
}
