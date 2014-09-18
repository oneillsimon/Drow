package drow.document;

import java.awt.GraphicsDevice;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;

import drow.document.helpers.DrowDocumentHelper;
import drow.view.DocumentView;

/**
 * <h1>FullScreenDocument</h1>
 * <p>
 * This class creates a JFrame and JTextPane based off the documents and current document's pages.
 * Then makes a full screen JPanel and displays the pages in full screen, with keyboard navigation to cycle through them.
 * <p>
 * @author Simon O'Neill
 * <p>
 */
public class FullScreenDocument extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	/** The document. */
	private DrowDocument document;
	
	/** The document view. */
	private DocumentView documentView;
	
	/** Integer marking the current page. */
	private int pageIndex;
	
	/** The page to display in full screen. */
	private DrowPage textPane;
	
	/**
	 * Inner class to invoke the full screen JFrame and handle keyboard input.
	 */
	private class KeyDispatcher implements KeyEventDispatcher {

		@Override
		public boolean dispatchKeyEvent(KeyEvent e) {
			
			/**
			 * Exits full screen if the ESC key is pressed.
			 */
			if(e.getID() == KeyEvent.KEY_PRESSED) {
			
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					try {
						UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
						SwingUtilities.updateComponentTreeUI(documentView);
					} catch (Exception e1) {
					}
					dispose();
				}
				
				/**
				 * If the Left key is pressed, decrement the page index, remove the page from the frame,
				 * merge it with the previous page and add it back to the frame.
				 */
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					if(pageIndex > 0) {
						pageIndex--;
						try {
							getContentPane().remove(textPane);
							textPane = new DrowPage(0);
							textPane.setEditable(false);
							DrowDocumentHelper.mergeDocument(document.getPages().get(pageIndex).getStyledDocument(), textPane.getStyledDocument());
							getContentPane().add(textPane);
							repaint();
						} catch (BadLocationException e1) {
						}
					}
				}
				
				/**
				 * If the Right key is pressed, increment the page index, remove the page from the frame,
				 * merge it with the next page and add it back to the frame.
				 */
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if(pageIndex < document.getPages().size() - 1) {
						pageIndex++;
						try {
							getContentPane().remove(textPane);
							textPane = new DrowPage(0);
							textPane.setEditable(false);
							DrowDocumentHelper.mergeDocument(document.getPages().get(pageIndex).getStyledDocument(), textPane.getStyledDocument());
							getContentPane().add(textPane);
							repaint();
						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
			
			return false;
		}
	}
	
	/**
	 * <h1>Constructor</h1>
	 * @param documentView - The document view containing the document.
	 */
	public FullScreenDocument(DocumentView documentView) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		this.documentView = documentView;
		this.document = documentView.getDrowDocument();
		this.pageIndex = 0;
		
		try {
			this.textPane = new DrowPage(0);
			DrowDocumentHelper.mergeDocument(document.getPages().get(pageIndex).getStyledDocument(), textPane.getStyledDocument());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		textPane.setEditable(false);
		getContentPane().add(textPane);
		
		/**
		 * Set the frame to maximised and remove all decoration.
		 */
		this.setUndecorated(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		GraphicsDevice graphicsDevice = this.getGraphicsConfiguration().getDevice();
		graphicsDevice.setFullScreenWindow(this);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyDispatcher());

		setResizable(false);
		setVisible(true);

		/**
		 * Launch the new full screen JFrame.
		 */
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Point p = new Point(0, 0);
				SwingUtilities.convertPointToScreen(p, getContentPane());
				Point l = getLocation();
				l.x -= p.x;
				l.y -= p.y;
				setLocation(l);
			}
		});
	}
}