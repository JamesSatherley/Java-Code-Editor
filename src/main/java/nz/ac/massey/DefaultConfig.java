package nz.ac.massey;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DefaultConfig {
    // Generates config file if it doesn't exist
    String s = "#\n" +
            "#         Config file for J & J File editor\n" +
            "#    Developed by James Satherley & James Gorman\n" +
            "#\n" +
            "#\n" +
            "# All colours must be defined RGB values (0, 0, 0 would be black for example, max numbers are (255, 255, 255). These numbers are seperated into 3 3\n" +
            "# digit numbers (000,000,000) but must be put together. !!! If items error the file will be reset as a fail-safe !!!\n" +
            "#\n" +
            "\n" +
            "# Blinking bar in document\n" +
            "Carot-Colour = 255,255,255\n" +
            "\n" +
            "# When you click, hold and drag over text\n" +
            "Text-Highlight = 0, 56, 143\n" +
            "\n" +
            "# Background colour of whole document\n" +
            "Background = 0, 21, 54\n" +
            "\n" +
            "# Text Colour\n" +
            "Foreground = 214, 214, 214\n" +
            "\n" +
            "# Line highlight colour\n" +
            "Line-Colour = 0, 36, 92\n" +
            "\n" +
            "# Line number background colour\n" +
            "Gutter-Background = 0, 36, 92\n" +
            "\n" +
            "# Line number text colour\n" +
            "Gutter-Foreground = 50, 214, 214\n" +
            "\n" +
            "# Default window size (formatted w,h)\n" +
            "Window-Size = 1000, 800\n" +
            "\n" +
            "# Font Name\n" +
            "Font = Dialog\n" +
            "\n" +
            "# Font Style\n" +
            "Font-Style = 0\n" +
            "\n" +
            "# Font Size\n" +
            "Font-Size = 12";
    public void createConfig(){
        Path path = Paths.get("config.yaml");

        try {
            Files.writeString(path, s, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
