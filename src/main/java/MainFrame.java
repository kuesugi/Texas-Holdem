import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class MainFrame extends JFrame {
	public MainFrame() {
		super("Texas Holdem");
		
		// name of the frame
	    JFrame _frame = new JFrame("Texas Holdem");
	    // new game button
	    JButton[] _newGame = new JButton[1];
	    // exit button
	    JButton[] _exit = new JButton[1];
	    // dealer
	    JButton[] _dealer = new JButton[1];
	    /*// Card images
	    Image 2C; Image 2D; Image 2H; Image 2S; Image AC; Image AD; Image AH; Image AS;
	    Image 3C; Image 3D; Image 3H; Image 3S; Image JC; Image JD; Image JH; Image JS;
	    Image 4C; Image 4D; Image 4H; Image 4S; Image QC; Image QD; Image QH; Image QS;
	    Image 5C; Image 5D; Image 5H; Image 5S; Image KC; Image KD; Image KH; Image KS;
	    Image 6C; Image 6D; Image 6H; Image 6S; Image J1; Image J2; Image back;
	    Image 7C; Image 7D; Image 7H; Image 7S; Image 8C; Image 8D; Image 8H; Image 8S;
	    Image 9C; Image 9D; Image 9H; Image 9S; Image 10C; Image 10D; Image 10H; Image 10S;*/

        // set the size of the frame
        _frame.setSize(1000,1000);
        // click X to exit
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Must be the last line of this constructor
        _frame.setVisible(true);
	}
}
