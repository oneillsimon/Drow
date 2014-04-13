package drow.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ModePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JButton metalButton = new JButton("    Normal    ");
	private JButton motifButton = new JButton("Developer Mode");

	public ModePanel() {

		add(metalButton);
		add(motifButton);
		metalButton.addActionListener(this);
		motifButton.addActionListener(this);

	}

	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		String plaf = "";
		if (source == metalButton)
			plaf = "com.jtattoo.plaf.graphite.GraphiteLookAndFeel";
		else if (source == motifButton)
			plaf = "com.jtattoo.plaf.hifi.HiFiLookAndFeel";
		try {
			UIManager.setLookAndFeel(plaf);
			SwingUtilities.updateComponentTreeUI(this);
			SwingUtilities.updateComponentTreeUI(getRootPane());
		} catch (Exception e) {
		}
	}
}