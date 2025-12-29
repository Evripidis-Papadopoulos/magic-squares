package GUI;
import javax.swing.*;
public class GUI_1 {

	public static void main(String[] args) {
		{
	        JFrame frame = new JFrame("GUI Example");
	        frame.setSize(400, 300);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        JButton button = new JButton("Πάτα με!");
	        frame.add(button); // προσθέτουμε το κουμπί στο παράθυρο

	        frame.setVisible(true);
	    }
	}

}
