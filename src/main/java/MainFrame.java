import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.jar.Attributes.Name;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.imageio.*;

public class MainFrame extends JFrame {
	// the frame structure
	private Image background;
	private Image stackImage;
	private Image chips;
	private int numOfAI = 7;
	private JPanel northAI1 = new JPanel();
		private int moneyNAI1 = 1000;
	private JPanel northAI2 = new JPanel();
		private int moneyNAI2 = 1000;
	private JPanel northAI3 = new JPanel();
	private String[] opponents = {"Leopold Bloom","Stephen Dedalus","Yelverton Barry","Buck Mulligan","Martin Cunningham","Molly Bloom","Josie Breen"};
		private int moneyNAI3 = 1000;
    private JPanel player = new JPanel();
		private int moneyPlayer = 1000;
    private JPanel pot = new JPanel();
    private JPanel eastAI1 = new JPanel();
		private int moneyEAI1 = 1000;
    private JPanel eastAI2 = new JPanel();
		private int moneyEAI2 = 1000;
    private JPanel westAI1 = new JPanel();
		private int moneyWAI1 = 1000;
    private JPanel westAI2 = new JPanel();
		private int moneyWAI2 = 1000;
    // the player name
    private String userName = new String();
    private Player user = new Player(false, userName, 1000);
    // money in the pot
    private int moneyInPot = 0;
    // new game button
    private JButton[] _newGame = new JButton[1];
    // exit button
    private JButton[] _exit = new JButton[1];
    // dealer
    private JButton[] _dealer = new JButton[1];
    // poker chips
    private int blackChip = 0;
    private int whiteChip = 0;
    private int redChip = 0;
    private int blueChip = 0;
    // AIs array
    private ArrayList<Player> players;
    
	public MainFrame(String playerName) {
		super("Texas Hold'em");
		userName = playerName;
		players = new ArrayList<>();
		for(int j = 0; j<numOfAI; j++) {
		    players.add(new Player(true, opponents[j], 1000));
		}
		initFrame();
        // set the size of the frame
        setSize(1190, 1250);
        // click X to exit
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Must be the last line of this constructor
        setVisible(true);
	}
	
	private void initFrame(){
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(9, 120, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		// load the background image
		getContentPane().setLayout(null);
		try {
			background = ImageIO.read(getClass().getResource("/background.jpg"));
			stackImage = ImageIO.read(getClass().getResource("/stacks.png"));
			chips = ImageIO.read(getClass().getResource("/pokerchips.png"));
		}
		catch (IOException ioex){
		    System.exit(1);
		}
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		if(numOfAI <=3) 
			setNorth();
		if(numOfAI > 3 && numOfAI <=5) {
			setNorth();
			setWest();
		}
        // CENTER
        // Stack chips
        setPokerChips();
        pot.setBackground(new Color(4, 95, 0));
		pot.setBorder(BorderFactory.createLineBorder(Color.white,3));
        pot.setBounds(326,180,630,320);
        JLabel moneyInPotLable = new JLabel();
        moneyInPotLable.setText("Money in the pot: " + String.valueOf(moneyInPot));
        moneyInPotLable.setFont(new Font("Optima", Font.BOLD, 23));
        moneyInPotLable.setForeground(Color.white);
        pot.add(moneyInPotLable);
        getContentPane().add(pot);
        if(numOfAI > 5) {
        	setNorth();
        	setWest();
        	setEast();
        }
        // SOUTH
        player.setBackground(new Color(43, 151, 0));
		player.setBorder(BorderFactory.createLineBorder(Color.white,2));
        player.setBounds(165,506,957,160);
        initAI(player, -1);
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
	
	private void setPokerChips() {
		// poker chips in the pot
		ImageIcon tempIcon = new ImageIcon(chips);
        Image tempImg = tempIcon.getImage();
        Image tempImg2 = tempImg.getScaledInstance(165,108,java.awt.Image.SCALE_SMOOTH);
        tempIcon = new ImageIcon(tempImg2);
        JLabel chips = new JLabel();
        chips.setBounds(775,215,165,108);
        chips.setIcon(tempIcon);
        add(chips);
        JLabel chipsAmt = new JLabel();
        chipsAmt.setText(String.valueOf(blackChip)+"   "+String.valueOf(whiteChip)+"   "+
        		String.valueOf(redChip)+"   "+String.valueOf(blueChip));
        chipsAmt.setFont(new Font("Consolas", Font.PLAIN, 14));
        chipsAmt.setForeground(Color.white);
        chipsAmt.setBounds(810,300,150,50);
        add(chipsAmt);
        // stack image in the player area
        tempIcon = new ImageIcon(stackImage);
        tempImg = tempIcon.getImage();
        tempImg2 = tempImg.getScaledInstance(65,80,java.awt.Image.SCALE_SMOOTH); 
        tempIcon = new ImageIcon(tempImg2);
        JLabel stacks = new JLabel();
        stacks.setBounds(185,580,65,80);
        stacks.setIcon(tempIcon);
        add(stacks);
	}
	
	private void initAI(JPanel panel, int num) {
		String name;
		int money;
		if (num == -1) {
			name = userName;
			money = user.getMoney();
		}
		else {
			name = players.get(num).getName();
			money = players.get(num).getMoney();
		}
		JLabel nameL = new JLabel(name);
		JLabel label = new JLabel();
		label.setText("Balance: " + String.valueOf(money));
		label.setForeground(Color.white);
		panel.add(label);
		panel.add(nameL);
	}
	
	private void setNorth() {
		northAI1.setBackground(new Color(43, 151, 0));
		northAI1.setBorder(BorderFactory.createLineBorder(Color.white,2));
		northAI1.setBounds(95,30,310,130);
		initAI(northAI1,0);
		add(northAI1);
		if(numOfAI == 2 || numOfAI == 3 || numOfAI > 3) {
			northAI2.setBackground(new Color(43, 151, 0));
			northAI2.setBorder(BorderFactory.createLineBorder(Color.white,2));
			northAI2.setBounds(481,30,310,130);
			initAI(northAI2,1);
			add(northAI2);
		}
		if (numOfAI == 3 || numOfAI > 3){
			northAI3.setBackground(new Color(43, 151, 0));
			northAI3.setBorder(BorderFactory.createLineBorder(Color.white,2));
			northAI3.setBounds(866,30,310,130);
			initAI(northAI3,2);
			add(northAI3);
		}
	}
	
	private void setWest() {
		westAI1.setBackground(new Color(43, 151, 0));
		westAI1.setBorder(BorderFactory.createLineBorder(Color.white,2));
		westAI1.setBounds(10,180,310,130);
		initAI(westAI1, 3);
		add(westAI1);
		if(numOfAI == 5 || numOfAI > 5) {
			westAI2.setBackground(new Color(43, 151, 0));
			westAI2.setBorder(BorderFactory.createLineBorder(Color.white,2));
			westAI2.setBounds(10,330,310,130);
			initAI(westAI2, 4);
			add(westAI2);
		}
	}
	
	private void setEast() {
    	eastAI1.setBackground(new Color(43, 151, 0));
    	eastAI1.setBorder(BorderFactory.createLineBorder(Color.white,2));
    	eastAI1.setBounds(962,180,310,130);
    	initAI(eastAI1, 5);
    	add(eastAI1);
    	if(numOfAI == 7) {
    		eastAI2.setBackground(new Color(43, 151, 0));
    		eastAI2.setBorder(BorderFactory.createLineBorder(Color.white,2));
    		eastAI2.setBounds(962,330,310,130);
    		initAI(eastAI2, 6);
    		add(eastAI2);
    	}
	}
}