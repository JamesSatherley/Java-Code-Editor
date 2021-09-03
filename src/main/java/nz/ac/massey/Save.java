package nz.ac.massey;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Save {
    String name;
    void SaveFunction(String s, String usage) {
        File file = null;
        PrintWriter fileOut = null;
    	
    	if (usage.equals("ui")) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));

        int response = fileChooser.showSaveDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            file = new File(fileChooser.getSelectedFile().getAbsolutePath());
        }
    	}
        
        else if (usage.equals("test")) {
        	String path = "src\\test\\resources\\SaveTest.txt";
    		file = new File(new File(path).getAbsolutePath());
        }
        
            try {
                fileOut = new PrintWriter(file);
                fileOut.println(s);
            } catch (FileNotFoundException el) {
                el.printStackTrace();

            } finally {
                fileOut.close();
                name = "J&J Pad | " + file.getName();
            }
    }
}