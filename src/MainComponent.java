import javax.swing.UIManager;

import drow.view.DocumentView;


public class MainComponent {
	public static void main(String[] args) {
		 try {
			  UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
			  //UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
			  //UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
			  
			  
			  } catch (Exception e) {
			  System.err.println("Look and feel not set.");
			  System.out.println(e.getStackTrace() + "\n");
			  System.out.println(e.getCause());
			  }	 
		
		new DocumentView();
		
	}
}