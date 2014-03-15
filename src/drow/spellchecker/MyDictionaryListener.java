package drow.spellchecker;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import drow.highlighter.DrowHighlightManager;

public class MyDictionaryListener implements DocumentListener {

	private JTextPane textPane;
	private Dictionary dictionary;
	private DrowHighlightManager highlightManager;
	private String word = "";
	boolean wordFinished = false;
	
	public MyDictionaryListener(JTextPane textPane) {
		this.textPane = textPane;
		
		try {
			dictionary = new Dictionary();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		highlightManager = new DrowHighlightManager(textPane, Color.red);
	}
	
	@Override
	public void changedUpdate(DocumentEvent e) {
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		
		try {
			
			if(e.getDocument().getText(e.getOffset(), 1).equals(" ")) {
				word = word.toLowerCase();
				word = word.trim();
				
				if(!dictionary.isWord(word) && !word.equals(" ")) {
					highlightManager.highlight(e.getOffset() - word.length(), word.length());
				}
				wordFinished = true;
				
			} else {
				
				if(!wordFinished) {
					word += e.getDocument().getText(e.getOffset(), 1);
				} else {
					word = "";
					word += e.getDocument().getText(e.getOffset(), 1);
					wordFinished = false;
				}
			}
			
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
	}

}
