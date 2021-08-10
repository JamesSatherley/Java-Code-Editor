package nz.ac.massey.cc251;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import nz.ac.massey.Open;
import nz.ac.massey.Save;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.Panel;
import java.awt.TextArea;

public class Main extends JFrame {

	private JPanel contentPane;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	private final Action action_3 = new SwingAction_3();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	JTextArea txtrTextHere;
	private final Action action_4 = new SwingAction_4();
	private final Action action_5 = new SwingAction_5();
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New");
		mntmNewMenuItem.setAction(action);
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Open");
		mntmNewMenuItem_1.setAction(action_1);
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Save");
		mntmNewMenuItem_2.setAction(action_2);
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_1 = new JMenu("Tools");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Search");
		mntmNewMenuItem_4.setAction(action_4);
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_2 = new JMenu("View");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("???");
		mnNewMenu_2.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_3 = new JMenu("Manage");
		menuBar.add(mnNewMenu_3);
		
		JMenu mnNewMenu_5 = new JMenu("Print as");
		mnNewMenu_3.add(mnNewMenu_5);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Print as txt");
		mnNewMenu_5.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Print as pdf");
		mnNewMenu_5.add(mntmNewMenuItem_7);
		
		JMenu mnNewMenu_4 = new JMenu("Help");
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Close All");
		mnNewMenu_4.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("About");
		mntmNewMenuItem_9.setAction(action_3);
		mnNewMenu_4.add(mntmNewMenuItem_9);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("T&D");
		mntmNewMenuItem_6.setAction(action_5);
		mnNewMenu_4.add(mntmNewMenuItem_6);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		txtrTextHere = new JTextArea();
		contentPane.add(txtrTextHere, BorderLayout.CENTER);
		
		JScrollBar scrollBar = new JScrollBar();
		contentPane.add(scrollBar, BorderLayout.EAST);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "New");
			putValue(SHORT_DESCRIPTION, "Creates new frame");
		}
		public void actionPerformed(ActionEvent e) {
			main(null);
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Open");
			putValue(SHORT_DESCRIPTION, "Opens txt in editor");
		}
		public void actionPerformed(ActionEvent e) {
			Open openPane = new Open(txtrTextHere);
		}
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Save");
			putValue(SHORT_DESCRIPTION, "Saves as txt");
		}
		public void actionPerformed(ActionEvent e) {
			Save savePanel = new Save(txtrTextHere.getText());
			
			

		}
	}
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "About");
			putValue(SHORT_DESCRIPTION, "Opens information about Devlopers");
		}
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Hello! Devloped by James Gorman and James Satherley!");
		}
	}
	private class SwingAction_4 extends AbstractAction {
		public SwingAction_4() {
			putValue(NAME, "Search");
			putValue(SHORT_DESCRIPTION, "Search box");
		}
		public void actionPerformed(ActionEvent e) {
			String searchBox = (String)JOptionPane.showInputDialog("Search Text:");
			final int l1 = txtrTextHere.getText().indexOf(searchBox);
			final int l2 = searchBox.length();

			if (l1 == -1) {
			    JOptionPane.showMessageDialog(null, "Search Value Not Found");
			} else {
				txtrTextHere.select(l1, l2+l1);
			}
		}
	}
	private class SwingAction_5 extends AbstractAction {
		public SwingAction_5() {
			putValue(NAME, "T&D");
			putValue(SHORT_DESCRIPTION, "Puts time and date at the start of your document");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
