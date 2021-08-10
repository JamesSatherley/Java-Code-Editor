package nz.ac.massey;

import javax.swing.*;

public class Search {
    JTextArea SearchFunction(JTextArea textBox) {
        String searchBox = (String)JOptionPane.showInputDialog("Search Text:");
        final int l1 = textBox.getText().indexOf(searchBox);
        final int l2 = searchBox.length();

        if (l1 == -1) {
            JOptionPane.showMessageDialog(null, "Search Value Not Found");
        } else {
            textBox.select(l1, l2+l1);
        }
        return textBox;
    }
}
