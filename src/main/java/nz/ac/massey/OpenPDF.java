package nz.ac.massey;

import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.doc.OdfTextDocument;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OpenPDF {
    String OpenFunction() {
        String[] returnArray = new String[2];
        String output = "";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Supported, use all files for other", "odt");
        fileChooser.setFileFilter(filter);

        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            try {
                OdfTextDocument odt = (OdfTextDocument) OdfDocument.loadDocument(fileChooser.getSelectedFile().getAbsolutePath());
                return odt.toString();



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "Error";
    }
}
