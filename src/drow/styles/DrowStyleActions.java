package drow.styles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.text.StyledDocument;

import drow.view.DocumentView;

public class DrowStyleActions {
	
	private static DocumentView docView;
	private static StyledDocument styledDocument;
	
	public static Action bold;
	
	public DrowStyleActions(DocumentView docView) {
		this.docView = docView;
		this.styledDocument = docView.getDrowDocument().getStyledDocument();
		
		bold = new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				styledDocument.setCharacterAttributes(DrowStyleActions.docView.getDrowDocument().getDot(),
													  DrowStyleActions.docView.getDrowDocument().getMark() - DrowStyleActions.docView.getDrowDocument().getDot(),
													  DrowStyles.applyStyleItalic(),
													  false);
			}
		};
	}
}
