package drow.spellchecker;

/**
 * <h1>EditDistance</h1>
 * @author Lee Mc Donald
 */
public class EditDistance {
    private static double Min3(double d, double e, double f) {
        if ((d < e) && (d < f)) {
            return d;
        }
        
        if ((e < d) && (e < f)) {
            return e;
        }
        return f;
    }

    public static double MinimumEditDistance(String needle, String haystack) {
        int rows = needle.length() + 1;
        int cols = haystack.length() + 1;
        Matrix m = new Matrix(rows, cols);
        
        for (int row = 0 ; row < rows; row ++) {
	        m.Set(row, 0, row);
        }
        
        for (int col = 0 ; col < cols; col ++) {
	        m.Set(0, col, col);
        }	

        for (int row = 1 ; row < rows ; row ++) {
	        for (int col = 1 ; col < cols ; col ++) {
		        if (haystack.charAt(col -1) == needle.charAt(row-1)) {
			        m.Set(row, col, m.Get(row -1 , col -1));
		        }
		        else {
			        double min = Min3(m.Get(row -1 , col -1), m.Get(row , col -1), m.Get(row -1 , col ));
			        m.Set(row, col, min + 1);
		        }
	        }
        }
        return (int) m.Get(rows - 1, cols - 1);
    }
}
