package nz.ac.massey;

import javax.swing.JOptionPane;

public class ChangeDims {
	public static int[] getNewDims(int i, int j) {
		// gets inputs from user, checks if they're integers, if not asks user again, otherwise sends back to window
        String widthBox = (String)JOptionPane.showInputDialog("Enter width");
        String heightBox = (String)JOptionPane.showInputDialog("Enter height");
        try {
	        if (!widthBox.equals(null) && !heightBox.equals(null)) {
	            widthBox = checkNumberAndRange(widthBox, "Enter width that is a number between 100 and 4000 please");
	            heightBox = checkNumberAndRange(heightBox, "Enter height that is a number between 100 and 4000 please");
	        } 
        } catch (NullPointerException e) {
        	return new int[] {i, j};
        }
		return new int[] {Integer.parseInt(widthBox),Integer.parseInt(heightBox)};
	}
	
	public static String checkNumberAndRange(String num, String text) {
        while (true) {
	        try {
	        	Integer.parseInt(num);
	        	if(Integer.parseInt(num)<100 || Integer.parseInt(num)>4000) {
	        		num="no.";
	        	}
	        	Integer.parseInt(num);
	        	break;
	        } catch (NumberFormatException e) {
	        	num = (String)JOptionPane.showInputDialog(text);
	        }
        }
		return num;
	}

}
