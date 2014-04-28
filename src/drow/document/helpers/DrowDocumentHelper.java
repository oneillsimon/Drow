package drow.document.helpers;

import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;

/**
 * <h1>DrowDocumentHelper</h1>
 * This class contains a method to merge to JTextPanes into one, retaining styles and text.
 * <p>
 * @author Simon O'Neill
 * @author Lee Mc Donald
 * @author Graham Wolfe
 * <p>
 */
public class DrowDocumentHelper {
	
	public static void mergeDocument(DefaultStyledDocument source, DefaultStyledDocument dest) throws BadLocationException {
        ArrayList<DefaultStyledDocument.ElementSpec> specs=new ArrayList<DefaultStyledDocument.ElementSpec>();
        DefaultStyledDocument.ElementSpec spec=new DefaultStyledDocument.ElementSpec(new SimpleAttributeSet(), 
                 DefaultStyledDocument.ElementSpec.EndTagType);
        specs.add(spec);
        fillSpecs(source.getDefaultRootElement(), specs, false);
        spec=new DefaultStyledDocument.ElementSpec(new SimpleAttributeSet(), DefaultStyledDocument.ElementSpec.StartTagType);
        specs.add(spec);
 
        DefaultStyledDocument.ElementSpec[] arr = new DefaultStyledDocument.ElementSpec[specs.size()];
        specs.toArray(arr);
        insertSpecs(dest, dest.getLength(), arr);
    }
 
    protected static void insertSpecs(DefaultStyledDocument doc, int offset, DefaultStyledDocument.ElementSpec[] specs) {
        try {
            Method m=DefaultStyledDocument.class.getDeclaredMethod("insert", new Class[] {int.class, DefaultStyledDocument.ElementSpec[].class});
            m.setAccessible(true);
            m.invoke(doc, new Object[] {offset, specs});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    protected static void fillSpecs(Element elem, ArrayList<DefaultStyledDocument.ElementSpec> specs, boolean includeRoot) throws BadLocationException{
        DefaultStyledDocument.ElementSpec spec;
        if (elem.isLeaf()) {
            String str=elem.getDocument().getText(elem.getStartOffset(), elem.getEndOffset()-elem.getStartOffset());
            spec=new DefaultStyledDocument.ElementSpec(elem.getAttributes(), 
                     DefaultStyledDocument.ElementSpec.ContentType,str.toCharArray(), 0, str.length());
            specs.add(spec);
        }
        else {
            if (includeRoot) {
                spec=new DefaultStyledDocument.ElementSpec(elem.getAttributes(), DefaultStyledDocument.ElementSpec.StartTagType);
                specs.add(spec);
            }
            for (int i=0; i<elem.getElementCount(); i++) {
                fillSpecs(elem.getElement(i), specs, true);
            }
 
            if (includeRoot) {
                spec=new DefaultStyledDocument.ElementSpec(elem.getAttributes(), DefaultStyledDocument.ElementSpec.EndTagType);
                specs.add(spec);
            }
        }
    }
}