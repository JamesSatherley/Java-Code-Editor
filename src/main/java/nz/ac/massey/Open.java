package nz.ac.massey;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Open {
    String Open() {
        String output = "";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));

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
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } finally {
                fileIn.close();
            }
        }
        return output;
    }
}
