import java.awt.*;
import java.awt.event.*;
import java.awt.List;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.jar.Attributes.Name;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.imageio.*;

@SuppressWarnings("unchecked")

public class MainFrame extends JFrame {
	// the frame structure
	private Image background;
	private Image stackImage;
	private Image chips;
	private Image back;
	// Card images
    private Image C2; 
	private int numOfAI;
	private int cardCount = 51;
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
    private static ArrayList<Card> deck;
    
    
	public MainFrame(String playerName, int num) {
		super("Texas Hold'em");
		userName = playerName;
		numOfAI = num;
		players = new ArrayList<>();
		deck = new ArrayList();
		for(int j = 0; j < numOfAI; j++) {
		    players.add(new Player(true, opponents[j], 1000));
		}
		buildDeck();
		shuffle();
		initFrame();
        // set the size of the frame
        setSize(1190, 1250);
        // click X to exit
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Must be the last line of this constructor
        setVisible(true);
	}

	public static void shuffle() {
        Collections.shuffle(deck);
    }
    
    //buildDeck populates deck with card objects
    public static void buildDeck() {
        // # of suits
        for(int i = 1; i < 5; i++) {
            // # of ranks
            for(int j = 1; j < 14; j++) {
                Card c = new Card(j,i);
                c.setCardIndex(i,j);
                deck.add(c);
            }
        }
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
			back = ImageIO.read(getClass().getResource("/back.png"));
			Image clubs2 = ImageIO.read(getClass().getResource("/clubs2.png"));
			Image clubs3 = ImageIO.read(getClass().getResource("/clubs3.png"));
			Image clubs4 = ImageIO.read(getClass().getResource("/clubs4.png"));
			Image clubs5 = ImageIO.read(getClass().getResource("/clubs5.png"));
			Image clubs6 = ImageIO.read(getClass().getResource("/clubs6.png"));
			Image clubs7 = ImageIO.read(getClass().getResource("/clubs7.png"));
			Image clubs8 = ImageIO.read(getClass().getResource("/clubs8.png"));
			Image clubs9 = ImageIO.read(getClass().getResource("/clubs9.png"));
			Image clubs10 = ImageIO.read(getClass().getResource("/clubs10.png"));
			Image clubs11 = ImageIO.read(getClass().getResource("/clubs11.png"));
			Image clubs12 = ImageIO.read(getClass().getResource("/clubs12.png"));
			Image clubs13 = ImageIO.read(getClass().getResource("/clubs13.png"));
			Image clubs1 = ImageIO.read(getClass().getResource("/clubs1.png"));
			Image diamonds2 = ImageIO.read(getClass().getResource("/diamonds2.png"));
			Image diamonds3 = ImageIO.read(getClass().getResource("/diamonds3.png"));
			Image diamonds4 = ImageIO.read(getClass().getResource("/diamonds4.png"));
			Image diamonds5 = ImageIO.read(getClass().getResource("/diamonds5.png"));
			Image diamonds6 = ImageIO.read(getClass().getResource("/diamonds6.png"));
			Image diamonds7 = ImageIO.read(getClass().getResource("/diamonds7.png"));
			Image diamonds8 = ImageIO.read(getClass().getResource("/diamonds8.png"));
			Image diamonds9 = ImageIO.read(getClass().getResource("/diamonds9.png"));
			Image diamonds10 = ImageIO.read(getClass().getResource("/diamonds10.png"));
			Image diamonds11 = ImageIO.read(getClass().getResource("/diamonds11.png"));
			Image diamonds12 = ImageIO.read(getClass().getResource("/diamonds12.png"));
			Image diamonds13 = ImageIO.read(getClass().getResource("/diamonds13.png"));
			Image diamonds1 = ImageIO.read(getClass().getResource("/diamonds1.png"));
			Image spades2 = ImageIO.read(getClass().getResource("/spades2.png"));
			Image spades3 = ImageIO.read(getClass().getResource("/spades3.png"));
			Image spades4 = ImageIO.read(getClass().getResource("/spades4.png"));
			Image spades5 = ImageIO.read(getClass().getResource("/spades5.png"));
			Image spades6 = ImageIO.read(getClass().getResource("/spades6.png"));
			Image spades7 = ImageIO.read(getClass().getResource("/spades7.png"));
			Image spades8 = ImageIO.read(getClass().getResource("/spades8.png"));
			Image spades9 = ImageIO.read(getClass().getResource("/spades9.png"));
			Image spades10 = ImageIO.read(getClass().getResource("/spades10.png"));
			Image spades11 = ImageIO.read(getClass().getResource("/spades11.png"));
			Image spades12 = ImageIO.read(getClass().getResource("/spades12.png"));
			Image spades13 = ImageIO.read(getClass().getResource("/spades13.png"));
			Image spades1 = ImageIO.read(getClass().getResource("/spades1.png"));
			Image hearts2 = ImageIO.read(getClass().getResource("/hearts2.png"));
			Image hearts3 = ImageIO.read(getClass().getResource("/hearts3.png"));
			Image hearts4 = ImageIO.read(getClass().getResource("/hearts4.png"));
			Image hearts5 = ImageIO.read(getClass().getResource("/hearts5.png"));
			Image hearts6 = ImageIO.read(getClass().getResource("/hearts6.png"));
			Image hearts7 = ImageIO.read(getClass().getResource("/hearts7.png"));
			Image hearts8 = ImageIO.read(getClass().getResource("/hearts8.png"));
			Image hearts9 = ImageIO.read(getClass().getResource("/hearts9.png"));
			Image hearts10 = ImageIO.read(getClass().getResource("/hearts10.png"));
			Image hearts11 = ImageIO.read(getClass().getResource("/hearts11.png"));
			Image hearts12 = ImageIO.read(getClass().getResource("/hearts12.png"));
			Image hearts13 = ImageIO.read(getClass().getResource("/hearts13.png"));
			Image hearts1 = ImageIO.read(getClass().getResource("/hearts1.png"));
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

        // space holding the five cards
        JPanel fiveCards = new JPanel();
        fiveCards.setBackground(new Color(4, 95, 0));
        fiveCards.setBorder(BorderFactory.createLineBorder(Color.white,2));
        fiveCards.setBounds(336,220,430,270);
        add(fiveCards);
        
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
		
	///////////////////////////////////////////////////////////start game play here
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
		ImageIcon tempIcon = new ImageIcon(back);
        Image tempImg = tempIcon.getImage();
        Image tempImg2 = tempImg.getScaledInstance(65,80,java.awt.Image.SCALE_SMOOTH); 
        tempIcon = new ImageIcon(tempImg2);
		String name;
		int money;
		Image cardImg;
		JLabel card1Display = new JLabel();
		JLabel card2Display = new JLabel();
		Card c1 = deck.get(cardCount--);
		Card c2 = deck.get(cardCount--);
		
		if (num != -1){
			name = players.get(num).getName();
			money = players.get(num).getMoney();
			players.get(num).setCard1(c1);
			players.get(num).setCard2(c2);
			card1Display.setIcon(tempIcon);
			card2Display.setIcon(tempIcon);
			panel.add(card1Display);
			panel.add(card2Display);
		}
		else{
			Card userC1 = new Card(c1.getRank(), c1.getSuit());
			Card userC2 = new Card(c2.getRank(), c2.getSuit());
			name = userName;
			money = user.getMoney();
			user.setCard1(userC1);
			user.setCard2(userC2);
			try{
				cardImg = ImageIO.read(getClass().getResource(c1.getIndex()+".png"));
				tempIcon = new ImageIcon(cardImg);
	        	tempImg = tempIcon.getImage();
	        	tempImg2 = tempImg.getScaledInstance(65,80,java.awt.Image.SCALE_SMOOTH); 
	        	tempIcon = new ImageIcon(tempImg2);
	        	card1Display.setIcon(tempIcon);

	        	cardImg = ImageIO.read(getClass().getResource(c2.getIndex()+".png"));
				tempIcon = new ImageIcon(cardImg);
	        	tempImg = tempIcon.getImage();
	        	tempImg2 = tempImg.getScaledInstance(65,80,java.awt.Image.SCALE_SMOOTH); 
	        	tempIcon = new ImageIcon(tempImg2);
	        	card2Display.setIcon(tempIcon);

	        	panel.add(card1Display);
	        	panel.add(card2Display);
			}
			catch (IOException ioex){
			}
			
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
