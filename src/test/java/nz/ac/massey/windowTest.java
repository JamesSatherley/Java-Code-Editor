package nz.ac.massey;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.BadLocationException;

import org.junit.jupiter.api.Test;

class windowTest {

	@Test
	void testOpen() throws BadLocationException, IOException {
		Open topen = new Open();		
		String[] opentest = topen.OpenFunction("test");
		assertTrue(opentest[1].contains("Test Text"));
	}
	
	@Test
	void testSave() {
		Save tsave = new Save();
		String output = "";
		
		double randomDouble = Math.random();
		String randomString = String.valueOf(randomDouble);
		
		tsave.SaveFunction(randomString, "test");
    	String path = "src\\test\\resources\\SaveTest.txt";
		File ReadFile = new File(new File(path).getAbsolutePath());
		
		try {
			Scanner read = new Scanner(ReadFile);
            if (ReadFile.isFile()) {
                while (read.hasNextLine()) {
                    String line = read.nextLine() + "\n";
                    output += line;
                }
            }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(output.contains(randomString));
	}
	
	@Test
	void testSearch() {
		Search tsearch = new Search();
		String testText = "This is test text to test the search feature.";
		List<Integer> foundWords = tsearch.findIndexes("test", testText);
		
		assertTrue(foundWords.get(0) == 8);
		assertTrue(foundWords.get(1) == 21);
	}

}
