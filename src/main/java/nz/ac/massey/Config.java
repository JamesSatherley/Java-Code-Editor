package nz.ac.massey;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Config {
    int fontStyle, fontSize, width, height;
    String fontName;
    List<String> result = new ArrayList<>();
    List<List<Integer>> output = new ArrayList<>();
    Color Carot, Highlight, Background, Foreground, Line, GutterBackground, GutterForeground;
    int NON_COLOUR_DATA = 4;

    void getPropValues(){

        File file = new File("config.yaml");
        Scanner fileIn = null;
        try {
            fileIn = new Scanner(file);
            if (file.isFile()) {
                while (fileIn.hasNextLine()) {
                    String line = fileIn.nextLine();
                    if (!line.contains("#") && !line.equals("")) {
                        result.add(line.substring(line.indexOf("= ") + 2));
                    }
                }
            }
        } catch (FileNotFoundException e1) {
            DefaultConfig dc = new DefaultConfig();
            dc.createConfig();
            JOptionPane.showMessageDialog(null, "Welcome to J&J Pad! Default config generated");
            getPropValues();
        } finally {
            if (fileIn != null) {
                fileIn.close();
            }
        }
    }

    void convertToVariables() {
        try {
            for (int j = 0; j < result.size() - NON_COLOUR_DATA; j++) {
                String s = result.get(j);
                String[] tempStringArray = s.replaceAll("\\s+", "").split(",");
                output.add(IntStream.range(0, 3).mapToObj(i -> Integer.parseInt(tempStringArray[i])).collect(Collectors.toList()));
            }
            Carot = new Color(output.get(0).get(0), output.get(0).get(1), output.get(0).get(2));
            Highlight = new Color(output.get(1).get(0), output.get(1).get(1), output.get(1).get(2));
            Background = new Color(output.get(2).get(0), output.get(2).get(1), output.get(2).get(2));
            Foreground = new Color(output.get(3).get(0), output.get(3).get(1), output.get(3).get(2));
            Line = new Color(output.get(4).get(0), output.get(4).get(1), output.get(4).get(2));
            GutterBackground = new Color(output.get(5).get(0), output.get(5).get(1), output.get(5).get(2));
            GutterForeground = new Color(output.get(6).get(0), output.get(6).get(1), output.get(6).get(2));

            String windowSize = result.get(result.size() - NON_COLOUR_DATA);
            String[] tempWindowSizeArray = windowSize.replaceAll("\\s+", "").split(",");
            width = Integer.parseInt(tempWindowSizeArray[0]);
            height = Integer.parseInt(tempWindowSizeArray[1]);

            fontName = result.get(result.size() - NON_COLOUR_DATA + 1).replaceAll(" ", "");
            fontStyle = Integer.parseInt(result.get(result.size() - NON_COLOUR_DATA + 2).replaceAll(" ", ""));
            fontSize = Integer.parseInt(result.get(result.size() - NON_COLOUR_DATA + 3).replaceAll(" ", ""));
        } catch (Exception e) {
            DefaultConfig dc = new DefaultConfig();
            dc.createConfig();
            JOptionPane.showMessageDialog(null, "Error: Config setup is not correct and has caused crash. Please restart");
            System.exit(0);
        }
    }
}
