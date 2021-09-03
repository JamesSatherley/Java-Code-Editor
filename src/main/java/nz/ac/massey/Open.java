package nz.ac.massey;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Open {
    String[] OpenFunction(String usage) {
        String[] returnArray = new String[2];
        String output = "";
        File file = null;
        Scanner fileIn = null;
        
        if (usage.equals("ui")) {
        	 JFileChooser fileChooser = new JFileChooser();
             fileChooser.setCurrentDirectory(new File("."));
             FileNameExtensionFilter filter = new FileNameExtensionFilter("Supported, use all files for other", "txt", "class", "java", "xml", "rss", 
             		".project", ".classpath", "h", "sql", "js", "php", "php5", "phtml", "html", "htm", "xhtm", "xhtml", "lua",
             		"bat", "pl", "sh", "css", "json", "rb", "make", "mak", "py", "properties", "config");
             fileChooser.setFileFilter(filter);

             int response = fileChooser.showOpenDialog(null);

             if (response == JFileChooser.APPROVE_OPTION) {
                 file = new File(fileChooser.getSelectedFile().getAbsolutePath());
             }
             
        }else if (usage.equals("test")){
        	String path = "src\\test\\resources\\OpenTest";
    		file = new File(new File(path).getAbsolutePath());
        }

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
        returnArray[1] = output;
        return returnArray;
    }
}
