package drow.io;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class ImageImport extends JFrame
{
	private static final long serialVersionUID = 1L;

	public ActionListener insertActionPerformed(final JTextPane textPane)
    {
        
			return new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					InsertImage(textPane);
					
				}
			};
    }
    
    private void InsertImage(JTextPane textPane){
    	JFileChooser jf=new JFileChooser();
        
        // Show open dialog
        int option=jf.showOpenDialog(this);
        
            // If user chooses to insert..
            if(option==JFileChooser.APPROVE_OPTION)
            {
                File file=jf.getSelectedFile();
                    if(isImage(file))
                    {
                        // Insert the icon
                        textPane.insertIcon(new ImageIcon(file.getAbsolutePath()));
                    }
                    else
                    // Show an error message, if not an image
                    JOptionPane.showMessageDialog(this,"The file is not an image.","Not Image",JOptionPane.ERROR_MESSAGE);
            }
    	
    }
    
    private boolean isImage(File file)
    {
        String name=file.getName();
            return name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".jpeg") || name.endsWith(".gif");
    
            
           
    }
}
