package drow.io;
import java.util.ArrayList;



public class Filters {
	public static DrowFileFilter DOC = new DrowFileFilter("doc", "Microsoft Word 97/2000/XP/2003");
	public static DrowFileFilter DOCX = new DrowFileFilter("docx", "Microsoft Word 2007/2010");
	public static DrowFileFilter TXT = new DrowFileFilter("txt", "Text");
	public static DrowFileFilter RTF = new DrowFileFilter("rtf", "Rich Text Format");
	
	private static ArrayList<DrowFileFilter> extensions = new ArrayList<DrowFileFilter>();
	
	//
	public static void setUpFilters() {
		extensions.add(DOC);
		extensions.add(DOCX);
		extensions.add(TXT);
		extensions.add(RTF);
	}
	
	public static DrowFileFilter getFilterFromString(String s) {

		for(int i = 0; i < extensions.size(); i++) {
			if(extensions.get(i).getExtension().equals(s)) {
				return extensions.get(i);
			}
		}
		
		return TXT;
	}

	public static ArrayList<DrowFileFilter> getExtensions() {
		return extensions;
	}
	
	public static String getExtensionString(int i) {
		return extensions.get(i).getExtension();
	}
}
