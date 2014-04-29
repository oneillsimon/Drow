package drow.spellchecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *  <h1>DrowFileFilter</h1>
 * Used to store the Dictionary in a Array list. The class also provides methods
 * to search the Dictionary to ensure that the word typed is in the dictionary.
 * <p>
 * @author Lee McDonald
 * <p>
 */
public class Dictionary {

	/** ArrayList to store the Dictionary. */
	List<String> dictionary = new ArrayList<String>();
	
	/** Buffer to store possible word matches */
    public String buffer;
    
    String resDirectory = "res/dictionary/";
    
	/**
	 * <h1>Constructor</h1>
	 * @param fileName - The file extension.
	 */
    public Dictionary(String fileName) throws IOException {
        
    	BufferedReader br = new BufferedReader(new FileReader(resDirectory + fileName));
    	String line;
    	while ((line = br.readLine()) != null) {
    		String word = line;
    		dictionary.add(word);
    		//TODO use a map to map the definition of the word to the word
    	}
    	br.close();
    }

    /**
     * returns to word most similar to the word inputed 
     * @param ToFind
     * @return
     */
    public String FindTheClosest(String ToFind)
    {
        Double temp;
        Double temp2 = 100.00;
        
        for(String d : dictionary ) {
        	
            temp = EditDistance.MinimumEditDistance(d, ToFind);
            if (temp == 0) {
                return "Correct";
            }
            if (temp < temp2) {
                buffer = "the word might be" + d;
                temp2 = temp;
            }
        }
        return buffer;
    }

    /** 
     * Returns true or false depending on if the word is in the dictionary
     * @param ToFind
     * @return
     */
    public Boolean isWord(String ToFind) {
        Double temp;
        Double temp2 = 2.00; // set big distance for first switch
        
        // looping through the dictionary
        // TODO need to refactor this for performance (possible hashTable)
        for(String d : dictionary ) {
            temp = EditDistance.MinimumEditDistance(d, ToFind);
            
            // if the distance between the words is zero the word exists
            if (temp == 0) {
                return true;
            }

            if (temp < temp2) {
                temp2 = temp;
            }
        }
        return false;
    }
}