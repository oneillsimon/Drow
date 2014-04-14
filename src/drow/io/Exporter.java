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
import drow.document.PageCollection;
import drow.view.DocumentView;

public class Exporter {
	
	private DocumentView docView;
	private JTextPane textPane = new JTextPane();
	private DocxDocument styledDocument;
	
	public Exporter(DocumentView docView) {
		this.docView = docView;
		//this.textPane = docView.getDrowDocument().getCombinedPage();
		this.styledDocument = docView.getDrowDocument().getCombinedPage().getStyledDocument();
		textPane.setText("Hello I am hardcoded");
	}
	
	public void exportFile(String fileName, FileFilter fileFilter) {
		
		DrowFileFilter dFilter;
		
		try {
			dFilter = (DrowFileFilter)fileFilter;
		} catch(Exception e) {
			dFilter = Filters.DROW;
		}
		
		// TODO: check if extension exists before adding it
		
		String[] split = fileName.split("\\.");
		
		for(int i = 0; i < Filters.getExtensions().size(); i++) {
			if(!split[split.length - 1].equals(Filters.getExtensionString(i))) {
				fileName += dFilter.getFullExtension();
				break;
			}
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
		//docView.getDrowDocument().getFocusedPage().setEditorKit(new DocxEditorKit());
		textPane.setEditorKit(new DocxEditorKit());
		try {
			System.out.println(docView.getDrowDocument().getCombinedPage().getText(0, docView.getDrowDocument().getCombinedPage().getDocument().getLength()));
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			FileOutputStream writer = new FileOutputStream(fileName);
            textPane.getEditorKit().write(writer,
            									textPane.getDocument(),
            									0,
            									textPane.getDocument().getLength());

            writer.flush();
            writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		docView.getDrowDocument().getCombinedPage().setStyledDocument(styledDocument);
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
