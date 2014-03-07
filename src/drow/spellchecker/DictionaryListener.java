package drow.spellchecker;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JTextPane;

import drow.highlighter.DrowHighlightManager;

// This class is not perfect but it is working

public class DictionaryListener implements KeyListener {
	
	JTextPane typingArea;
	Dictionary d;
	
    public DrowHighlightManager highlightManager = new DrowHighlightManager(Color.red);
	
	public DictionaryListener(JTextPane t) {
		this.typingArea = t;
		
		try {
			this.d = new Dictionary();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		// TODO Auto-generated method stub
		if (Character.toString(e.getKeyChar()).matches("\\W")) { 
			// find the last non word char and split from there 
        	String temp = typingArea.getText();
        	String[] parts = temp.split(" ");
        	
        	// String.valueOf(typingArea.getText().matches("\\W"));
        	String lastWord = parts[parts.length - 1];
        	lastWord = lastWord.toLowerCase();
        	
    		System.out.println(" To lower case: " + lastWord);
        	System.out.println("\n word is: " + d.isWord(lastWord)); // "sentence"
        	
        	if(!d.isWord(lastWord)) {
        		highlightManager.highlight(lastWord);
    			System.out.println(" this is not a word: " + lastWord);
        	}
        	
        	if (lastWord.matches("\\W")) {
        		// the last word is not a word
        	}
			if (d.isWord(lastWord)) {
				System.out.println("\n WORDDDD Exists"); // "sentence"
			}
		}
	}
}
