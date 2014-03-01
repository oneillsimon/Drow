package drow.io;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.filechooser.FileFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import drow.styles.DrowStyles;
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
		String s = "";
		
		for(int i = 0; i < docView.getDrowDocument().getStyledDocument().getLength(); i++) {
			try {
				s += docView.getDrowDocument().getStyledDocument().getText(i, 1);
				
				boolean b = StyleConstants.Bold instanceof AttributeSet.CharacterAttribute;
				run.setBold(b);
				b = false;
				
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		run.setText(s);
		
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
			docView.setTitle(fileName);
			docView.setChanged(false);
			docView.getDrowGui().getActionSave().setEnabled(false);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void asRtf(String fileName) {

	}
}
