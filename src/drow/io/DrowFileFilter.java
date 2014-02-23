package drow.io;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class DrowFileFilter extends FileFilter {

	private String extension;
	private String description;
	
	public DrowFileFilter(String extension, String description) {
		this.extension = extension;
		this.description = description;
	}
	
	@Override
	public boolean accept(File file) {
		if(file.isDirectory())
			return true;
		
		if(file.getName().toLowerCase().endsWith(extension))
			return true;
		else
			return false;
	}

	@Override
	public String getDescription() {
		return description + " (." + extension + ")";
	}
	
	public String getExtension() {
		return extension;
	}
	
	public String getFullExtension() {
		return "." + extension;
	}
}
