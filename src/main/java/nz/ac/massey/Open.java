package nz.ac.massey;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Open {

	public Open(JTextArea txtrTextHere) {
		// TODO Auto-generated constructor stub
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", ".txt");
        fileChooser.setFileFilter(filter);
		
		int response = fileChooser.showOpenDialog(null);
		
		if (response == JFileChooser.APPROVE_OPTION) {
			File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			Scanner fileIn = null;
			
			try {
				fileIn = new Scanner(file);
				if(file.isFile()) {
					while(fileIn.hasNextLine()) {
						String line = fileIn.nextLine()+"\n";
						txtrTextHere.append(line);
						
					}
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			finally {
				fileIn.close();
			}
			
			
		}
	}
}

