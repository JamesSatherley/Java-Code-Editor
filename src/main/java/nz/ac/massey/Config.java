package nz.ac.massey;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Config {
    List<String> result = new ArrayList<>();
    List<List<Integer>> output = new ArrayList<>();
    Color Carot, Highlight, Background, Foreground, Line, GutterBackground, GutterForeground;

    void getPropValues(){

        File file = new File("config.properties");
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
            e1.printStackTrace();
        } finally {
            fileIn.close();
        }
    }

    void convertToVariables() {
        for (String s:result) {
            String[] tempStringArray = s.replaceAll("\\s+","").split(",");
            output.add(IntStream.range(0, 3).mapToObj(i -> Integer.parseInt(tempStringArray[i])).collect(Collectors.toList()));
        }
        Carot = new Color(output.get(0).get(0),output.get(0).get(1),output.get(0).get(2));
        Highlight = new Color(output.get(1).get(0),output.get(1).get(1),output.get(1).get(2));
        Background = new Color(output.get(2).get(0),output.get(2).get(1),output.get(2).get(2));
        Foreground = new Color(output.get(3).get(0),output.get(3).get(1),output.get(3).get(2));
        Line = new Color(output.get(4).get(0),output.get(4).get(1),output.get(4).get(2));
        GutterBackground = new Color(output.get(5).get(0),output.get(5).get(1),output.get(5).get(2));
        GutterForeground = new Color(output.get(6).get(0),output.get(6).get(1),output.get(6).get(2));
    }
}
