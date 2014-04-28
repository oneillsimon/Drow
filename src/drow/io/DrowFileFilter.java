package drow.io;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * <h1>DrowFileFilter</h1>
 * Used to define the file extensions supported by Drow.
 * <p>
 * @author Simon O'Neill
 * <p>
 */
public class DrowFileFilter extends FileFilter {

	/** The extension of the file. */
	private String extension;
	
	/** The description of the extension. */
	private String description;
	
	/**
	 * <h1>Constructor</h1>
	 * @param extension - The file extension.
	 * @param description - The description of the extension.
	 */
	public DrowFileFilter(String extension, String description) {
		this.extension = extension;
		this.description = description;
	}
	
	/**
	 * Whether the given file is accepted by this filter.
	 */
	@Override
	public boolean accept(File file) {
		if(file.isDirectory())
			return true;
		
		if(file.getName().toLowerCase().endsWith(extension))
			return true;
		else
			return false;
	}

	/**
	 * Gets the description of the file filter.
	 */
	@Override
	public String getDescription() {
		return description + " (." + extension + ")";
	}
	
	/**
	 * Gets the extension of the file filter.
	 * @return String - the extension.
	 */
	public String getExtension() {
		return extension;
	}
	
	/**
	 * Gets the extension of the file filter including the ".".
	 * @return String - the extension including the dot.
	 */
	public String getFullExtension() {
		return "." + extension;
	}
}
