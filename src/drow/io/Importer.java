package drow.io;

import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

import sl.docx.DocxEditorKit;
import drow.document.PageCollection;
import drow.view.DocumentView;

public class Importer {
	
	private DocumentView docView;
	
	public Importer(DocumentView docView) {
		this.docView = docView;
	}
	
	public void importFile(String filePath, FileFilter fileFilter) {
		
		String[] split = filePath.split("\\.");
		DrowFileFilter dFilter = Filters.getFilterFromString(split[split.length - 1]);
		
		if(dFilter.equals(Filters.DOC)) {
			asDoc(filePath);
		}
		
		if(dFilter.equals(Filters.DOCX)) {
			asDocx(filePath);
		}

		if(dFilter.equals(Filters.RTF)) {
			asRtf(filePath);
		}
		
		if(dFilter.equals(Filters.TXT)) {
			asTxt(filePath);
		}
		
		if(dFilter.equals(Filters.DROW)) {
			asDrow(filePath);
		}
	}

	private void asDoc(String fileName) {

	}

	private void asDocx(String filePath) {
		docView.getDrowDocument().removeAllPages();
		docView.getDrowDocument().add(docView.getDrowDocument().newPage());
		docView.getDrowDocument().getFocusedPage().setEditorKit(new DocxEditorKit());
		try {
			docView.getDrowDocument().getFocusedPage().getEditorKit().read(new FileInputStream(filePath),
																		   docView.getDrowDocument().getFocusedPage().getStyledDocument(),
																		   0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	private void asTxt(String filePath) {
		try {
			FileReader reader = new FileReader(filePath);
			docView.getDrowDocument().removeAllPages();
			docView.getDrowDocument().add(docView.getDrowDocument().newPage());
			docView.getDrowDocument().getFocusedPage().read(reader, null);
			reader.close();
		} catch (IOException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(docView, "Editor can't find the file called " + filePath);
		}
	}

	private void asRtf(String fileName) {
		RTFEditorKit kit = new RTFEditorKit();
		docView.getDrowDocument().removeAllPages();
		docView.getDrowDocument().add(docView.getDrowDocument().newPage());
		docView.getDrowDocument().getFocusedPage().setEditorKit(kit);
		
		FileInputStream in = null;		
		try {
			in = new FileInputStream(fileName);
			kit.read(in, (Document)docView.getDrowDocument().getFocusedPage().getDocument(), 0);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	private void asDrow(String fileName) {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
			try {
				PageCollection collection = (PageCollection)in.readObject();
				docView.getDrowDocument().removeAllPages();
				collection.applyToDocument(docView);
				docView.getDrowDocument().determinePageX();
				in.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}