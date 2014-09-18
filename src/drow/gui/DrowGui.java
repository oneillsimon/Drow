package drow.gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import drow.view.DocumentView;

/**
 * <h1>DrowGui</h1>
 * This class controls the GUI and JTabbedPane.
 * <p>
 * @author Simon O'Neill
 * @author Graham Wolfe
 * <p>
 */
public class DrowGui {
	
	/** The document view containing the document. */
	private DocumentView documentView;
	
	/** The JTabbedPane containing the panels. */
	private JTabbedPane tabbedPane;
	
	/** The scroll pane in which the document sits. */
	private JScrollPane scrollPane;
	
	/**
	 * <h1>Constructor</h1>
	 * This constructor sets the JFrame title and icon image,
	 * it also sets the document view layout to null so we can position the document with
	 * absolute coordinates.
	 * @param documentView - The view containing the document.
	 * @param tabbedPane - The JTabbedPane containing the panels.
	 */
	public DrowGui(DocumentView documentView, JTabbedPane tabbedPane) {
		this.documentView = documentView;
		
		this.documentView.setTitle("drow");
		this.documentView.setIconImage(new ImageIcon("res/windowIcon/drow.png").getImage());
		
		this.documentView.getDrowDocument().setLayout(null);
		
		scrollPane = new JScrollPane(documentView.getDrowDocument(),
									 JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
									 JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(null);
		this.tabbedPane = tabbedPane;
		
		this.documentView.add(scrollPane, BorderLayout.CENTER);
		this.documentView.add(tabbedPane, BorderLayout.NORTH);
	}
	
	/**
	 * Sets the JTabbedPane.
	 * @param tabbedPane - The JTabbedPane to set.
	 */
	public void setTabbedPane(JTabbedPane tabbedPane) {
		documentView.remove(this.tabbedPane);
		this.tabbedPane = tabbedPane;
		documentView.add(this.tabbedPane, BorderLayout.NORTH);
		tabbedPane.revalidate();
		tabbedPane.repaint();
	}
}