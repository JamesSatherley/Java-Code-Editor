package nz.ac.massey;

import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.dom.element.office.OfficeTextElement;
import org.odftoolkit.odfdom.dom.element.text.TextPElement;
import org.odftoolkit.odfdom.pkg.OdfElement;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OpenODT {
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
                OfficeTextElement officeText = odt.getContentRoot();
                TextPElement firstParagraph = OdfElement.findFirstChildNode(TextPElement.class, officeText);
                String text = firstParagraph.toString();
                text = text.replace("<text:p text:style-name=\"Standard\">", "");
                text = text.replace("</text:p>", "");
                return text;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "Error";
    }
}
