package drow.io;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import drow.view.DocumentView;

public class Exporter {
	
	private DocumentView docView;
	
	public Exporter(DocumentView docView) {
		this.docView = docView;
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
	}

	private void asDoc(String fileName) {

	}

	private void asDocx(String fileName) {
		XWPFDocument document = new XWPFDocument();
		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();
		
		run.setText(docView.getDrowDocument().getTextPane().getText());
		
		try {
			FileOutputStream output = new FileOutputStream(fileName);
			document.write(output);
			output.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void asTxt(String fileName) {

		try {
			FileWriter writer = new FileWriter(fileName);
			docView.getDrowDocument().getTextPane().write(writer);
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
}
