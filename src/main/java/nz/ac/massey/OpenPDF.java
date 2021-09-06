package nz.ac.massey;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.LocationTextExtractionStrategy;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.doc.OdfTextDocument;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OpenPDF {
    // Gets file from user using FileChooser and imports it using pdfreader which is from the itext dependency. it then adds each line in a for loop and returns as a string
    String OpenFunction() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Supported, use all files for other", "pdf");
        fileChooser.setFileFilter(filter);

        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            try {
                PdfReader reader = new PdfReader(fileChooser.getSelectedFile().getAbsolutePath());
                PdfDocument pdf = new PdfDocument(reader);
                String str = "";
                for (int i = 1; i <= pdf.getNumberOfPages(); i++) {
                    str = str+PdfTextExtractor.getTextFromPage(pdf.getPage(i), new LocationTextExtractionStrategy());
                }
                return str;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "Error";
    }
}
