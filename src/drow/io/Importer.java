package drow.io;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;

import drow.view.DocumentView;

public class Importer {
	
	private DocumentView docView;
	
	public Importer(DocumentView docView) {
		this.docView = docView;
	}
	
	public void importFile(String filePath, FileFilter fileFilter) {
		
		if(fileFilter.equals(Filters.DOC)) {
			
		}
		
		if(fileFilter.equals(Filters.DOCX)) {
			asDocx(filePath);
		}

		if(fileFilter.equals(Filters.RTF)) {
	
		}
		
		if(fileFilter.equals(Filters.TXT)) {
			asTxt(filePath);
		}
		
		String[] split = filePath.split("\\\\");
		String fileName = split[split.length - 1];
		
		docView.setCurrentFileName(fileName);
		docView.setTitle(fileName);
		docView.setChanged(false);
	}

	private void asDoc(String fileName) {

	}

	private void asDocx(String filePath) {
		try {
			XWPFDocument doc = new XWPFDocument(new FileInputStream(filePath));
			XWPFWordExtractor extract = new XWPFWordExtractor(doc);
			
			docView.getDrowDocument().getTextPane().setText(extract.getText());
		} catch (FileNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void asTxt(String filePath) {
		
		try {
			FileReader reader = new FileReader(filePath);
			docView.getDrowDocument().getTextPane().read(reader, null);
			reader.close();
		} catch (IOException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(docView, "Editor can't find the file called " + filePath);
		}
	}

	private void asRtf(String fileName) {

	}

}
