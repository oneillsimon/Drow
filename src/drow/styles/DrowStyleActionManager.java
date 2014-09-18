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
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import drow.document.DrowDocument;
import drow.document.DrowPage;
import drow.view.DocumentView;

/**
 * <h1>DrowStyleActionManager</h1>
 * This class manages the actions used to style text in the document.
 * <p>
 * @author Simon O'Neill
 * <p>
 */
public class DrowStyleActionManager {
	
	/** The document to style. */
	private static DrowDocument document;
	
	/** The list of pages in the document. */
	private static ArrayList<DrowPage> pages;
	
	/**
	 * <h1>Constructor</h1>
	 * @param documentView - The view containing the document.
	 */
	public DrowStyleActionManager(DocumentView documentView) {
		
		if(documentView != null) {
			DrowStyleActionManager.document = documentView.getDrowDocument();
			DrowStyleActionManager.pages = documentView.getDrowDocument().getPages();
		}
	}
	
	/** Action for changing the font family. */
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
	
	/** Action for changing the font size. */
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
	
	/** Action for changing the left indent of the text. */
	public Action leftIndentAction() {
		return new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleLeftIndent(1f));
			}
		};
	}
	
	/** Action for changing the right indent of the text. */
	public Action rightIndentAction() {
		return new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleRightIndent(1f));
			}
		};
	}
	
	/** Action for changing the first line indent of the text. */
	public Action firstLineIndentAction() {
		return new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleFirstLineIndent(1f));
			}
		};
	}
	
	/** Action for changing the spacing of text above some text. */
	public Action spaceAboveAction() {
		return new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleSpaceAbove(1f));
			}
		};
	}
	
	/** Action for changing the spacing of text below some text. */
	public Action spaceBelowAction() {
		return new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleSpaceBelow(1f));
			}
		};
	}
	
	/** Action for toggling bold style. */
	public Action boldAction() {
		return new AbstractAction("", new ImageIcon("res/buttonIcons/bold.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleBold());
			}
		};
	}
	
	/** Action for toggling italic style. */
	public Action italicAction() {
		return new AbstractAction("", new ImageIcon("res/buttonIcons/italic.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleItalic());
			}
		};
	}
	
	/** Action for toggling underline style. */
	public Action underlineAction() {
		return new AbstractAction("", new ImageIcon("res/buttonIcons/underline.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleUnderline());
			}
		};
	}
	
	/** Action for toggling strike through style. */
	public Action strikeThroughAction() {
		return new AbstractAction("", new ImageIcon("res/buttonIcons/strike.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleStrikeThrough());
			}
		};
	}
	
	/** Action for toggling superscript style. */
	public Action superScriptAction() {
		return new AbstractAction("", new ImageIcon("res/buttonIcons/superscript.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleSuperScript());
			}
		};
	}
	
	/** Action for toggling subscript style. */
	public Action subScriptAction() {
		return new AbstractAction("", new ImageIcon("res/buttonIcons/subscript.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				styleDoc(DrowStyles.applyStyleSubScript());
			}
		};
	}
	
	/** Action for changing font colour. */
	public Action foregroundColorAction() {
		return new AbstractAction("", new ImageIcon("res/buttonIcons/fontcolour.png")) {
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
	
	/** Action for changing font highlight colour. */
	public Action backgroundColorAction() {
		return new AbstractAction("", new ImageIcon("res/buttonIcons/fonthighlight.png")) {
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
	
	/** Action for justifying text left. */
	public Action justifyLeftAction() {
		return new AbstractAction("", new ImageIcon("res/buttonIcons/align_left.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleAttributeSet left = new SimpleAttributeSet();
				StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
				styleDoc(left);
			}
		};
	}
	
	/** Action for justifying text centre. */
	public Action justifyCenterAction() {
		return new AbstractAction("", new ImageIcon("res/buttonIcons/align_centre.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleAttributeSet center = new SimpleAttributeSet();
				StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
				styleDoc(center);
			}
		};
	}
	
	/** Action for justifying text right. */
	public Action justifyRightAction() {
		return new AbstractAction("", new ImageIcon("res/buttonIcons/align_right.png")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleAttributeSet right = new SimpleAttributeSet();
				StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
				styleDoc(right);
			}
		};
	}
	
	/** Method for adding a bullet point to the document, inserts the Unicode char "2022". */
	public Action addBulletPoint() {
		return new AbstractAction("\u2022") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					pages.get(DrowDocument.FOCUSED_PAGE_NUMBER).getStyledDocument().insertString(pages.get(DrowDocument.FOCUSED_PAGE_NUMBER).getDot(),
																								 "\t\u2022\n",
																								 new SimpleAttributeSet());
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}
		};
	}
	
	/** Method for applying style to a particular area of a JTextPane. */
	public void styleText(JTextPane textPane, Style style, int offset, int length) {
		textPane.getStyledDocument().setCharacterAttributes(offset, length, style, false);
	}
	
	/** Convenience method for styling a document. */
	private void styleDoc(Style style) {
		pages.get(DrowDocument.FOCUSED_PAGE_NUMBER).getStyledDocument().setCharacterAttributes(document.getLesser(),
											  document.getDiff(),
											  style,
											  false);
	}
	
	/** Convenience method for styling a document. */
	private void styleDoc(SimpleAttributeSet attribSet) {
		pages.get(DrowDocument.FOCUSED_PAGE_NUMBER).getStyledDocument().setParagraphAttributes(document.getLesser(),
											  document.getDiff(),
											  attribSet, false);
	}
}