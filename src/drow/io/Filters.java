package drow.io;
import java.util.ArrayList;

import javax.swing.JFileChooser;

/**
 * <h1>Filters</h1>
 * A static class handling all DrowFileFilters.
 * <p>
 * @author Simon O'Neill
 * <p>
 */
public class Filters {
	
	/** DrowFileFilter for .docx files. */
	public static DrowFileFilter DOCX = new DrowFileFilter("docx", "Microsoft Word 2007/2010 Document");
	
	/** DrowFileFilter for .txt files. */
	public static DrowFileFilter TXT = new DrowFileFilter("txt", "Plain Text File");
	
	/** DrowFileFilter for .rtf files. */
	public static DrowFileFilter RTF = new DrowFileFilter("rtf", "Rich Text Format");
	
	/** DrowFileFilter for .drow files. */
	public static DrowFileFilter DROW = new DrowFileFilter("drow", "Drow worD Document");
	
	/** A list of all the extensions. */
	private static ArrayList<DrowFileFilter> extensions = new ArrayList<DrowFileFilter>();
	
	/** Method to add all the extensions to the list. */
	public static void setUp() {
		extensions.add(DOCX);
		extensions.add(TXT);
		extensions.add(RTF);
		extensions.add(DROW);
	}
	
	/**
	 * Gets a FileFilter based on a String.
	 * @param s - The String containing a file filter
	 * @return DrowFileFilter - The Filter matching the passed in String, if no match is found return the FileFilter for Plain Text.
	 */
	public static DrowFileFilter getFilterFromString(String s) {
		for(int i = 0; i < extensions.size(); i++) {
			if(extensions.get(i).getExtension().equals(s)) {
				return extensions.get(i);
			}
		}
		
		return TXT;
	}
	
	/**
	 * Checks if a string has a matching FileFilter.
	 * @param s - The String containing the FileFilter.
	 * @return boolean - true if the String has a corresponding FileFilter, false if it doesn't.
	 */
	public static boolean isStringFileFilter(String s) {
		for(int i = 0; i < extensions.size(); i++) {
			if(s.equals(extensions.get(i).getExtension())) {
					return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Adds the FileFilters to a JFileChooser.
	 * @param fileChooser - The JFileChooser to add the FileFilters to.
	 */
	public static void addFiltersToFileChooser(JFileChooser fileChooser) {
		for(int i = 0; i < extensions.size(); i++) {
			fileChooser.addChoosableFileFilter(extensions.get(i));
		}
	}

	/**
	 * Gets the ArrayList of FileFilters.
	 * @return ArrayList<<a>DrowFileFilter> - The list of FileFilters.
	 */
	public static ArrayList<DrowFileFilter> getExtensions() {
		return extensions;
	}
	
	/**
	 * Gets the DrowFileFilter at a specified index in the ArrayList.
	 * @param i - The index in the ArrayList.
	 * @return DrowFileFilter - The FileFilter at the specified index.
	 */
	public static String getExtensionString(int i) {
		return extensions.get(i).getExtension();
	}
}