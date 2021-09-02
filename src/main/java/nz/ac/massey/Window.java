package nz.ac.massey;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.BadLocationException;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

public class Window extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
    private RSyntaxTextArea textArea;
    private String savedFile = "";
    private int index;
    private JTextField searchField, replaceField;
    private JButton searchForButton, searchNextButton, searchExitButton, searchReplaceAllButton;
    private Search search;

    @SuppressWarnings("deprecation")
	Window() throws BadLocationException, IOException {
        frame = new JFrame("J&J Pad | Unnamed");
        frame.setIconImage(ImageIO.read(new File("icon.png")));
        JMenuBar menuBar = new JMenuBar();
        
        textArea = new RSyntaxTextArea(30, 60);
        RTextScrollPane scrollPane = new RTextScrollPane(textArea);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setText("");
		textArea.setAutoIndentEnabled(true);
		textArea.setMarkOccurrences(true);
		textArea.setTabSize(4);

        Config config = new Config();
        config.getPropValues();
        config.convertToVariables();

        textArea.setCaretColor(config.Carot);
        textArea.setBackground(config.Background);
        textArea.setForeground(config.Foreground);
        textArea.setSelectionColor(config.Highlight);
        textArea.setCurrentLineHighlightColor(config.Line);
        scrollPane.getGutter().setBackground(config.GutterBackground);
        scrollPane.getGutter().setForeground(config.GutterForeground);

        JMenu submenu_exportAsMenu = new JMenu("Export As");
        JMenuItem exportPDF = new JMenuItem("Export as PDF");
        JMenuItem exportODT = new JMenuItem("Export as ODT");
        exportPDF.addActionListener(this);
        exportODT.addActionListener(this);
        submenu_exportAsMenu.add(exportPDF);
        submenu_exportAsMenu.add(exportODT);

        JMenu submenu_importAsMenu = new JMenu("Import As");
        JMenuItem importPDF = new JMenuItem("Import PDF");
        JMenuItem importODT = new JMenuItem("Import ODT");
        importPDF.addActionListener(this);
        importODT.addActionListener(this);
        submenu_importAsMenu.add(importPDF);
        submenu_importAsMenu.add(importODT);

        JMenu fileMenu = new JMenu("File");
        JMenuItem fileMenuNew = new JMenuItem("New");
        JMenuItem fileMenuOpen = new JMenuItem("Open");
        JMenuItem fileMenuSave = new JMenuItem("Save");
        fileMenuNew.addActionListener(this);
        fileMenuOpen.addActionListener(this);
        fileMenuSave.addActionListener(this);
        fileMenu.add(fileMenuNew);
        fileMenu.add(fileMenuOpen);
        fileMenu.add(fileMenuSave);
        fileMenu.add(submenu_exportAsMenu);
        fileMenu.add(submenu_importAsMenu);

        JMenu editMenu = new JMenu("Edit");
        JMenuItem editMenuCut = new JMenuItem("Cut");
        JMenuItem editMenuCopy = new JMenuItem("Copy");
        JMenuItem editMenuPaste = new JMenuItem("Paste");
        JMenuItem editMenuTimeDate = new JMenuItem("Insert Time and Date");
        JMenuItem editMenuSearch = new JMenuItem("Find");
        editMenuCut.addActionListener(this);
        editMenuCopy.addActionListener(this);
        editMenuPaste.addActionListener(this);
        editMenuTimeDate.addActionListener(this);
        editMenuSearch.addActionListener(this);
        editMenu.add(editMenuCut);
        editMenu.add(editMenuCopy);
        editMenu.add(editMenuPaste);
        editMenu.add(editMenuTimeDate);
        editMenu.add(editMenuSearch);

        JMenu viewMenu = new JMenu("View");
        JMenuItem viewMenuPrint = new JMenuItem("Print");
        JMenuItem viewMenuPrintCSOF = new JMenuItem("Change Size of Window");
        viewMenuPrint.addActionListener(this);
        viewMenuPrintCSOF.addActionListener(this);
        viewMenu.add(viewMenuPrint);
        viewMenu.add(viewMenuPrintCSOF);
        
        JMenu helpMenu = new JMenu("Help");
        JMenuItem helpMenuCloseAll = new JMenuItem("Close All");
        JMenuItem helpMenuAbout = new JMenuItem("About");
        helpMenuCloseAll.addActionListener(this);
        helpMenuAbout.addActionListener(this);
        helpMenu.add(helpMenuCloseAll);
        helpMenu.add(helpMenuAbout);

        searchForButton = new JButton("Search for");
        searchField = new JTextField();
        searchNextButton = new JButton("Next");
        replaceField = new JTextField();
        searchReplaceAllButton = new JButton("Replace All");
        searchExitButton = new JButton("Exit Search");

        searchForButton.addActionListener(this);
        searchNextButton.addActionListener(this);
        searchReplaceAllButton.addActionListener(this);
        searchExitButton.addActionListener(this);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);

        menuBar.add(searchForButton);
        menuBar.add(searchField);
        menuBar.add(searchNextButton);
        menuBar.add(replaceField);
        menuBar.add(searchReplaceAllButton);
        menuBar.add(searchExitButton);

        searchField.setVisible(false);
        replaceField.setVisible(false);
        searchForButton.setVisible(false);
        searchNextButton.setVisible(false);
        searchReplaceAllButton.setVisible(false);
        searchExitButton.setVisible(false);

        frame.setJMenuBar(menuBar);
        frame.add(scrollPane);
        frame.setSize(1000, 800);
        frame.show();
        
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
            	if(!savedFile.equals(textArea.getText())) {
                    int resp = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit without saving?", "Exit?", JOptionPane.YES_NO_OPTION);
                    if (resp == JOptionPane.YES_OPTION) {
                    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    } else {
                        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    }
            	} else {
                	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            	}
            }
        });
    }

    @SuppressWarnings("unused")
	@Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "New":
                try {
                    Window window = new Window();
                } catch (BadLocationException | IOException exception) {
                	exception.printStackTrace();
                }
                break;
            case "Open":
                Open open = new Open();
                if (textArea.getText().equals("")) {
                	String[] returnArray = open.OpenFunction();
                    textArea.setText(returnArray[1]);
                    SetFrame("J&J Pad | " + returnArray[0]);
                } else {
                    Window windowNew = null;
                    try {
                        windowNew = new Window();
                    } catch (BadLocationException | IOException exception) {
                    	exception.printStackTrace();
                    }
                	String[] returnArray = open.OpenFunction();
                    windowNew.textArea.setText(returnArray[1]);
                    SetFrame("J&J Pad | " + returnArray[0]);
                }
                break;
            case "Save":
                Save save = new Save();
                save.SaveFunction(textArea.getText().equals("") ? "" : textArea.getText(), frame);
                savedFile = textArea.getText();
                break;
            case "Find":
                searchField.setVisible(true);
                replaceField.setVisible(true);
                searchForButton.setVisible(true);
                searchNextButton.setVisible(true);
                searchReplaceAllButton.setVisible(true);
                searchExitButton.setVisible(true);
                break;
            case "Search for":
                search = new Search();
                search.findIndexes(searchField.getText(), textArea.getText());
                index = 1;
                if (search.indexes.size() > 0) {
                    textArea.select(search.indexes.get(0), search.indexes.get(0) + searchField.getText().length());
                } else {
                    JOptionPane.showMessageDialog(null, "No occurrences");
                }
                break;
            case "Next":
                if(index == search.indexes.size()) index = 0;
                textArea.select(search.indexes.get(index), search.indexes.get(index) + searchField.getText().length());
                index++;
                break;
            case "Replace All":
                search = new Search();
                search.findIndexes(searchField.getText(), textArea.getText());
                Collections.reverse(search.indexes);
                for (int n : search.indexes) {
                    textArea.replaceRange(replaceField.getText(), n, n + searchField.getText().length());
                }
                break;
            case "Exit Search":
                searchField.setVisible(false);
                replaceField.setVisible(false);
                searchForButton.setVisible(false);
                searchNextButton.setVisible(false);
                searchReplaceAllButton.setVisible(false);
                searchExitButton.setVisible(false);
                break;
            case "Export as PDF":
                PrintToPDF pdfPrinter = new PrintToPDF();
                pdfPrinter.print(textArea.getText().equals("") ? "" : textArea.getText());
                break;
            case "Export as ODT":
                PrintToODT odtPrinter = new PrintToODT();
                odtPrinter.print(textArea.getText().equals("") ? "" : textArea.getText());
                break;
            case "Import PDF":
                OpenPDF pdfOpener = new OpenPDF();
                textArea.setText(pdfOpener.OpenFunction());
                break;
            case "Import ODT":
                OpenODT odtOpener = new OpenODT();
                textArea.setText(odtOpener.OpenFunction());
                break;
            case "Change Size of Window":
                ChangeDims d = new ChangeDims();
                int[] tempArray = ChangeDims.getNewDims(frame.getWidth(), frame.getHeight());
                frame.setSize(new Dimension(tempArray[0], tempArray[1]));
                break;
            case "Print":
				try {
					textArea.print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}            
            	break;
            case "Cut":
                textArea.cut();
                break;
            case "Copy":
                textArea.copy();
                break;
            case "Paste":
                textArea.paste();
                break;
            case "Insert Time and Date":
            	DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            	DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
                textArea.setText("Date: "+ date.format(LocalDateTime.now()) + ", Time: " + time.format(LocalDateTime.now()) + "\n" + textArea.getText());
                break;
            case "Close All":
            	System.exit(0);
                break;
            case "About":
                JOptionPane.showMessageDialog(null, "Hello! Devloped by James Gorman and James Satherley!");
                break;
            default:
                System.out.println("Default output in switch; Error!! Somehow no item was selected");
        }
    }
    
    void SetFrame(String name) {
    	frame.setTitle(name);
    	if (name.toLowerCase().endsWith(".class") || name.toLowerCase().endsWith(".java"))
    	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
    	  else if (name.toLowerCase().endsWith(".xml") || name.toLowerCase().endsWith(".rss")
    	      || name.toLowerCase().endsWith(".project") || name.toLowerCase().endsWith(".classpath"))
    	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
    	  else if (name.toLowerCase().endsWith(".h"))
    	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
    	  else if (name.toLowerCase().endsWith(".sql"))
    	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
    	  else if (name.toLowerCase().endsWith(".js"))
    	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
    	  else if (name.toLowerCase().endsWith(".php") || name.toLowerCase().endsWith(".php5")
    	      || name.toLowerCase().endsWith(".phtml"))
    	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PHP);
    	  else if (name.toLowerCase().endsWith(".html") || name.toLowerCase().endsWith(".htm")
    	      || name.toLowerCase().endsWith(".xhtm") || name.toLowerCase().endsWith(".xhtml"))
    	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
    	  else if (name.toLowerCase().endsWith(".lua"))
    	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LUA);
    	  else if (name.toLowerCase().endsWith(".bat"))
    	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_WINDOWS_BATCH);
    	  else if (name.toLowerCase().endsWith(".pl"))
    	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PERL);
    	  else if (name.toLowerCase().endsWith(".sh"))
    	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_UNIX_SHELL);
    	  else if (name.toLowerCase().endsWith(".css"))
    	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSS);
    	  else if (name.toLowerCase().endsWith(".json"))
    	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
    	  else if (name.toLowerCase().endsWith(".txt"))
    	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
    	  else if (name.toLowerCase().endsWith(".rb"))
    	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_RUBY);
    	  else if (name.toLowerCase().endsWith(".make") || name.toLowerCase().endsWith(".mak"))
    	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_MAKEFILE);
    	  else if (name.toLowerCase().endsWith(".py"))
    	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
    	  else
    	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PROPERTIES_FILE);
    }

    @SuppressWarnings("unused")
	public static void main(String args[]) throws BadLocationException, IOException {
        Window window = new Window();
    }
}