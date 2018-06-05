import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.*;

public class MainFrame extends JFrame {
	// the frame structure
	// TODO: AI names
	private Image background;
	private JButton northAI1 = new JButton("1");
	private JButton northAI2 = new JButton("2");
	private JButton northAI3 = new JButton("3");
    private JButton player = new JButton("Player");
    private JButton pot = new JButton("Pot");
    private JButton eastAI1 = new JButton("E");
    private JButton eastAI2 = new JButton("E");
    private JButton westAI1 = new JButton("W");
    private JButton westAI2 = new JButton("W");
    // the player name
    private String userName = new String();
    // money in the pot
    private double moneyInPot = 0;
    // new game button
    private JButton[] _newGame = new JButton[1];
    // exit button
    private JButton[] _exit = new JButton[1];
    // dealer
    private JButton[] _dealer = new JButton[1];
		
	public MainFrame() {
		super("Texas Holdem");
		initFrame();
        // set the size of the frame
        setSize(1190, 1250);
        // click X to exit
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Must be the last line of this constructor
        setVisible(true);
	}
	
	private void initFrame(){
		// load the background image
		getContentPane().setLayout(null);
		JLabel label = new JLabel("");
		try {
			background = ImageIO.read(getClass().getResource("/background.jpg"));
		}
		catch (IOException ioex){
		    System.exit(1);
		}
		label.setIcon(new ImageIcon(background));
		label.setBounds(0, 0, 1190, 1250);	// TODO: not full windowed background
		//getContentPane().add(label);
		
		// JPanel mainPanel = new JPanel();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		// NORTH
        northAI1.setBounds(95,30,310,130);
        add(northAI1);
        northAI2.setBounds(481,30,310,130);
        add(northAI2);
        northAI3.setBounds(866,30,310,130);
        add(northAI3);
		// WEST
        westAI1.setBounds(10,180,310,130);
        add(westAI1);
        westAI2.setBounds(10,330,310,130);
        add(westAI2);
        // CENTER
        pot.setBounds(326,180,630,320);
        add(pot);
        // EAST
        eastAI1.setBounds(962,180,310,130);
        add(eastAI1);
        eastAI2.setBounds(962,330,310,130);
        add(eastAI2);
        // SOUTH
        player.setBounds(165,506,957,160);
        add(player);
		
	    /*// Card images
	    Image 2C; Image 2D; Image 2H; Image 2S; Image AC; Image AD; Image AH; Image AS;
	    Image 3C; Image 3D; Image 3H; Image 3S; Image JC; Image JD; Image JH; Image JS;
	    Image 4C; Image 4D; Image 4H; Image 4S; Image QC; Image QD; Image QH; Image QS;
	    Image 5C; Image 5D; Image 5H; Image 5S; Image KC; Image KD; Image KH; Image KS;
	    Image 6C; Image 6D; Image 6H; Image 6S; Image J1; Image J2; Image back;
	    Image 7C; Image 7D; Image 7H; Image 7S; Image 8C; Image 8D; Image 8H; Image 8S;
	    Image 9C; Image 9D; Image 9H; Image 9S; Image 10C; Image 10D; Image 10H; Image 10S;*/

	}
}
