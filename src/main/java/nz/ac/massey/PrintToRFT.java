package nz.ac.massey;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class PrintToRFT {
    public void print(String text){
        // outputs file to selected place with outputstream
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        byte data[] = text.getBytes();
        int response = fileChooser.showOpenDialog(null);

        Path p = Paths.get(fileChooser.getSelectedFile().getAbsolutePath());

        if (response == JFileChooser.APPROVE_OPTION) {
            try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(p , CREATE, APPEND))) {
                out.write(data, 0, data.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
