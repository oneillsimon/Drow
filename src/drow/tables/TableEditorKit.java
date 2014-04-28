package drow.tables;

import javax.swing.text.Document;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.ViewFactory;

/**
 * <h1>TableEditorKit</h1>
 * @author Lee Mc Donald
 */
public class TableEditorKit extends StyledEditorKit {

	private static final long serialVersionUID = 1L;
	
	private ViewFactory defaultFactory = new TableFactory();
    
    public ViewFactory getViewFactory() {
        return defaultFactory;
    }

    public Document createDefaultDocument() {
        return new TableDocument();
    }
}
