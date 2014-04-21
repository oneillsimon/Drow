package drow.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.*;

import sl.docx.DocxDocument;
import sl.docx.DocxEditorKit;
import drow.document.PageCollection;
import drow.view.DocumentView;

public class Exporter {
	
	private DocumentView docView;
	
	public Exporter(DocumentView docView) {
		this.docView = docView;
	}
	
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
		
		if(dFilter.equals(Filters.DOC)) {
			asDoc(fileName);
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

		docView.setCurrentFileName(fileName);
		docView.setTitle(fileName);
		docView.setChanged(false);
		docView.setChanged(!docView.getChanged());
	}

	private void asDoc(String fileName) {

	}

	private void asDocx(String fileName) {
		docView.getDrowDocument().getCombinedPage().setEditorKit(new DocxEditorKit());
		
		try {
			FileOutputStream writer = new FileOutputStream(fileName);
			docView.getDrowDocument().getCombinedPage().getEditorKit().write(writer,
																			 docView.getDrowDocument().getCombinedPage().getDocument(),
																			 0,
																			 docView.getDrowDocument().getCombinedPage().getDocument().getLength());

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

	private void asTxt(String fileName) {
		try {
			FileWriter writer = new FileWriter(fileName);
			docView.getDrowDocument().getCombinedPage().write(writer);
			writer.close();

			docView.setCurrentFileName(fileName);
			docView.setChanged(false);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void asRtf(String fileName) {

	}
	
	private void asDrow(String fileName) {
		try {
			PageCollection collection = new PageCollection();
			collection.setPages(docView.getDrowDocument());
			
			docView.getDrowDocument().removeListeners();
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(collection);
			out.close();
			
			docView.getDrowDocument().addListeners();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
