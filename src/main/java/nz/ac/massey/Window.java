package nz.ac.massey;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

public class Window extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
    private RSyntaxTextArea textArea;
    private String savedFile = "";

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

        JMenu searchMenu = new JMenu("Search");
        JMenuItem searchMenuSearch = new JMenuItem("Search");
        searchMenuSearch.addActionListener(this);
        searchMenu.add(searchMenuSearch);

        JMenu viewMenu = new JMenu("View");
        JMenuItem viewMenuPrint = new JMenuItem("Print");
        JMenuItem viewMenuPrintAsPDF = new JMenuItem("Print as PDF");
        viewMenuPrint.addActionListener(this);
        viewMenuPrintAsPDF.addActionListener(this);
        viewMenu.add(viewMenuPrint);
        viewMenu.add(viewMenuPrintAsPDF);

        JMenu manageMenu = new JMenu("Manage");
        JMenuItem manageMenuCut = new JMenuItem("Cut");
        JMenuItem manageMenuCopy = new JMenuItem("Copy");
        JMenuItem manageMenuPaste = new JMenuItem("Paste");
        JMenuItem manageMenuTimeDate = new JMenuItem("Insert Time and Date");
        manageMenuCut.addActionListener(this);
        manageMenuCopy.addActionListener(this);
        manageMenuPaste.addActionListener(this);
        manageMenuTimeDate.addActionListener(this);
        manageMenu.add(manageMenuCut);
        manageMenu.add(manageMenuCopy);
        manageMenu.add(manageMenuPaste);
        manageMenu.add(manageMenuTimeDate);
        JMenu helpMenu = new JMenu("Help");
        JMenuItem helpMenuCloseAll = new JMenuItem("Close All");
        JMenuItem helpMenuAbout = new JMenuItem("About");
        helpMenuCloseAll.addActionListener(this);
        helpMenuAbout.addActionListener(this);
        helpMenu.add(helpMenuCloseAll);
        helpMenu.add(helpMenuAbout);

        menuBar.add(fileMenu);
        menuBar.add(searchMenu);
        menuBar.add(viewMenu);
        menuBar.add(manageMenu);
        menuBar.add(helpMenu);

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
                	String[] returnArray = open.OpenFunction(frame);
                    textArea.setText(returnArray[1]);
                    SetFrame("J&J Pad | " + returnArray[0]);
                } else {
                    Window windowNew = null;
                    try {
                        windowNew = new Window();
                    } catch (BadLocationException | IOException exception) {
                    	exception.printStackTrace();
                    }
                	String[] returnArray = open.OpenFunction(frame);
                    windowNew.textArea.setText(returnArray[1]);
                    SetFrame("J&J Pad | " + returnArray[0]);
                }
                break;
            case "Save":
                Save save = new Save();
                save.SaveFunction(textArea.getText().equals("") ? "" : textArea.getText(), frame);
                savedFile = textArea.getText();
                break;
            case "Search":
                Search search = new Search();
                textArea = search.SearchFunction(textArea);
                break;
            case "Print as PDF":
                PrintToPDF pdfPrinter = new PrintToPDF();
                pdfPrinter.print(textArea.getText().equals("") ? "" : textArea.getText());
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
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                break;
            case "About":
                JOptionPane.showMessageDialog(null, "Hello! Devloped by James Gorman and James Satherley!");
                break;
            default:
                System.out.println("Default output in switch; Error!! Somehow no item was selected. @ line 114");
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