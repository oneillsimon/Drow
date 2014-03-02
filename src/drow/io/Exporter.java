package drow.io;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat.Field;
import java.util.Enumeration;

import javax.swing.JTextPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.*;
import javax.swing.text.html.CSS;

import sl.docx.DocxEditorKit;

import drow.styles.DrowStyles;
import drow.view.DocumentView;

public class Exporter {
	
	private DocumentView docView;
	private JTextPane textPane;
	private StyledDocument styledDocument;
	
	public Exporter(DocumentView docView) {
		this.docView = docView;
		this.textPane = docView.getDrowDocument().getTextPane();
		this.styledDocument = textPane.getStyledDocument();
	}
	
	public void exportFile(String fileName, FileFilter fileFilter) {
		
		DrowFileFilter dFilter = (DrowFileFilter)fileFilter;
		fileName += dFilter.getFullExtension();
		
		if(fileFilter.equals(Filters.DOC)) {
			
		}
		
		if(fileFilter.equals(Filters.DOCX)) {
			asDocx(fileName);
		}

		if(fileFilter.equals(Filters.RTF)) {
	
		}
		
		if(fileFilter.equals(Filters.TXT)) {
			asTxt(fileName);
		}
		
		docView.setCurrentFileName(fileName);
		docView.setTitle(fileName);
		docView.setChanged(false);
		docView.getDrowGui().getActionSave().setEnabled(false);
	}

	private void asDoc(String fileName) {

	}

	private void asDocx(String fileName) {
		textPane.setEditorKit(new DocxEditorKit());
		
		try {
			textPane.getEditorKit().write(new FileOutputStream(fileName), styledDocument, 0, styledDocument.getLength());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	private void asTxt(String fileName) {
		try {
			FileWriter writer = new FileWriter(fileName);
			docView.getDrowDocument().getTextPane().write(writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void asRtf(String fileName) {

	}
}
