package drow.io;

import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;

import sl.docx.DocxEditorKit;
import drow.document.PageCollection;
import drow.view.DocumentView;

/**
 * <h1>Importer</h1>
 * Class for handling the importing of files.
 * <p>
 * @author Simon O'Neill
 * <p>
 */
public class Importer {
	
	/** The view containing the document. */
	private DocumentView documentView;
	
	/**
	 * <h1>Constructor</h1>
	 * @param documentView - The view containing the document.
	 */
	public Importer(DocumentView documentView) {
		this.documentView = documentView;
	}
	
	/**
	 * Imports the file.
	 * The file filter will dictate how the file is imported.
	 * @param fileName - The name of the file.
	 * @param fileFilter - The filter of the file which will determine the method of importing.
	 */
	public void importFile(String fileName, FileFilter fileFilter) {
		
		String[] split = fileName.split("\\.");
		DrowFileFilter dFilter = Filters.getFilterFromString(split[split.length - 1]);
		
		if(dFilter.equals(Filters.DOCX)) {
			asDocx(fileName);
		}

		if(dFilter.equals(Filters.RTF)) {
			asRtf(fileName);
		}
		
		if(dFilter.equals(Filters.TXT)) {
			asTxt(fileName);
		}
		
		if(dFilter.equals(Filters.DROW)) {
			asDrow(fileName);
		}
		
		split = fileName.split("\\\\");
		documentView.setTitle(split[split.length - 1]);
	}

	/**
	 * Imports the file as a ".docx" file.
	 * @param fileName - The name of the file.
	 */
	private void asDocx(String fileName) {
		documentView.getDrowDocument().removeAllPages();
		documentView.getDrowDocument().add(documentView.getDrowDocument().newPage());
		documentView.getDrowDocument().getFocusedPage().setEditorKit(new DocxEditorKit());
		try {
			documentView.getDrowDocument().getFocusedPage().getEditorKit().read(new FileInputStream(fileName),
																		   documentView.getDrowDocument().getFocusedPage().getStyledDocument(),
																		   0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Imports the file as a plain text file.
	 * @param fileName - The name of the file.
	 */
	private void asTxt(String fileName) {
		try {
			FileReader reader = new FileReader(fileName);
			documentView.getDrowDocument().removeAllPages();
			documentView.getDrowDocument().add(documentView.getDrowDocument().newPage());
			documentView.getDrowDocument().getFocusedPage().read(reader, null);
			reader.close();
		} catch (IOException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(documentView, "Editor can't find the file called " + fileName);
		}
	}

	/**
	 * Imports the file as a Rich Text Format document.
	 * @param fileName - The name of the file.
	 */
	private void asRtf(String fileName) {
		RTFEditorKit kit = new RTFEditorKit();
		documentView.getDrowDocument().removeAllPages();
		documentView.getDrowDocument().add(documentView.getDrowDocument().newPage());
		documentView.getDrowDocument().getFocusedPage().setEditorKit(kit);
		
		FileInputStream in = null;		
		try {
			in = new FileInputStream(fileName);
			kit.read(in, (Document)documentView.getDrowDocument().getFocusedPage().getDocument(), 0);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Imports the file as a .drow file.
	 * @param fileName - The name of the file.
	 */
	private void asDrow(String fileName) {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
			try {
				PageCollection collection = (PageCollection)in.readObject();
				documentView.getDrowDocument().removeAllPages();
				collection.applyToDocument(documentView);
				documentView.getDrowDocument().determinePageX();
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