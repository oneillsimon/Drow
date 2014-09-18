package drow.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

import sl.docx.DocxEditorKit;
import drow.document.PageCollection;
import drow.view.DocumentView;

/**
 * <h1>Exporter</h1>
 * Class for handling the exporting of files.
 * <p>
 * @author Simon O'Neill
 * <p>
 */
public class Exporter {
	
	/** The view containing the document. */
	private DocumentView documentView;
	
	/**
	 * <h1>Constructor</h1>
	 * @param documentView - The view containing the document.
	 */
	public Exporter(DocumentView documentView) {
		this.documentView = documentView;
	}
	
	/**
	 * Exports the file, using the JFileChooser to determine the save location and file extension.
	 * The file extensions will dictate how the file is exported.
	 * @param fileName - The name of the file.
	 * @param fileChooser - The JFileChooser to determine the save location and file extension.
	 */
	public void exportFile(String fileName, JFileChooser fileChooser) {
		
		DrowFileFilter dFilter;
		
		try {
			dFilter = (DrowFileFilter)fileChooser.getFileFilter();
		} catch(Exception e) {
			dFilter = Filters.DROW;
		}
		
		String[] split = fileName.split("\\.");
		
		if(!Filters.isStringFileFilter(split[split.length - 1])) {
			fileName += dFilter.getFullExtension();
		} else {
			dFilter = Filters.getFilterFromString(split[split.length - 1]);
		}
		
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
	 * Exports the file as a ".docx" file.
	 * @param fileName - The name of the file.
	 */
	private void asDocx(String fileName) {
		documentView.getDrowDocument().getCombinedPage().setEditorKit(new DocxEditorKit());
		
		try {
			FileOutputStream writer = new FileOutputStream(fileName);
			documentView.getDrowDocument().getCombinedPage().getEditorKit().write(writer,
																			 documentView.getDrowDocument().getCombinedPage().getDocument(),
																			 0,
																			 documentView.getDrowDocument().getCombinedPage().getDocument().getLength());

            writer.flush();
            writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Exports the file as a plain text file.
	 * @param fileName - The name of the file.
	 */
	private void asTxt(String fileName) {
		try {
			FileWriter writer = new FileWriter(fileName);
			documentView.getDrowDocument().getCombinedPage().write(writer);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Exports the file in the Rich Text Format.
	 * @param fileName - The name of the file.
	 */
	private void asRtf(String fileName) {
		StyledDocument doc = documentView.getDrowDocument().getCombinedPage().getStyledDocument();
		RTFEditorKit kit = new RTFEditorKit();
		
		try {
			FileOutputStream out = new FileOutputStream(fileName);
			kit.write(out, doc, 0, doc.getLength());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Exports the file as a ".drow" file.
	 * @param fileName - The name of the file.
	 */
	private void asDrow(String fileName) {
		try {
			PageCollection collection = new PageCollection();
			collection.setPages(documentView.getDrowDocument());
			
			documentView.getDrowDocument().removeListeners();
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(collection);
			out.close();
			
			documentView.getDrowDocument().addWordListeners();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}