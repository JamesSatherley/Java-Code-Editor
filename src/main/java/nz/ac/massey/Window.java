package nz.ac.massey;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

public class Window extends JFrame implements ActionListener {
    private JFrame frame;
    private TextArea textArea;

    Window() throws BadLocationException {
        String fileName = "Unnamed";
        JFrame frame = new JFrame("J&J Pad | " + fileName);
        JMenuBar menuBar = new JMenuBar();
        RSyntaxTextArea textArea = new RSyntaxTextArea(30, 60);
        RTextScrollPane scrollPane = new RTextScrollPane(textArea);

        textArea.setCaretColor(Color.white);
        textArea.setBackground(Color.darkGray);
        textArea.setForeground(Color.lightGray);
        textArea.setSelectionColor(Color.black);
        textArea.setCurrentLineHighlightColor(Color.black);

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
        JMenuItem viewMenuItem = new JMenuItem("???");
        viewMenuItem.addActionListener(this);
        viewMenu.add(viewMenuItem);

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "New":
                try {
                    Window window = new Window();
                } catch (BadLocationException badLocationException) {
                    badLocationException.printStackTrace();
                }
                break;
            case "Open":
                Open open = new Open();
                if (textArea.getText().equals("")) {
                    textArea.setText(open.OpenFunction());
                } else {
                    Window windowNew = null;
                    try {
                        windowNew = new Window();
                    } catch (BadLocationException badLocationException) {
                        badLocationException.printStackTrace();
                    }
                    windowNew.textArea.setText(open.OpenFunction());
                }
                break;
            case "Save":
                Save save = new Save();
                save.SaveFunction(textArea.getText());
                break;
            case "Search":
                Search search = new Search();
                textArea = search.SearchFunction(textArea);
                break;
            case "???":
                //TODO
                break;
            case "Cut":
                //TODO
                break;
            case "Copy":
                //TODO
                break;
            case "Paste":
                //TODO
                break;
            case "Insert Time and Date":
                textArea.setText(LocalDateTime.now()+ "\n" + textArea.getText());
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

    public static void main(String args[]) throws BadLocationException {
        Window window = new Window();
    }
}