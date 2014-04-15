package drow.spellchecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {

	// graham is gay
	List<String> dictionary = new ArrayList<String>();
    public String buffer;
    String resDirectory = "res/";

    // returns to word most similar to the word inputted
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
    
    public Boolean isWord(String ToFind) {
        Double temp;
        Double temp2 = 100.00; // set big distance for first switch
        
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

    public Dictionary() throws IOException {
        
    	BufferedReader br = new BufferedReader(new FileReader(resDirectory + "englishDictionary.txt"));
    	String line;
    	while ((line = br.readLine()) != null) {

    		String word = line;
    		dictionary.add(word);
    		//TODO use a map to map the definition of the word to the word
    	}
    	br.close();
    }
}
