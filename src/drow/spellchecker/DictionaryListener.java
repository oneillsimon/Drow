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
	private static final int BACK_KEY = 8;
	private static final int MAX_SIZE_WORD = 20; // only pull 20 chars off text pane to save on splitting the entire string
	private static int CURRENT_MAX_WORD = 0; // starts at zero
	
    public DrowHighlightManager highlightManager;
	
	public DictionaryListener(JTextPane t) {
		this.typingArea = t;
		highlightManager = new DrowHighlightManager(typingArea, Color.red);

		try {
			this.d = new Dictionary();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
	    if(e.getKeyCode() == e.VK_BACK_SPACE) {  
	    	// do nothing
	    } else {
			if (Character.toString(e.getKeyChar()).matches("\\W")) { 
				Thread runner = new Thread() {
		
					public void run() {				
						// find the last non word char and split from there 
			        	String temp = typingArea.getText();
			        	CURRENT_MAX_WORD = temp.length() < MAX_SIZE_WORD ? temp.length() : CURRENT_MAX_WORD;

			        	temp = temp.substring(temp.length() - CURRENT_MAX_WORD);
			        	String[] parts = temp.split(" ");
			        	
			        	String lastWord = parts[parts.length - 1];
			        	lastWord = lastWord.toLowerCase();
			        	
			        	if(!d.isWord(lastWord)) {
			        		//System.out.println(typingArea.getText().lastIndexOf(lastWord));
			        		System.out.println(lastWord.length());
			        		//highlightManager.highlight(typingArea.getText().lastIndexOf(lastWord), lastWord.length());
			        	}
					}
				};
				runner.start();
			} 
	    }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
