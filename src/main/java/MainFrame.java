import java.awt.*;
import java.awt.event.*;
import java.awt.List;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.jar.Attributes.Name;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.imageio.*;

@SuppressWarnings("unchecked")

public class MainFrame extends JFrame{
	// to convert and display images
	private Image stackImage;
	private Image chips;
	private Image back;
	private int numOfAI;
	private int cardCount = 51;
	// the frame structures
	private JPanel northAI1 = new JPanel();
	private JPanel northAI2 = new JPanel();
	private JPanel northAI3 = new JPanel();
	private String[] opponents = {"Leopold Bloom","Stephen Dedalus","Yelverton Barry","Buck Mulligan","Martin Cunningham","Molly Bloom","Josie Breen"};
    private JPanel p = new JPanel(new GridLayout(2,3,2,2));
    private JPanel p2 = new JPanel(new GridLayout(2,4,2,2));
    private JPanel p3 = new JPanel();
	private JPanel player = new JPanel();

    private JPanel pot = new JPanel();
    private JPanel eastAI1 = new JPanel();
    private JPanel eastAI2 = new JPanel();
    private JPanel westAI1 = new JPanel();
    private JPanel westAI2 = new JPanel();
    private JPanel fiveCards = new JPanel();
    private Hand centerHand = new Hand();
    // TODO: add some 10, 50, 100, clear buttons
    private JButton betButton = new JButton("Bet");
    private JButton bet10Button = new JButton ("$10");
    private JButton bet50Button = new JButton ("$50");
    private JButton bet100Button = new JButton ("$100");
    private JButton clearButton = new JButton ("CLEAR");

    private JButton callButton = new JButton("Call");
    private JButton foldButton = new JButton("Fold");
    private int balance = 1000;
    private int betAmount = 0;
    private JTextField betAmt = new JTextField("$"+betAmount);
    // the player name
    private String userName = new String();
    private Player user = new Player(false, userName, 1000);
    // money in the pot
    private int moneyInPot = 0;
    // poker chips
    private int blackChip, whiteChip, redChip, blueChip;
    // AIs array
    private ArrayList<Player> players;
    private static ArrayList<Card> deck;
    // initialize the log file
    PrintWriter logWriter = null;
    int gameRound = 0;
    boolean allFold = false;
    
    /**
	 * Constructor
	 */
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
		try {
			initLog(playerName, num);
		} catch (Exception e) {}
		initFrame();
        // set the size of the frame
        setSize(1190, 1250);
        // click X to exit
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Must be the last line of this constructor
        setVisible(true);
	}

	/**
	 * Shuffle the deck
	 */
	private static void shuffle() {
        Collections.shuffle(deck);
    }
    
	/**
	 * Populates deck with card objects
	 */
    private static void buildDeck() {
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

    /**
	 * Initialize the logWriter and log
	 * the basic info about the game
	 */
    private void initLog(String name, int num) throws FileNotFoundException{
    	logWriter = new PrintWriter("log.txt");
    	String startTime = new SimpleDateFormat("dd MMMM yyyy  -  HH : mm").format(Calendar.getInstance().getTime());
    	logWriter.println("       * * * Game Log * * *\n" + "- Game started " + startTime);
    	logWriter.print("- Username: "+ name + "\n- Players: " + num + "\n  ");
    	for (int i = 0; i < num-1; i++)
    		logWriter.print(opponents[i]+", ");
    	logWriter.println(opponents[num-1] + "\n\nHand 1:\nCards Dealt:");
    	// cards dealt are recorded when initialize (AI)players
    }

    /**
	 * Construct the main frame
	 */
	private void initFrame(){
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(9, 120, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		// load the background image
		getContentPane().setLayout(null);
		try {
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
        centerHand.addCard(deck.get(cardCount--));
		centerHand.addCard(deck.get(cardCount--));
		centerHand.addCard(deck.get(cardCount--));
        initAI(player,-1,0);
        add(player);

        
		betButton.setEnabled(false);
	
        
        
        p.setBackground(new Color(43, 151, 0));
        p2.setBackground(new Color(43, 151, 0));
        p3.setBackground(new Color(43, 151, 0));


        p.add(betButton);
        p.add(callButton);
        p2.add(bet10Button);
        p2.add(bet50Button);
        p2.add(bet100Button);
        p2.add(clearButton);
        
        
        player.add(p);
        p.add(p2);
        betAmt.setPreferredSize( new Dimension(50, 24) );

        p3.add(betAmt);
        betAmt.setEditable(false);
        p2.add(p3);
        p.add(foldButton);

        p.setVisible(true);
        p2.setVisible(true);
        p3.setVisible(true);

        gameStart();
        
        
	}
	
	/**
	 * Display the center hand cards
	 */
	private void displayCenterCards(Hand centerHand, int round){
		if(round == -1) return;
		ImageIcon tempIcon;
        Image tempImg;
        Image tempImg2;
        Image cardImg;

		JLabel card1Display = new JLabel();
		JLabel card2Display = new JLabel();
		JLabel card3Display = new JLabel();
		JLabel card4Display = new JLabel();
		JLabel card5Display = new JLabel();

		try{
			if(round == 1){
				Card c1 = centerHand.getCard(0);
				Card c2 = centerHand.getCard(1);
				Card c3 = centerHand.getCard(2);

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

	        	cardImg = ImageIO.read(getClass().getResource(c3.getIndex()+".png"));
				tempIcon = new ImageIcon(cardImg);
	        	tempImg = tempIcon.getImage();
	        	tempImg2 = tempImg.getScaledInstance(65,80,java.awt.Image.SCALE_SMOOTH); 
	        	tempIcon = new ImageIcon(tempImg2);
	        	card3Display.setIcon(tempIcon);
	        	
	        	fiveCards.add(card1Display);
        		fiveCards.add(card2Display);
        		fiveCards.add(card3Display);
        		fiveCards.revalidate();
			}
			else if (round == 2){
				Card c4 = centerHand.getCard(3);
	        	cardImg = ImageIO.read(getClass().getResource(c4.getIndex()+".png"));
				tempIcon = new ImageIcon(cardImg);
	        	tempImg = tempIcon.getImage();
	        	tempImg2 = tempImg.getScaledInstance(65,80,java.awt.Image.SCALE_SMOOTH); 
	        	tempIcon = new ImageIcon(tempImg2);
	        	card4Display.setIcon(tempIcon);
	        	fiveCards.add(card4Display);
	        	fiveCards.revalidate();
	        }
	        else{
				Card c5 = centerHand.getCard(4);
	        	cardImg = ImageIO.read(getClass().getResource(c5.getIndex()+".png"));
				tempIcon = new ImageIcon(cardImg);
	        	tempImg = tempIcon.getImage();
	        	tempImg2 = tempImg.getScaledInstance(65,80,java.awt.Image.SCALE_SMOOTH); 
	        	tempIcon = new ImageIcon(tempImg2);
	        	card5Display.setIcon(tempIcon);
	        	fiveCards.add(card5Display);
	        	fiveCards.revalidate();
	        }
			pot.revalidate();
		}
		catch (IOException ioex){
		}
	}

	
	/**
	 * Handle transitions and buttons
	 */
	private void transition() {
		if(gameRound == 0) { // flop round
			displayCenterCards(centerHand, 1);
			centerHand.addCard(deck.get(cardCount--));
			gameRound++;
		}
		else if(gameRound == 1) {
			displayCenterCards(centerHand, 2);
			centerHand.addCard(deck.get(cardCount--));
			gameRound++;
		}
		else if(gameRound == 2) {
			displayCenterCards(centerHand, 3);
			gameRound++;
		}
		else if (gameRound == 3){
			// TODO: move money to the winner's stack
			// display AIs' cards
			if(!allFold) {
				if(numOfAI == 1)	initAI(northAI1, 1, 1);
				else if(numOfAI == 2) { initAI(northAI1,0,1); initAI(northAI2,1,1);}
				else if(numOfAI == 3) { initAI(northAI1,0,1); initAI(northAI2,1,1); 
					initAI(northAI3,2,1);}
				else if(numOfAI == 4) { initAI(northAI1,0,1); initAI(northAI2,1,1); 
					initAI(northAI3,2,1); initAI(westAI1,3,1);}
				else if(numOfAI == 5) { initAI(northAI1,0,1); initAI(northAI2,1,1);
					initAI(northAI3,2,1); initAI(westAI1,3,1); initAI(westAI2,4,1);}
				else if(numOfAI == 6) { initAI(northAI1,0,1); initAI(northAI2,1,1); 
					initAI(northAI3,2,1); initAI(westAI1,3,1); initAI(westAI2,4,1); 
					initAI(eastAI1,5,1);}
				else { initAI(northAI1,0,1); initAI(northAI2,1,1); initAI(northAI3,2,1);
					initAI(westAI1,3,1); initAI(westAI2,4,1); initAI(eastAI1,5,1);
					initAI(eastAI2,6,1);}
			}
			// log the end time of the game and close the file writing
			String endTime = new SimpleDateFormat("dd MMMM yyyy  -  HH : mm").format(Calendar.getInstance().getTime());
	    	logWriter.println("- Game ends " + endTime);
	    	logWriter.close();
	    	JPanel result = new JPanel();
		}
		else return;
	}
	
	/**
	 * Start a game and go into three transitions
	 */
	private void gameStart(){
		boolean ifUserFold = false;
		// BUTTONS:
		bet10Button.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
        	if (balance >= betAmount+10) {
        	betAmount+=10;
            betAmt.setText("$"+betAmount);
			betButton.setEnabled(true);
        	}
        	else
        		bet10Button.setEnabled(false);
          }
        });
        
        bet50Button.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
          	if (balance >= betAmount+50) {
        	betAmount+=50;
            betAmt.setText("$"+betAmount);
			betButton.setEnabled(true);
          	}
        	else
        		bet50Button.setEnabled(false);
          }
        });
        
        bet100Button.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
        	// hide the frame
        	if (balance >= betAmount+100) {
        	betAmount+=100;
            betAmt.setText("$"+betAmount);
			betButton.setEnabled(true);
        	}
        	else
        		bet100Button.setEnabled(false);
          }
        });
		
        clearButton.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
        	// hide the frame
        	betAmount=0;
            betAmt.setText("$"+betAmount);
			betButton.setEnabled(false);
        	bet10Button.setEnabled(true);
        	bet50Button.setEnabled(true);
        	bet100Button.setEnabled(true);

          }
        });
		betButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(betAmount <= user.getStack()) {
					user.setStack(user.getStack()-betAmount);
					moneyInPot += betAmount;
					betAmount = 0;
					allFold = true; 
					// NOTE: fold button is disabled here because all AIs fold after the user bets
					foldButton.setEnabled(false);
					
					transition();
				}
				
			}
		});
		foldButton.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
        	player.removeAll();
        	JLabel label = new JLabel();
			label.setText("Balance: " + balance);
			label.setForeground(Color.white);
			add(label);
        	player.repaint(); player.revalidate();
          }
        });
		
		int winnerIndex = -1;
		//centerHand = new Hand();
		while (gameRound <= 3 && ifUserFold) {
			if (betAmount < 0 || betAmount == 0)
				betButton.setEnabled(false);
			else
				betButton.setEnabled(true);
		}
		// cards dealt are recorded when initialize players
		//flop: the three cards
		logWriter.println("Hand 2:\n");
		
		//turn
		logWriter.println("Hand 3:\n");
		//river
		logWriter.println("Hand 4:\n");
		if(winnerIndex == -1) {
			logWriter.print("You win with " + user.getCard1().suitToString(user.getCard1().getSuit()) +" "+
					user.getCard1().getRank() + ", " + user.getCard2().suitToString(user.getCard2().getSuit()) +
					" " + user.getCard2().getRank() + ", "  ); //+ ????????
			logWriter.println("You win $" + user.getMoney()); 
		}
		else{
			logWriter.println("The winner is " + players.get(winnerIndex) + ".");
			logWriter.print(players.get(winnerIndex) + "wins $" + players.get(winnerIndex).getMoney());
		}
		
		
	}
	
	/**
	 * Add the poker chips images
	 */
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
	
	private void displayAICards(int i, JPanel panel, JLabel c1Dis, JLabel c2Dis) {
		Card c1 = players.get(i).getCard1();
		Card c2 = players.get(i).getCard2();
		try {
        	Image cardImg = ImageIO.read(getClass().getResource(c1.getIndex()+".png"));
			ImageIcon tempIcon = new ImageIcon(cardImg);
        	Image tempImg = tempIcon.getImage();
        	Image tempImg2 = tempImg.getScaledInstance(65,80,java.awt.Image.SCALE_SMOOTH); 
        	tempIcon = new ImageIcon(tempImg2);
        	c1Dis.setIcon(tempIcon);
        	panel.add(c1Dis);
        	
        	cardImg = ImageIO.read(getClass().getResource(c2.getIndex()+".png"));
			tempIcon = new ImageIcon(cardImg);
        	tempImg = tempIcon.getImage();
        	tempImg2 = tempImg.getScaledInstance(65,80,java.awt.Image.SCALE_SMOOTH); 
        	tempIcon = new ImageIcon(tempImg2);
        	c2Dis.setIcon(tempIcon);
        	panel.add(c2Dis);
    	}
    	catch (IOException ioex){
		}
	}
	
	/**
	 * Initialize a player
	 */
	private void initAI(JPanel panel, int num, int display) {
		ImageIcon tempIcon = new ImageIcon(back);
        Image tempImg = tempIcon.getImage();
        Image tempImg2 = tempImg.getScaledInstance(65,80,java.awt.Image.SCALE_SMOOTH); 
        tempIcon = new ImageIcon(tempImg2);
		String name;
		int money;
		Image cardImg;
		JLabel card1Display = new JLabel();
		JLabel card2Display = new JLabel();
		Card c1 = null; Card c2 = null;
		if (num != -1){
			card1Display.setIcon(tempIcon);
			card2Display.setIcon(tempIcon);
			panel.add(card1Display);
			panel.add(card2Display);
			if(display == 1) {
				panel.removeAll();	panel.revalidate();
				panel.repaint();
				displayAICards(num, panel, card1Display, card2Display);
				name = players.get(num).getName();
				money = players.get(num).getMoney();
				JLabel nameL = new JLabel(name);
				JLabel label = new JLabel();
				label.setText("Balance: " + balance);
				label.setForeground(Color.white);
				panel.add(label); panel.add(nameL);
				return;
			}
			c1 = deck.get(cardCount--);
			c2 = deck.get(cardCount--);
			name = players.get(num).getName();
			money = players.get(num).getMoney();
			players.get(num).setCard1(c1);
			players.get(num).setCard2(c2);
			log(name, 0, c1, c2);
		}
		else{
			c1 = deck.get(cardCount--);
			c2 = deck.get(cardCount--);
			Card userC1 = new Card(c1.getRank(), c1.getSuit());
			Card userC2 = new Card(c2.getRank(), c2.getSuit());
			name = userName;
			money = user.getMoney();
			user.setCard1(userC1);
			user.setCard2(userC2);
			log(name, 0, c1, c2);
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
		label.setText("Balance:" + balance);
		label.setForeground(Color.white);
		panel.add(label);
		panel.add(nameL);
	}
	
	/**
	 * Set the north AI players
	 */
	private void setNorth() {
		northAI1.setBackground(new Color(43, 151, 0));
		northAI1.setBorder(BorderFactory.createLineBorder(Color.white,2));
		northAI1.setBounds(95,30,310,130);
		initAI(northAI1,0,0);
		add(northAI1);
		if(numOfAI == 2 || numOfAI == 3 || numOfAI > 3) {
			northAI2.setBackground(new Color(43, 151, 0));
			northAI2.setBorder(BorderFactory.createLineBorder(Color.white,2));
			northAI2.setBounds(481,30,310,130);
			initAI(northAI2,1,0);
			add(northAI2);
		}
		if (numOfAI == 3 || numOfAI > 3){
			northAI3.setBackground(new Color(43, 151, 0));
			northAI3.setBorder(BorderFactory.createLineBorder(Color.white,2));
			northAI3.setBounds(866,30,310,130);
			initAI(northAI3,2,0);
			add(northAI3);
		}
	}
	
	/**
	 * Set the west AI players
	 */
	private void setWest() {
		westAI1.setBackground(new Color(43, 151, 0));
		westAI1.setBorder(BorderFactory.createLineBorder(Color.white,2));
		westAI1.setBounds(10,180,310,130);
		initAI(westAI1,3,0);
		add(westAI1);
		if(numOfAI == 5 || numOfAI > 5) {
			westAI2.setBackground(new Color(43, 151, 0));
			westAI2.setBorder(BorderFactory.createLineBorder(Color.white,2));
			westAI2.setBounds(10,330,310,130);
			initAI(westAI2,4,0);
			add(westAI2);
		}
	}
	
	/**
	 * Set the east AI players
	 */
	private void setEast() {
    	eastAI1.setBackground(new Color(43, 151, 0));
    	eastAI1.setBorder(BorderFactory.createLineBorder(Color.white,2));
    	eastAI1.setBounds(962,180,310,130);
    	initAI(eastAI1,5,0);
    	add(eastAI1);
    	if(numOfAI == 7) {
    		eastAI2.setBackground(new Color(43, 151, 0));
    		eastAI2.setBorder(BorderFactory.createLineBorder(Color.white,2));
    		eastAI2.setBounds(962,330,310,130);
    		initAI(eastAI2,6,0);
    		add(eastAI2);
    	}
	}
	
	/**
	 * Log the event in a text file
	 */
	private void log(String name, int event, Card c1, Card c2) {
		if(event == -1) {
			
		}
		// event: cards dealt
		if(event == 0) {
			logWriter.print(name + ": ");
			logWriter.print(c1.suitToString(c1.getSuit()) +" " + c1.getRank() + ", ");
			logWriter.println(c2.suitToString(c2.getSuit()) + " " + c2.getRank());
		}
		// event: calls
		if(event == 1) {
			logWriter.println(name + " calls");
		}
		// event: folds
		if(event == 2) {
			logWriter.println(name + " folds");
		}
		// pass c1 as the flop/river/turn card, c2 as null
		if(event == 3) {
			Card center1 = centerHand.getCard(0);
			Card center2 = centerHand.getCard(1);
			Card center3 = centerHand.getCard(2);
			logWriter.print("Flop: " + center1.suitToString(c1.getSuit()) +" " + center1.getRank() + ", ");
			logWriter.print(center2.suitToString(center2.getSuit()) +" " + center2.getRank() + ", ");
			logWriter.println(center3.suitToString(center3.getSuit()) +" " + center3.getRank());
		}
		if(event == 4) {
			logWriter.println("River: " + c1.suitToString(c1.getSuit()) +" " + c1.getRank());
		}
		if(event == 5) {
			logWriter.println("Turn: " + c1.suitToString(c1.getSuit()) +" " + c1.getRank());
		}
	}
}
