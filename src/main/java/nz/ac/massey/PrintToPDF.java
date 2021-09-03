package nz.ac.massey;

import java.io.File;

import javax.swing.JFileChooser;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

public class PrintToPDF {
	public void print(String string) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));

        int response = fileChooser.showSaveDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            try {
                PdfWriter writer = new PdfWriter(fileChooser.getSelectedFile().getAbsolutePath());
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf);
                Text text = new Text(string);
                text.setNextRenderer(new PDFSaveOverrider(text));
                document.add(new Paragraph(text));
                document.close(); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
}