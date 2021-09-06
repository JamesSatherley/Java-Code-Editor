package nz.ac.massey;

import org.odftoolkit.odfdom.doc.OdfTextDocument;

import java.io.File;

import javax.swing.JFileChooser;


public class PrintToODT {
    public void print(String string) {
        // Using odftextdocument import saves to chosen file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));

        int response = fileChooser.showSaveDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            try {
                OdfTextDocument odt = OdfTextDocument.newTextDocument();
                odt.addText(string);
                odt.save(fileChooser.getSelectedFile().getAbsolutePath());
                odt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}