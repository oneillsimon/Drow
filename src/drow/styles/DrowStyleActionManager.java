package drow.styles;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import drow.document.DrowDocument;
import drow.document.DrowPage;
import drow.view.DocumentView;

public class DrowStyleActionManager {
	
	private static DrowDocument document;
	private static ArrayList<DrowPage> pages;
	
	public DrowStyleActionManager(DocumentView docView) {
		DrowStyleActionManager.document = docView.getDrowDocument();
		DrowStyleActionManager.pages = docView.getDrowDocument().getPages();
	}
	
	public Action fontFamilyAction() {
		return new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				
				@SuppressWarnings("unchecked")
				JComboBox<String> comboBox = (JComboBox<String>)e.getSource();
				Object selectedItem = comboBox.getSelectedItem();
				styleDoc(DrowStyles.applyStyleFontFamily(selectedItem.toString()));
			}
		};
	}
	
	public Action fontSizeAction() {
		return new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				
				@SuppressWarnings("unchecked")
				JComboBox<String> comboBox = (JComboBox<String>)e.getSource();
				Object selectedItem = comboBox.getSelectedItem();
				styleDoc(DrowStyles.applyStyleFontSize(Integer.parseInt(selectedItem.toString())));
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
		return new AbstractAction("", new ImageIcon("res/bold.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleBold());
			}
		};
	}
	
	public Action italicAction() {
		return new AbstractAction("", new ImageIcon("res/italic.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleItalic());
			}
		};
	}
	
	public Action underlineAction() {
		return new AbstractAction("", new ImageIcon("res/underline.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleUnderline());
			}
		};
	}
	
	public Action strikeThroughAction() {
		return new AbstractAction("", new ImageIcon("res/strike.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleStrikeThrough());
			}
		};
	}
	
	public Action superScriptAction() {
		return new AbstractAction("", new ImageIcon("res/superscript.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleSuperScript());
			}
		};
	}
	
	public Action subScriptAction() {
		return new AbstractAction("", new ImageIcon("res/subscript.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleSubScript());
			}
		};
	}
	
	public Action foregroundColorAction() {
		return new AbstractAction("", new ImageIcon("res/fontcolour.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JPanel p = new JPanel();
				JColorChooser jc = new JColorChooser();
				p.add(jc);
				
				if(JOptionPane.showConfirmDialog(null, p, "Please choose a color", JOptionPane.OK_CANCEL_OPTION) == 
				   JOptionPane.OK_OPTION)
				{
					styleDoc(DrowStyles.applyStyleForegroundColor(jc.getColor()));	
				}
			}
		};
	}
	
	public Action backgroundColorAction() {
		return new AbstractAction("", new ImageIcon("res/fonthighlight.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

				JPanel p = new JPanel();
				JColorChooser jc = new JColorChooser();
				p.add(jc);
				
				if(JOptionPane.showConfirmDialog(null, p, "Please choose a color", JOptionPane.OK_CANCEL_OPTION) == 
				   JOptionPane.OK_OPTION)
				{
					styleDoc(DrowStyles.applyStyleBackgroundColor(jc.getColor()));
				}
			}
		};
	}
	
	public Action justifyLeftAction() {
		return new AbstractAction("", new ImageIcon("res/align_left.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleAttributeSet left = new SimpleAttributeSet();
				StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
				styleDoc(left);
			}
		};
	}
	
	public Action justifyCenterAction() {
		return new AbstractAction("", new ImageIcon("res/align_centre.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleAttributeSet center = new SimpleAttributeSet();
				StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
				styleDoc(center);
			}
		};
	}
	
	public Action justifyRightAction() {
		return new AbstractAction("", new ImageIcon("res/align_right.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleAttributeSet right = new SimpleAttributeSet();
				StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
				styleDoc(right);
			}
		};
	}
	
	private void styleDoc(Style style) {
		pages.get(DrowDocument.FOCUSED_PAGE_NUMBER).getStyledDocument().setCharacterAttributes(document.getLesser(),
											  document.getDiff(),
											  style,
											  false);
	}
	
	private void styleDoc(SimpleAttributeSet attribSet) {
		pages.get(DrowDocument.FOCUSED_PAGE_NUMBER).getStyledDocument().setParagraphAttributes(document.getLesser(),
											  document.getDiff(),
											  attribSet, false);
	}
}
