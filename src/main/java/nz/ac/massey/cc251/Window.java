package nz.ac.massey.cc251;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {
    private String name = "JPad";
    private JFrame frame;

    private JMenuBar menuBar;
    private JTextArea textArea;
    private JTextArea lines;

    Window() {
        String fileName = "Unnamed";
        frame = new JFrame(fileName + " | " + name);
        menuBar = new JMenuBar();
        textArea = new JTextArea();

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
        frame.add(textArea);
        JScrollPane scrollBar = new JScrollPane(textArea);
        frame.add(scrollBar);
        frame.setSize(500, 500);
        frame.show();
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "New":
                Window window = new Window();
                break;
            case "Open":
                //TODO
                break;
            case "Save":
                //TODO
                break;
            case "Search":
                //TODO
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
                //TODO
                break;
            case "Close All":
                //TODO
                break;
            case "About":
                JOptionPane.showMessageDialog(null, "Hello! Devloped by James Gorman and James Satherley!");
                break;
            default:
                System.out.println("Default output in switch; Error!! Somehow no item was selected. @ line 114");
        }
    }
    
    public static void main(String args[])
    {
        Window window = new Window();
    }
}