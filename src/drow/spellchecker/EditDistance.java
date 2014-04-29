package drow.spellchecker;
/**
 * 
 * @author leemcdonald
 *
 * This class implements the classic Levenshtein distance algorithm. 
 * The Levenshtein distance algorithm is used to measure the distance 
 * between two words which are mapped to Matrix's. More famously known as the 
 * Needle and haystack algorithm. This class implementation was forwarded engineered
 * from Dr.Bryan Dugan's second year lecture.
 */
public class EditDistance {
	
	/**
	 * 
	 * @param d
	 * @param e
	 * @param f
	 * @return
	 */
    private static double Min3(double d, double e, double f) {
        if ((d < e) && (d < f)) {
            return d;
        }
        
        if ((e < d) && (e < f)) {
            return e;
        }
        return f;
    }
    /**
     * 
     * @param needle
     * @param haystack
     * @return
     * 
     * Needle is referred to the word we are looking for with in the haystack 
     * which is the dictionary.
     */
    public static double MinimumEditDistance(String needle, String haystack) {
        int rows = needle.length() + 1;
        int cols = haystack.length() + 1;
        Matrix m = new Matrix(rows, cols);
        
        /** 
         * Setting the rows of the matrix with the needle word
         */
        for (int row = 0 ; row < rows; row ++) {
	        m.Set(row, 0, row);
        }
        /**
         *  Setting columns of the matrix with the haystack word
         *  from the dictionary
         */
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