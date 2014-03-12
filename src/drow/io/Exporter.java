package drow.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JTextPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.*;

import sl.docx.DocxDocument;
import sl.docx.DocxEditorKit;
import drow.view.DocumentView;

public class Exporter {
	
	private DocumentView docView;
	private JTextPane textPane;
	private DocxDocument styledDocument;
	
	public Exporter(DocumentView docView) {
		this.docView = docView;
		this.textPane = docView.getDrowDocument().getPage().getTextPane();
		this.styledDocument = docView.getDrowDocument().getPage().getStyledDocument();
	}
	
	public void exportFile(String fileName, FileFilter fileFilter) {
		DrowFileFilter dFilter = (DrowFileFilter)fileFilter;
		
		// TODO: check if extension exists before adding it
		
		String[] split = fileName.split("\\.");
		
		for(int i = 0; i < Filters.getExtensions().size(); i++) {
			if(!split[split.length - 1].equals(Filters.getExtensionString(i))) {
				fileName += dFilter.getFullExtension();
				break;
			}
		}
		
		if(fileFilter.equals(Filters.DOC)) {
			asDoc(fileName);
		}
		
		if(fileFilter.equals(Filters.DOCX)) {
			asDocx(fileName);
		}

		if(fileFilter.equals(Filters.RTF)) {
			asRtf(fileName);
		}
		
		if(fileFilter.equals(Filters.TXT)) {
			asTxt(fileName);
		}
		
		if(fileFilter.equals(Filters.DROW)) {
			asDrow(fileName);
		}

		docView.setCurrentFileName(fileName);
		docView.setTitle(fileName);
		docView.setChanged(false);
		docView.setChanged(!docView.getChanged());
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
		textPane.setStyledDocument(styledDocument);
	}

	private void asTxt(String fileName) {
		try {
			FileWriter writer = new FileWriter(fileName);
			docView.getDrowDocument().getPage().getTextPane().write(writer);
			writer.close();

			docView.setCurrentFileName(fileName);
			//docView.setTitle(fileName);
			docView.setChanged(false);
			//docView.getDrowGui().getActionSave().setEnabled(false);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void asRtf(String fileName) {

	}
	
	private void asDrow(String fileName) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(styledDocument);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
