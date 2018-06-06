import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.*;

public class MainFrame extends JFrame {
	// the frame structure
	
	// TODO: AI names
	private Image background;
	private JPanel northAI1 = new JPanel();
	private JPanel northAI2 = new JPanel();
	private JPanel northAI3 = new JPanel();
    private JPanel player = new JPanel();
    private JPanel pot = new JPanel();
    private JPanel eastAI1 = new JPanel();
    private JPanel eastAI2 = new JPanel();
    private JPanel westAI1 = new JPanel();
    private JPanel westAI2 = new JPanel();
    // the player name
    private String userName = new String();
    // money in the pot
    private int moneyInPot = 0;
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
		northAI1.setBackground(new Color(43, 151, 0));
		northAI1.setBorder(BorderFactory.createLineBorder(Color.gray,2));
        northAI1.setBounds(95,30,310,130);
        add(northAI1);
        northAI2.setBackground(new Color(43, 151, 0));
		northAI2.setBorder(BorderFactory.createLineBorder(Color.gray,2));
        northAI2.setBounds(481,30,310,130);
        add(northAI2);
        northAI3.setBackground(new Color(43, 151, 0));
		northAI3.setBorder(BorderFactory.createLineBorder(Color.gray,2));
        northAI3.setBounds(866,30,310,130);
        add(northAI3);
		// WEST
        westAI1.setBackground(new Color(43, 151, 0));
		westAI1.setBorder(BorderFactory.createLineBorder(Color.gray,2));
        westAI1.setBounds(10,180,310,130);
        add(westAI1);
        westAI2.setBackground(new Color(43, 151, 0));
		westAI2.setBorder(BorderFactory.createLineBorder(Color.gray,2));
        westAI2.setBounds(10,330,310,130);
        add(westAI2);
        // CENTER
        pot.setBackground(new Color(4, 95, 0));
		pot.setBorder(BorderFactory.createLineBorder(Color.gray,3));
        pot.setBounds(326,180,630,320);
        JLabel moneyInPotLable = new JLabel();
        moneyInPotLable.setText("Money in the pot: " + String.valueOf(moneyInPot));
        moneyInPotLable.setFont(new Font("Optima", Font.BOLD, 23));
        moneyInPotLable.setForeground(Color.white);
        pot.add(moneyInPotLable);
        add(pot);
        // EAST
        eastAI1.setBackground(new Color(43, 151, 0));
		eastAI1.setBorder(BorderFactory.createLineBorder(Color.gray,2));
        eastAI1.setBounds(962,180,310,130);
        add(eastAI1);
        eastAI2.setBackground(new Color(43, 151, 0));
		eastAI2.setBorder(BorderFactory.createLineBorder(Color.gray,2));
        eastAI2.setBounds(962,330,310,130);
        add(eastAI2);
        // SOUTH
        player.setBackground(new Color(43, 151, 0));
		player.setBorder(BorderFactory.createLineBorder(Color.gray,2));
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
