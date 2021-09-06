package nz.ac.massey;

import java.util.ArrayList;
import java.util.List;

public class Search {
    // Gets indexes of words in string
    List<Integer> indexes = new ArrayList<Integer>();

    List<Integer> findIndexes(String word, String text){
        word = word.toLowerCase();
        text = text.toLowerCase();
        int index = 0;

        while (index != -1) {
            index = text.indexOf(word, index);
            if (index != -1) {
                indexes.add(index);
                index++;
            }
        }
        return indexes;
    }
}
