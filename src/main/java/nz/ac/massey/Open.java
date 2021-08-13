package nz.ac.massey;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Open {
    String[] OpenFunction(JFrame frame) {
        String[] returnArray = new String[2];
        String output = "";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Supported, use all files for other", "txt", "class", "java", "xml", "rss", 
        		".project", ".classpath", "h", "sql", "js", "php", "php5", "phtml", "html", "htm", "xhtm", "xhtml", "lua",
        		"bat", "pl", "sh", "css", "json", "rb", "make", "mak", "py", "properties", "config");
        fileChooser.setFileFilter(filter);

        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            Scanner fileIn = null;

            try {
                fileIn = new Scanner(file);
                if (file.isFile()) {
                    while (fileIn.hasNextLine()) {
                        String line = fileIn.nextLine() + "\n";
                        output += line;

                    }
                }
                returnArray[0] = file.getName();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } finally {
                fileIn.close();
            }
        }
        returnArray[1] = output;
        return returnArray;
    }
}
