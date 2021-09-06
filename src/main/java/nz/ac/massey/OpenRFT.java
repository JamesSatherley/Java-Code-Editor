package nz.ac.massey;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OpenRFT {
    // Gets file from user using FileChooser and imports it using bufferedReader, adding line by line and returning as a string
    String OpenFunction () {
        String output = "";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        int response = fileChooser.showOpenDialog(null);

        Path p = Paths.get(fileChooser.getSelectedFile().getAbsolutePath());

        if (response == JFileChooser.APPROVE_OPTION) {
            try (InputStream in = Files.newInputStream(p);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    output += line + "\n";
                }
                return output;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "Error";
    }
}