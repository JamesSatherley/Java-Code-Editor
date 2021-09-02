package nz.ac.massey;

import javax.swing.*;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import java.util.ArrayList;
import java.util.List;

public class Search {
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
