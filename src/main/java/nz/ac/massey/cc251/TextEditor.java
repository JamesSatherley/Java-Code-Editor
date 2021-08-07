package nz.ac.massey.cc251;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;


public class TextEditor extends JFrame{
	JTextArea textArea;
	JScrollPane scrollPane;
	
	TextEditor() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Text Editor");
		this.setSize(1000, 1000);
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Arial", Font.PLAIN,70));
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(950, 950));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		this.add(scrollPane);
		this.setVisible(true);
	}
	
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new TextEditor();

	}

}
