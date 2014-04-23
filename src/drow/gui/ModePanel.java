package drow.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import drow.view.DocumentView;

public class ModePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JButton metalButton = new JButton("    Normal    ");
	private JButton motifButton = new JButton("Developer Mode");
	private DocumentView docView;

	public ModePanel(DocumentView docView) {

		this.docView = docView;
		add(metalButton);
		add(motifButton);
		metalButton.addActionListener(this);
		motifButton.addActionListener(this);

	}

	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		String plaf = "";
		if (source == metalButton) {
			plaf = "com.jtattoo.plaf.graphite.GraphiteLookAndFeel";
			docView.getGui().setTabbedPane(new WordTabs(docView));
			docView.repaint();
			docView.revalidate();
		}
		else if (source == motifButton) {
			plaf = "com.jtattoo.plaf.hifi.HiFiLookAndFeel";
			docView.getGui().setTabbedPane(new DeveloperTabs(docView));
			docView.repaint();
			docView.revalidate();
		}
		try {
			UIManager.setLookAndFeel(plaf);
			SwingUtilities.updateComponentTreeUI(docView);
		} catch (Exception e) {
		}
	}
}