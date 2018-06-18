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

public class MainFrame extends JFrame implements ActionListener{
	// the frame structure
	private Image background;
	private Image stackImage;
	private Image chips;
	private Image back;
	// Card images
	private int numOfAI;
	private int cardCount = 51;
	private JPanel northAI1 = new JPanel();
	private JPanel northAI2 = new JPanel();
	private JPanel northAI3 = new JPanel();
	private String[] opponents = {"Leopold Bloom","Stephen Dedalus","Yelverton Barry","Buck Mulligan","Martin Cunningham","Molly Bloom","Josie Breen"};
    private JPanel player = new JPanel();
    private JPanel pot = new JPanel();
    private JPanel eastAI1 = new JPanel();
    private JPanel eastAI2 = new JPanel();
    private JPanel westAI1 = new JPanel();
    private JPanel westAI2 = new JPanel();
    private JPanel fiveCards = new JPanel();
    private Hand centerHand = new Hand();
    // TODO
    private JButton betButton = new JButton("Bet");
    private JButton foldButton = new JButton("Fold");
    
    private JTextField betAmt = new JTextField();
    // the player name
    private String userName = new String();
    private Player user = new Player(false, userName, 1000);
    // money in the pot
    private int moneyInPot = 0;
    // poker chips
    private int blackChip = 0;
    private int whiteChip = 0;
    private int redChip = 0;
    private int blueChip = 0;
    // AIs array
    private ArrayList<Player> players;
    private static ArrayList<Card> deck;
    // initialize the log file
    PrintWriter logWriter = null;
    // 1 if the game ends
    int endOfGame = 0;
    
    
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

    private void initLog(String name, int num) throws FileNotFoundException{
    	logWriter = new PrintWriter("log.txt");
    	String time = new SimpleDateFormat("dd MMMM yyyy  -  HH : mm").format(Calendar.getInstance().getTime());
    	logWriter.println("       * * * Game Log * * *\n" + "- Game started " + time);
    	logWriter.print("- Username: "+ name + "\n- Players: " + num + "\n  ");
    	for (int i = 0; i < num-1; i++)
    		logWriter.print(opponents[i]+", ");
    	logWriter.println(opponents[num-1] + "\n\n-");
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
        player.add(betButton);
        betButton.addActionListener(this);
        player.add(foldButton);
        gameStart();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// flop
		if(centerHand.isEmpty()){
			centerHand.addCard(deck.get(cardCount--));
			centerHand.addCard(deck.get(cardCount--));
			centerHand.addCard(deck.get(cardCount--));
		}
		else{
			centerHand.addCard(deck.get(cardCount--));
		}

		if(centerHand.getSize() == 3)
			displayCenterCards(centerHand, 1);
		else if(centerHand.getSize() == 4)
			displayCenterCards(centerHand, 2);
		else
			displayCenterCards(centerHand, 3);
	}

	private void displayCenterCards(Hand centerHand, int round){
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
	        	
	        	player.add(card1Display);
        		player.add(card2Display);
        		player.add(card3Display);
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
	        }
	        add(player);
		}
		catch (IOException ioex){
		}
	}

	private void gameStart(){
		int winnerIndex = -1;
		centerHand = new Hand();

		//preflop

		//flop: the three cards



		//turn

		//river
		if(winnerIndex == -1)
			logWriter.println("The winner is you!");
		else{

		}
		endOfGame = 1;
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
			log(name, 0, c1, c2);
		}
		else{
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
    	endOfGame = 1;
	}
	private void log(String name, int event, Card c1, Card c2) {
		if(event == -1) {
			logWriter.println("Hands 1:\n"+"Cards Dealt:");
		}
		// event: cards dealt
		if(event == 0) {
			logWriter.print(name + ": ");
			logWriter.print(c1.suitToString(c1.getSuit()) +" " + c1.getRank() + ", ");
			logWriter.println(c2.suitToString(c2.getSuit()) + " " + c2.getRank());
		}
		// event: draw cards
		// event: 
		if(endOfGame == 1)
			logWriter.close();
	}
}
