package drow.styles;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;

import drow.document.DrowDocument;
import drow.view.DocumentView;

public class DrowStyleActionManager {
	
	private static StyledDocument styledDocument;
	private static DrowDocument document;
	
	public DrowStyleActionManager(DocumentView docView) {
		DrowStyleActionManager.styledDocument = docView.getDrowDocument().getPage().getStyledDocument();
		DrowStyleActionManager.document = docView.getDrowDocument();
	}
	
	public Action fontFamilyAction() {
		return new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> comboBox = (JComboBox<String>)e.getSource();
				Object selectedItem = comboBox.getSelectedItem();
				styledDocument.setCharacterAttributes(document.getLesser(),
													  document.getDiff(),
													  DrowStyles.applyStyleFontFamily(selectedItem.toString()),
													  false);
			}
		};
	}
	
	public Action fontSizeAction() {
		return new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> comboBox = (JComboBox<String>)e.getSource();
				Object selectedItem = comboBox.getSelectedItem();
				styledDocument.setCharacterAttributes(document.getLesser(),
													  document.getDiff(),
													  DrowStyles.applyStyleFontSize(Integer.parseInt(selectedItem.toString())),
													  false);
			}
		};
	}
	
	public Action leftIndentAction() {
		return new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleLeftIndent(1f));
			}
		};
	}
	
	public Action rightIndentAction() {
		return new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleRightIndent(1f));
			}
		};
	}
	
	public Action firstLineIndentAction() {
		return new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleFirstLineIndent(1f));
			}
		};
	}
	
	public Action spaceAboveAction() {
		return new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleSpaceAbove(1f));
			}
		};
	}
	
	public Action spaceBelowAction() {
		return new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleSpaceBelow(1f));
			}
		};
	}
	
	public Action boldAction() {
		return new AbstractAction("B") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleBold());
			}
		};
	}
	
	public Action italicAction() {
		return new AbstractAction("I") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleItalic());
			}
		};
	}
	
	public Action underlineAction() {
		return new AbstractAction("U") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleUnderline());
			}
		};
	}
	
	public Action strikeThroughAction() {
		return new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleStrikeThrough());
			}
		};
	}
	
	public Action superScriptAction() {
		return new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleSuperScript());
			}
		};
	}
	
	public Action subScriptAction() {
		return new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleSubScript());
			}
		};
	}
	
	public Action foregroundColorAction() {
		return new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleForegroundColor(Color.black));
			}
		};
	}
	
	public Action backgroundColorAction() {
		return new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleBackgroundColor(Color.black));
			}
		};
	}
	
	private void styleDoc(Style style) {
		styledDocument.setCharacterAttributes(document.getLesser(),
											  document.getDiff(),
											  style,
											  false);
	}
}
