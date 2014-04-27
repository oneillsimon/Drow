/**
 * <h1>DrowDocumentManager</h1>
 * <p>
 * This class manages the importing and exporting of documents.
 */

package drow.document;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

import drow.io.Exporter;
import drow.io.Importer;
import drow.view.DocumentView;

public class DrowDocumentManager extends JFrame {

	private static final long serialVersionUID = 1L;

	/** Importer for importing files. */
	private Importer importer;
	
	/** Exporter for exporting files. */
	private Exporter exporter;

	/**
	 * <h1>Constructor</h1>
	 * @param documentView - The view containing the DrowDocument.
	 */
	public DrowDocumentManager(DocumentView documentView) {
		this.importer = new Importer(documentView);
		this.exporter = new Exporter(documentView);
	}

	/**
	 * Saves a file, calling Exporter.exportFile().
	 * @param fileName - The name of the file to save.
	 * @param fileChooser - The fileChooser which will tell us where to save the file.
	 */
	public void saveFile(String fileName, JFileChooser fileChooser) {
		exporter.exportFile(fileName, fileChooser);
	}

	/**
	 * Opens a file, calling Importer.import().
	 * @param fileName - The name of the file to open.
	 * @param filter - The extension of the file being opened.
	 */
	public void openFile(String fileName, FileFilter filter) {
		importer.importFile(fileName, filter);
	}
}