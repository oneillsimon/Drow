package drow.tables;

import javax.swing.text.Document;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.ViewFactory;

public class TableEditorKit extends StyledEditorKit {
    ViewFactory defaultFactory = new TableFactory();
    
    public ViewFactory getViewFactory() {
        return defaultFactory;
    }

    public Document createDefaultDocument() {
        return new TableDocument();
    }
}
