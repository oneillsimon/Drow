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

public class FullScreenDocument extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private DrowDocument document;
	private DocumentView docView;
	private int pageIndex;
	private DrowPage textPane;
	
	private class KeyDispatcher implements KeyEventDispatcher {

		@Override
		public boolean dispatchKeyEvent(KeyEvent e) {
			
			if(e.getID() == KeyEvent.KEY_PRESSED) {
			
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					try {
						UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
						SwingUtilities.updateComponentTreeUI(docView);
					} catch (Exception e1) {
					}
					dispose();
				}
				
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
	
	public FullScreenDocument(DocumentView docView) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		this.docView = docView;
		this.document = docView.getDrowDocument();
		this.pageIndex = 0;
		
		try {
			this.textPane = new DrowPage(0);
			DrowDocumentHelper.mergeDocument(document.getPages().get(pageIndex).getStyledDocument(), textPane.getStyledDocument());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		textPane.setEditable(false);
		getContentPane().add(textPane);
		
		this.setUndecorated(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		GraphicsDevice graphicsDevice = this.getGraphicsConfiguration().getDevice();
		graphicsDevice.setFullScreenWindow(this);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyDispatcher());

		setResizable(false);
		setVisible(true);

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