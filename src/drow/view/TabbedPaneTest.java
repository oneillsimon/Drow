package drow.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class TabbedPaneTest {
    public static void main(String [] a) {
    	JFrame frame= new JFrame("test frame");
        JPanel panel= new JPanel() {
            public void paintComponent(Graphics g) {
 
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.drawOval(0, 0, 200, 200);
            }
        };
 
        JLabel label= new JLabel("test");
 
        panel.add(label);
        label.setBounds(400, 400, 60, 20);
 
        panel.setPreferredSize(new Dimension(500, 500));
        panel.setLayout(null);
 
        JScrollPane sp= new JScrollPane(panel);
 
        frame.getContentPane().add(sp, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
   }
}