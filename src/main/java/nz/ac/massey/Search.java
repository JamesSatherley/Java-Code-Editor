package nz.ac.massey;

import javax.swing.*;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import java.awt.*;

public class Search {
    RSyntaxTextArea SearchFunction(RSyntaxTextArea textArea) {
        String searchBox = (String)JOptionPane.showInputDialog("Search Text:");
        final int l1 = textArea.getText().indexOf(searchBox);
        final int l2 = searchBox.length();

        if (l1 == -1) {
            JOptionPane.showMessageDialog(null, "Search Value Not Found");
        } else {
            textArea.select(l1, l2+l1);
        }
        return textArea;
    }
}
