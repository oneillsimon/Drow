package drow.styles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.text.StyledDocument;

import drow.document.DrowDocument;
import drow.view.DocumentView;

public class DrowStyleActions {
	
	private static StyledDocument styledDocument;
	private static DrowDocument document;
	
	public DrowStyleActions(DocumentView docView) {
		DrowStyleActions.styledDocument = docView.getDrowDocument().getPage().getStyledDocument();
		DrowStyleActions.document = docView.getDrowDocument();
	}
	
	public static Action boldAction() {
		return new AbstractAction("B") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styledDocument.setCharacterAttributes(document.getLesser(),
													  document.getDiff(),
													  DrowStyles.applyStyleBold(),
													  false);
			}
		};
	}
	
	public static Action italicAction() {
		return new AbstractAction("I") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styledDocument.setCharacterAttributes(document.getLesser(),
						  							  document.getDiff(),
						  							  DrowStyles.applyStyleItalic(),
						  							  false);
			}
		};
	}
	
	public static Action underlineAction() {
		return new AbstractAction("U") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styledDocument.setCharacterAttributes(document.getLesser(),
						  							  document.getDiff(),
						  							  DrowStyles.applyStyleUnderline(),
						  							  false);
			}
		};
	}
	
	public static ActionListener setFontFamily(String s) {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}
}
