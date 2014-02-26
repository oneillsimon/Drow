package drow.document;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import drow.view.DocumentView;

public class DrowDocument extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private String title;
	private ArrayList<DrowParagraph> paragraphs = new ArrayList<DrowParagraph>();
	
	private JTextPane textPane;
	
	public DrowDocument(JPanel view) {
		textPane = new JTextPane();
		
		view.add(textPane);
	}

	public JTextPane getTextPane() {
		
		return textPane;
	}

	public void setTextPane(JTextPane textPane) {
		this.textPane = textPane;
	}
}
