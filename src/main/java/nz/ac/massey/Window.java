package nz.ac.massey;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.BadLocationException;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

public class Window extends JFrame implements ActionListener {
	@Serial
    private static final long serialVersionUID = 1L;
	private final JFrame frame;
    private final RSyntaxTextArea textArea;
    private String savedFile = "";
    private int index;
    private final JTextField searchField;     // Creating all needed global variables
    private final JTextField replaceField;
    private final JButton searchForButton;
    private final JButton searchNextButton;
    private final JButton searchExitButton;
    private final JButton searchReplaceAllButton;
    private Search search;

    @SuppressWarnings("deprecation")
	Window() throws BadLocationException, IOException {
        // Defining the frame with a name and image, and defining a menu bar
        frame = new JFrame("J&J Pad | Unnamed");
        frame.setIconImage(ImageIO.read(new File("icon.png")));
        JMenuBar menuBar = new JMenuBar();

        //Tries to set look and feel to default OS look and feel to look better on different os's
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Defining Text area and setting different functions of it
        textArea = new RSyntaxTextArea(30, 60);
        RTextScrollPane scrollPane = new RTextScrollPane(textArea);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setText("");
		textArea.setAutoIndentEnabled(true);
		textArea.setMarkOccurrences(true);
		textArea.setTabSize(4);

        // Get configs and set them to their relative objects
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
        textArea.setFont(new Font(config.fontName,  config.fontStyle, config.fontSize));

        // Creating export submenu for menu bar
        JMenu submenu_exportAsMenu = new JMenu("Export As");
        JMenuItem exportPDF = new JMenuItem("Export as PDF");
        JMenuItem exportODT = new JMenuItem("Export as ODT");
        JMenuItem exportRFT = new JMenuItem("Export as RFT");
        exportPDF.setAccelerator(KeyStroke.getKeyStroke("control alt P"));   // All menu items will be defined with
        exportODT.setAccelerator(KeyStroke.getKeyStroke("control alt O"));   // hotkeys in the form of accelerators
        exportRFT.setAccelerator(KeyStroke.getKeyStroke("control alt R"));   // and are defined with listeners too
        exportPDF.addActionListener(this);
        exportODT.addActionListener(this);
        exportRFT.addActionListener(this);
        submenu_exportAsMenu.add(exportPDF);
        submenu_exportAsMenu.add(exportODT);
        submenu_exportAsMenu.add(exportRFT);

        // Creating import submenu for menu bar
        JMenu submenu_importAsMenu = new JMenu("Import As");
        JMenuItem importPDF = new JMenuItem("Import PDF");
        JMenuItem importODT = new JMenuItem("Import ODT");
        JMenuItem importRFT = new JMenuItem("Import RFT");
        importPDF.setAccelerator(KeyStroke.getKeyStroke("control shift P"));
        importODT.setAccelerator(KeyStroke.getKeyStroke("control shift O"));
        importRFT.setAccelerator(KeyStroke.getKeyStroke("control shift R"));
        importPDF.addActionListener(this);
        importODT.addActionListener(this);
        importRFT.addActionListener(this);
        submenu_importAsMenu.add(importPDF);
        submenu_importAsMenu.add(importODT);
        submenu_importAsMenu.add(importRFT);

        // Creating file menu for menu bar - used for import/export/open/save etc
        JMenu fileMenu = new JMenu("File");
        JMenuItem fileMenuNew = new JMenuItem("New");
        JMenuItem fileMenuOpen = new JMenuItem("Open");
        JMenuItem fileMenuSave = new JMenuItem("Save");
        fileMenuNew.setAccelerator(KeyStroke.getKeyStroke("control N"));
        fileMenuOpen.setAccelerator(KeyStroke.getKeyStroke("control O"));
        fileMenuSave.setAccelerator(KeyStroke.getKeyStroke("control S"));
        fileMenuNew.addActionListener(this);
        fileMenuOpen.addActionListener(this);
        fileMenuSave.addActionListener(this);
        fileMenu.add(fileMenuNew);
        fileMenu.add(fileMenuOpen);
        fileMenu.add(fileMenuSave);
        fileMenu.add(submenu_exportAsMenu);
        fileMenu.add(submenu_importAsMenu);

        // Creating edit menu for menu bar - used for ccp functions and search
        JMenu editMenu = new JMenu("Edit");
        JMenuItem editMenuCut = new JMenuItem("Cut");
        JMenuItem editMenuCopy = new JMenuItem("Copy");
        JMenuItem editMenuPaste = new JMenuItem("Paste");
        JMenuItem editMenuTimeDate = new JMenuItem("Insert Time and Date");
        JMenuItem editMenuSearch = new JMenuItem("Find");
        editMenuCut.setAccelerator(KeyStroke.getKeyStroke("control X"));
        editMenuCopy.setAccelerator(KeyStroke.getKeyStroke("control C"));
        editMenuPaste.setAccelerator(KeyStroke.getKeyStroke("control V"));
        editMenuTimeDate.setAccelerator(KeyStroke.getKeyStroke("control T"));
        editMenuSearch.setAccelerator(KeyStroke.getKeyStroke("control F"));
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

        // Creating view menu for menu bar - used for print and changing the window size
        JMenu viewMenu = new JMenu("View");
        JMenuItem viewMenuPrint = new JMenuItem("Print");
        JMenuItem viewMenuPrintCSOF = new JMenuItem("Change Size of Window");
        viewMenuPrint.setAccelerator(KeyStroke.getKeyStroke("control P"));
        viewMenuPrintCSOF.setAccelerator(KeyStroke.getKeyStroke("control W"));
        viewMenuPrint.addActionListener(this);
        viewMenuPrintCSOF.addActionListener(this);
        viewMenu.add(viewMenuPrint);
        viewMenu.add(viewMenuPrintCSOF);

        // Creating help menu for menu bar - used for close all and about buttons
        JMenu helpMenu = new JMenu("Help");
        JMenuItem helpMenuCloseAll = new JMenuItem("Close All");
        JMenuItem helpMenuAbout = new JMenuItem("About");
        helpMenuCloseAll.setAccelerator(KeyStroke.getKeyStroke("control alt 1"));
        helpMenuAbout.setAccelerator(KeyStroke.getKeyStroke("control I"));
        helpMenuCloseAll.addActionListener(this);
        helpMenuAbout.addActionListener(this);
        helpMenu.add(helpMenuCloseAll);
        helpMenu.add(helpMenuAbout);

        // Creating search buttons and fields
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

        // Adding menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);

        // Adding search functions to menu bar
        menuBar.add(searchForButton);
        menuBar.add(searchField);
        menuBar.add(searchNextButton);
        menuBar.add(replaceField);
        menuBar.add(searchReplaceAllButton);
        menuBar.add(searchExitButton);

        // Hiding search functions (buttons/fields)
        searchField.setVisible(false);
        replaceField.setVisible(false);
        searchForButton.setVisible(false);
        searchNextButton.setVisible(false);
        searchReplaceAllButton.setVisible(false);
        searchExitButton.setVisible(false);

        // Adding everything to frame
        frame.setJMenuBar(menuBar);
        frame.add(scrollPane);
        frame.setSize(config.width, config.height);
        frame.show();

        // Adding close listener to double check if the user wished to exit when not saved
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

    // Giant switch to get each button press and give each an action/case
    @SuppressWarnings("unused")
	@Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "New":
                // Creates new window, catches errors thrown
                try {
                    Window window = new Window();
                } catch (BadLocationException | IOException exception) {
                	exception.printStackTrace();
                }
                break;
            case "Open":
                // Opens new file and checks if it should open in current window or new window
                Open open = new Open();
                if (textArea.getText().equals("")) {
                	String[] returnArray = open.OpenFunction("ui");
                    textArea.setText(returnArray[1]);
                    SetFrame("J&J Pad | " + returnArray[0]);
                } else {
                    Window windowNew = null;
                    try {
                        windowNew = new Window();
                    } catch (BadLocationException | IOException exception) {
                    	exception.printStackTrace();
                    }
                	String[] returnArray = open.OpenFunction("ui");
                    if (windowNew != null) {
                        windowNew.textArea.setText(returnArray[1]);
                    }
                    SetFrame("J&J Pad | " + returnArray[0]);
                }
                break;
            case "Save":
                // Saves file to select location
                Save save = new Save();
                save.SaveFunction(textArea.getText().equals("") ? "" : textArea.getText(), "ui");
                frame.setTitle(save.name);
                savedFile = textArea.getText();
                break;
            case "Find":
                // Sets search functions to visible
                searchField.setVisible(true);
                replaceField.setVisible(true);
                searchForButton.setVisible(true);
                searchNextButton.setVisible(true);
                searchReplaceAllButton.setVisible(true);
                searchExitButton.setVisible(true);
                break;
            case "Search for":
                // Searches for item is search field
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
                // Gets next item from index array in search class
                if(index == search.indexes.size()) index = 0;
                textArea.select(search.indexes.get(index), search.indexes.get(index) + searchField.getText().length());
                index++;
                break;
            case "Replace All":
                // Replaces all occurrences of a certain word with another
                search = new Search();
                search.findIndexes(searchField.getText(), textArea.getText());
                Collections.reverse(search.indexes);
                for (int n : search.indexes) {
                    textArea.replaceRange(replaceField.getText(), n, n + searchField.getText().length());
                }
                break;
            case "Exit Search":
                // Hides search functions
                searchField.setVisible(false);
                replaceField.setVisible(false);
                searchForButton.setVisible(false);
                searchNextButton.setVisible(false);
                searchReplaceAllButton.setVisible(false);
                searchExitButton.setVisible(false);
                break;
            case "Export as PDF":
                // Creates instance of pdfprint class to export pdf
                PrintToPDF pdfPrinter = new PrintToPDF();
                pdfPrinter.print(textArea.getText().equals("") ? "" : textArea.getText());
                break;
            case "Export as ODT":
                // Creates instance of odtprint class to export odt
                PrintToODT odtPrinter = new PrintToODT();
                odtPrinter.print(textArea.getText().equals("") ? "" : textArea.getText());
                break;
            case "Export as RFT":
                // Creates instance of rftprint class to export rft
                PrintToRFT rftPrinter = new PrintToRFT();
                rftPrinter.print(textArea.getText().equals("") ? "" : textArea.getText());
                break;
            case "Import PDF":
                // Creates instance of openpdf class to export pdf
                OpenPDF pdfOpener = new OpenPDF();
                textArea.setText(pdfOpener.OpenFunction());
                break;
            case "Import ODT":
                // Creates instance of openodt class to export odt
                OpenODT odtOpener = new OpenODT();
                textArea.setText(odtOpener.OpenFunction());
                break;
            case "Import RFT":
                // Creates instance of openrft class to export rft
                OpenRFT rftOpener = new OpenRFT();
                textArea.setText(rftOpener.OpenFunction());
                break;
            case "Change Size of Window":
                // Creates instance of change dims and sends away current width and height in case of error
                ChangeDims d = new ChangeDims();
                int[] tempArray = ChangeDims.getNewDims(frame.getWidth(), frame.getHeight());
                frame.setSize(new Dimension(tempArray[0], tempArray[1]));
                break;
            case "Print":
                // Tries to print the contents of textarea
				try {
					textArea.print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}            
            	break;
            case "Cut":
                // Performs cut function on selected text
                textArea.cut();
                break;
            case "Copy":
                // Performs copy function on selected text
                textArea.copy();
                break;
            case "Paste":
                // Performs paste function on selected text
                textArea.paste();
                break;
            case "Insert Time and Date":
                // Inserts text and date to the top of the document
            	DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            	DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
                textArea.setText("Date: "+ date.format(LocalDateTime.now()) + ", Time: " + time.format(LocalDateTime.now()) + "\n" + textArea.getText());
                break;
            case "Close All":
                // Closes all windows
            	System.exit(0);
                break;
            case "About":
                // Shows some info about programmers
                JOptionPane.showMessageDialog(null, "Hello! Devloped by James Gorman and James Satherley!");
                break;
            default:
                // default output, should never be possible
                System.out.println("Default output in switch; Error!! Somehow no item was selected");
        }
    }
    
    void SetFrame(String name) {
    	frame.setTitle(name);
    	// Sets frame to name, finds end of name and sets syntax colours
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

    // Creates window as psvm
    @SuppressWarnings("unused")
	public static void main(String args[]) throws BadLocationException, IOException {
        Window window = new Window();
    }
}