import java.awt.*;
import java.awt.event.*;
import java.awt.List;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.jar.Attributes.Name;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.imageio.*;

@SuppressWarnings("unchecked")

public class MainFrame extends JFrame {
	// to convert and display images
	private Image stackImage;
	private Image chips;
	private Image back;

	// AI and card
	private int numOfAI;
	private int cardCount = 51;

	// the frame structures
	private JPanel northAI1 = new JPanel();
	private JPanel northAI2 = new JPanel();
	private JPanel northAI3 = new JPanel();
	private String[] opponents = { "Leopold Bloom", "Stephen Dedalus", "Yelverton Barry", "Buck Mulligan",
			"Martin Cunningham", "Molly Bloom", "Josie Breen" };
	private JPanel p = new JPanel(new GridLayout(2, 3, 2, 2));
	private JPanel p2 = new JPanel(new GridLayout(2, 4, 2, 2));
	private JPanel p3 = new JPanel();
	private JPanel player = new JPanel();
	private JPanel pot = new JPanel();
	private JPanel eastAI1 = new JPanel();
	private JPanel eastAI2 = new JPanel();
	private JPanel westAI1 = new JPanel();
	private JPanel westAI2 = new JPanel();
	private JPanel fiveCards = new JPanel();
	private Hand centerHand = new Hand();
	private JLabel moneyInPotLable = new JLabel();
	private JLabel roundLabel = new JLabel();
	private JLabel userStack = new JLabel();

	// USER OPTION BUTTONS
	private int betAmount = 0;
	private JButton betButton = new JButton("Bet");
	private JButton bet10Button = new JButton("$10");
	private JButton bet50Button = new JButton("$50");
	private JButton bet100Button = new JButton("$100");
	private JButton clearButton = new JButton("CLEAR");
	private JButton callButton = new JButton("Call");
	private JButton foldButton = new JButton("Fold");
	private JTextField betAmt = new JTextField("$" + betAmount);

	// the player name
	private String userName = new String();
	private Player user = new Player(false, userName, 1000);

	// money in the pot
	private int moneyInPot = 0;

	// AIs array
	private ArrayList<Player> players;
	private static ArrayList<Card> deck;

	// initialize the log file
	PrintWriter logWriter = null;
	int gameRound = 0;
	boolean allFold = false;
	boolean ifUserFold = false;

	/**
	 * Constructor
	 */
	public MainFrame(String playerName, int num) {
		super("Texas Hold'em");
		userName = playerName;
		numOfAI = num;
		players = new ArrayList<>();
		deck = new ArrayList();
		for (int j = 0; j < numOfAI; j++) {
			players.add(new Player(true, opponents[j], 1000));
		}
		buildDeck();
		shuffle();
		try {
			initLog(playerName, num);
		} catch (Exception e) {
		}
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
		for (int i = 1; i < 5; i++) {
			// # of ranks
			for (int j = 1; j < 14; j++) {
				Card c = new Card(j, i);
				c.setCardIndex(i, j);
				deck.add(c);
			}
		}
	}

	/**
	 * Initialize the logWriter and log the basic info about the game
	 */
	private void initLog(String name, int num) throws FileNotFoundException {
		logWriter = new PrintWriter("log.txt");
		String startTime = new SimpleDateFormat("dd MMMM yyyy  -  HH : mm").format(Calendar.getInstance().getTime());
		logWriter.println("       * * * Game Log * * *\n" + "- Game started " + startTime);
		logWriter.print("- Username: " + name + "\n- Players: " + num + "\n  ");
		for (int i = 0; i < num - 1; i++)
			logWriter.print(opponents[i] + ", ");
		logWriter.println(opponents[num - 1] + "\n\nPreflop:\nCards Dealt:");
		// cards dealt are recorded when initialize (AI)players
	}

	/**
	 * Construct the main frame
	 */
	private void initFrame() {
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(9, 120, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
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
		} catch (IOException ioex) {
			System.exit(1);
		}

		// NORTH AND WEST
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		if (numOfAI <= 3)
			setNorth();
		if (numOfAI > 3 && numOfAI <= 5) {
			setNorth();
			setWest();
		}

		// CENTER
		// stack chips
		setPokerChips();
		pot.setBackground(new Color(4, 95, 0));
		pot.setBorder(BorderFactory.createLineBorder(Color.white, 3));
		pot.setBounds(326, 180, 630, 320);
		// space holding the five cards
		fiveCards.setBackground(new Color(4, 95, 0));
		fiveCards.setBorder(BorderFactory.createLineBorder(Color.white, 2));
		fiveCards.setBounds(336, 220, 430, 270);
		add(fiveCards);
		moneyInPotLable.setText("Money in the pot: " + String.valueOf(moneyInPot));
		moneyInPotLable.setFont(new Font("Optima", Font.BOLD, 23));
		moneyInPotLable.setForeground(Color.white);
		pot.add(moneyInPotLable);
		getContentPane().add(pot);
		//

		// NORTH, WEST, AND EAST
		if (numOfAI > 5) {
			setNorth();
			setWest();
			setEast();
		}

		// SOUTH
		player.setBackground(new Color(43, 151, 0));
		player.setBorder(BorderFactory.createLineBorder(Color.white, 2));
		player.setBounds(165, 506, 957, 160);
		centerHand.addCard(deck.get(cardCount--));
		centerHand.addCard(deck.get(cardCount--));
		centerHand.addCard(deck.get(cardCount--));
		initAI(player, -1, 0);
		add(player);
		betButton.setEnabled(false);

		// FORMATTING FOR USER OPTIONS (in SOUTH)
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
		betAmt.setPreferredSize(new Dimension(50, 24));

		p3.add(betAmt);
		betAmt.setEditable(false);
		p2.add(p3);
		p.add(foldButton);

		p.setVisible(true);
		p2.setVisible(true);
		p3.setVisible(true);

		// START GAME!
		gameStart();
	}

	/**
	 * Display the center hand cards
	 */
	private void displayCenterCards(Hand centerHand, int round) {
		if (round == -1)
			return;
		ImageIcon tempIcon;
		Image tempImg;
		Image tempImg2;
		Image cardImg;

		JLabel card1Display = new JLabel();
		JLabel card2Display = new JLabel();
		JLabel card3Display = new JLabel();
		JLabel card4Display = new JLabel();
		JLabel card5Display = new JLabel();

		try {
			if (round == 1) {
				Card c1 = centerHand.getCard(0);
				Card c2 = centerHand.getCard(1);
				Card c3 = centerHand.getCard(2);

				cardImg = ImageIO.read(getClass().getResource(c1.getIndex() + ".png"));
				tempIcon = new ImageIcon(cardImg);
				tempImg = tempIcon.getImage();
				tempImg2 = tempImg.getScaledInstance(65, 80, java.awt.Image.SCALE_SMOOTH);
				tempIcon = new ImageIcon(tempImg2);
				card1Display.setIcon(tempIcon);

				cardImg = ImageIO.read(getClass().getResource(c2.getIndex() + ".png"));
				tempIcon = new ImageIcon(cardImg);
				tempImg = tempIcon.getImage();
				tempImg2 = tempImg.getScaledInstance(65, 80, java.awt.Image.SCALE_SMOOTH);
				tempIcon = new ImageIcon(tempImg2);
				card2Display.setIcon(tempIcon);

				cardImg = ImageIO.read(getClass().getResource(c3.getIndex() + ".png"));
				tempIcon = new ImageIcon(cardImg);
				tempImg = tempIcon.getImage();
				tempImg2 = tempImg.getScaledInstance(65, 80, java.awt.Image.SCALE_SMOOTH);
				tempIcon = new ImageIcon(tempImg2);
				card3Display.setIcon(tempIcon);

				fiveCards.add(card1Display);
				fiveCards.add(card2Display);
				fiveCards.add(card3Display);
				fiveCards.revalidate();
			} else if (round == 2) {
				Card c4 = centerHand.getCard(3);
				cardImg = ImageIO.read(getClass().getResource(c4.getIndex() + ".png"));
				tempIcon = new ImageIcon(cardImg);
				tempImg = tempIcon.getImage();
				tempImg2 = tempImg.getScaledInstance(65, 80, java.awt.Image.SCALE_SMOOTH);
				tempIcon = new ImageIcon(tempImg2);
				card4Display.setIcon(tempIcon);
				fiveCards.add(card4Display);
				fiveCards.revalidate();
			} else {
				Card c5 = centerHand.getCard(4);
				cardImg = ImageIO.read(getClass().getResource(c5.getIndex() + ".png"));
				tempIcon = new ImageIcon(cardImg);
				tempImg = tempIcon.getImage();
				tempImg2 = tempImg.getScaledInstance(65, 80, java.awt.Image.SCALE_SMOOTH);
				tempIcon = new ImageIcon(tempImg2);
				card5Display.setIcon(tempIcon);
				fiveCards.add(card5Display);
				fiveCards.revalidate();
			}
			pot.revalidate();
		} catch (IOException ioex) {
		}
	}

	/**
	 * Show the round number
	 */
	private void showRound() {
		if (gameRound == 0)
			roundLabel.setText("Round: Preflop");
		else if (gameRound == 1)
			roundLabel.setText("Round: Flop");
		else if (gameRound == 2)
			roundLabel.setText("Round: Turn");
		else if (gameRound == 3)
			roundLabel.setText("Round: River");
		else
			roundLabel.setText("Final");
		roundLabel.setBackground(new Color(43, 151, 0));
		roundLabel.setFont(new Font("Optima", Font.BOLD, 23));
		roundLabel.setForeground(Color.yellow);
		pot.add(roundLabel);
		pot.revalidate();
	}

	/**
	 * Handle transitions and buttons
	 * 
	 * @throws InterruptedException
	 */
	private void transition(boolean ifAuto) throws InterruptedException {
		// currently in preflop; to enter the flop round
		if (gameRound == 0) {
			logWriter.println("\nFlop:");
			displayCenterCards(centerHand, 1);
			centerHand.addCard(deck.get(cardCount--));
			gameRound++;
			if (user.getFold())
				logWriter.println(userName + " folded");
			TimeUnit.MILLISECONDS.sleep(560);
			if (user.getFold()) {
				for (int i = 0; i < players.size(); i++) {
					logWriter.println(players.get(i).getName() + " calls");
				}
			}
			showRound();
			if (ifAuto)
				transition(true);
		}
		// to enter the turn round
		else if (gameRound == 1) {
			logWriter.println("\nTurn:");
			displayCenterCards(centerHand, 2);
			centerHand.addCard(deck.get(cardCount--));
			gameRound++;
			if (user.getFold())
				logWriter.println(userName + " folded");
			TimeUnit.MILLISECONDS.sleep(560);
			if (user.getFold()) {
				for (int i = 0; i < players.size(); i++) {
					logWriter.println(players.get(i).getName() + " calls");
				}
			}
			showRound();
			if (ifAuto)
				transition(true);
		}
		// to enter the river round
		else if (gameRound == 2) {
			logWriter.println("\nRiver:");
			displayCenterCards(centerHand, 3);
			gameRound++;
			if (user.getFold())
				logWriter.println(userName + " folded");
			TimeUnit.MILLISECONDS.sleep(560);
			if (user.getFold()) {
				for (int i = 0; i < players.size(); i++) {
					logWriter.println(players.get(i).getName() + " calls");
				}
			}
			showRound();
			if (ifAuto)
				transition(true);
		}
		// to get the result
		else if (gameRound == 3) {
			logWriter.println("\nFinal:");
			String result = new String();
			gameRound++;
			showRound();
			// disable all the buttons for the user
			betButton.setEnabled(false);
			foldButton.setEnabled(false);
			callButton.setEnabled(false);
			bet10Button.setEnabled(false);
			bet50Button.setEnabled(false);
			bet100Button.setEnabled(false);
			clearButton.setEnabled(false);
			boolean tie = false;
			int tieIndex = -1;
			// display the cards
			if (!allFold) {
				int winnerIndex = -1;
				// check the user and AIs' score
				int maxScore = user.getHand().checkScore(centerHand, user.getFold());
				int aiHighestScore = 0;
				for (int i = 0; i < players.size(); i++) {
					aiHighestScore = players.get(i).getHand().checkScore(centerHand, allFold);
					if (aiHighestScore > maxScore) {
						maxScore = aiHighestScore;
						winnerIndex = i;
					} else if (aiHighestScore == maxScore) {

						int tieBreak = 0;
						if (winnerIndex == -1) {

							tieBreak = tieBreaker(user, players.get(i));
							if (tieBreak == 2) {

								maxScore = aiHighestScore;
								winnerIndex = i;
							}
						} else {

							tieBreak = tieBreaker(players.get(winnerIndex), players.get(i));
							if (tieBreak == 2) {

								maxScore = aiHighestScore;
								winnerIndex = i;
							}
						}
						if (tieBreak == 0) {

							tie = true;
							tieIndex = i;
						}
					}
				}
				// display AI cards
				if (numOfAI == 1)
					initAI(northAI1, 1, 1);
				else if (numOfAI == 2) {
					initAI(northAI1, 0, 1);
					initAI(northAI2, 1, 1);
				} else if (numOfAI == 3) {
					initAI(northAI1, 0, 1);
					initAI(northAI2, 1, 1);
					initAI(northAI3, 2, 1);
				} else if (numOfAI == 4) {
					initAI(northAI1, 0, 1);
					initAI(northAI2, 1, 1);
					initAI(northAI3, 2, 1);
					initAI(westAI1, 3, 1);
				} else if (numOfAI == 5) {
					initAI(northAI1, 0, 1);
					initAI(northAI2, 1, 1);
					initAI(northAI3, 2, 1);
					initAI(westAI1, 3, 1);
					initAI(westAI2, 4, 1);
				} else if (numOfAI == 6) {
					initAI(northAI1, 0, 1);
					initAI(northAI2, 1, 1);
					initAI(northAI3, 2, 1);
					initAI(westAI1, 3, 1);
					initAI(westAI2, 4, 1);
					initAI(eastAI1, 5, 1);
				} else {
					initAI(northAI1, 0, 1);
					initAI(northAI2, 1, 1);
					initAI(northAI3, 2, 1);
					initAI(westAI1, 3, 1);
					initAI(westAI2, 4, 1);
					initAI(eastAI1, 5, 1);
					initAI(eastAI2, 6, 1);
				}

				if (winnerIndex == -1) {

					result = "\nYou win with " + handType(user) + ", and you win $" + moneyInPot;
					logWriter.println(result);
					// move the money in the pot to the user's pocket
					userStack.setText("Balance:" + 1000);
					userStack.setForeground(Color.white);
					player.revalidate();
				} else {
					result = "The winner is " + players.get(winnerIndex).getName() + " with "
							+ handType(players.get(winnerIndex)) + ", and "
							+ players.get(winnerIndex).getName() + " wins $" + moneyInPot;
					logWriter.println(result);
				}
			} else {
				result = "\nYou win with " + handType(user) + ", and you win $" + moneyInPot;
				logWriter.println(result);
				// move the money in the pot to the user's pocket
				userStack.setText("Balance:" + 1000);
				userStack.setForeground(Color.white);
				player.revalidate();
			}
			// log the end time of the game and close the file writing
			String endTime = new SimpleDateFormat("dd MMMM yyyy  -  HH : mm").format(Calendar.getInstance().getTime());
			logWriter.println("\n- Game ends " + endTime);
			logWriter.close();

			// pop-up window showing the result
			new resultFrame(result);
		} else
			return;
	}

	/**
	 * Start a game and go into three transitions
	 */
	private void gameStart() {
		showRound();
		// BUTTONS:
		bet10Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (user.getStack() >= betAmount + 10) {
					betAmount += 10;
					betAmt.setText("$" + betAmount);
					betButton.setEnabled(true);
				} else
					bet10Button.setEnabled(false);
			}
		});

		bet50Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (user.getStack() >= betAmount + 50) {
					betAmount += 50;
					betAmt.setText("$" + betAmount);
					betButton.setEnabled(true);
				} else
					bet50Button.setEnabled(false);
			}
		});

		bet100Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// hide the frame
				if (user.getStack() >= betAmount + 100) {
					betAmount += 100;
					betAmt.setText("$" + betAmount);
					betButton.setEnabled(true);
				} else
					bet100Button.setEnabled(false);
			}
		});

		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// hide the frame
				betAmount = 0;
				betAmt.setText("$" + betAmount);
				betButton.setEnabled(false);
				bet10Button.setEnabled(true);
				bet50Button.setEnabled(true);
				bet100Button.setEnabled(true);
			}
		});

		betButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (betAmount <= user.getStack()) {
					logWriter.println(userName + " bets " + betAmount);
					for (int i = 0; i < players.size(); i++) {
						if (!players.get(i).getFold()) {
							logWriter.println(players.get(i).getName() + " folds");
							players.get(i).setFold();
						} else
							logWriter.println(players.get(i).getName() + " folded");
					}
					user.setStack(user.getStack() - betAmount);
					moneyInPot += betAmount;
					userStack.setText("Balance:" + user.getStack());
					userStack.setForeground(Color.white);
					player.revalidate();
					betAmount = 0; // reset the bet amount
					allFold = true; // all the AI folds
					moneyInPotLable.setText("Money in the pot: " + moneyInPot);
					moneyInPotLable.setFont(new Font("Optima", Font.BOLD, 23));
					moneyInPotLable.setForeground(Color.white);
					pot.add(moneyInPotLable);
					pot.revalidate();
					aiCardsRemove();
					/*
					 * NOTE: fold button is disabled here because all AIs fold after the user bets
					 */
					foldButton.setEnabled(false);
					// bet button is disabled before the next round
					betButton.setEnabled(false);
					try {
						transition(false);
					} catch (InterruptedException e1) {
					}
				}
			}
		});

		foldButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!user.getFold())
					logWriter.println(userName + " folds");
				for (int i = 0; i < players.size(); i++) {
					logWriter.println(players.get(i).getName() + " calls");
				}
				user.setFold();
				player.removeAll();
				// if the user folds, remove all the components
				// except the balance/stack
				JLabel label = new JLabel();
				label.setText("Balance: " + 1000);
				label.setForeground(Color.white);
				add(label);
				player.revalidate();
				try {
					transition(true);
				} catch (InterruptedException e1) {
				}
			}
		});

		callButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logWriter.println(userName + " calls");
				for (int i = 0; i < players.size(); i++) {
					if (!players.get(i).getFold())
						logWriter.println(players.get(i).getName() + " calls");
					else
						logWriter.println(players.get(i).getName() + " folded");
				}
				try {
					transition(false);
				} catch (InterruptedException e1) {
				}
			}
		});
	}

	private void aiCardsRemove() {
		JLabel nameL = new JLabel();
		JLabel n1StackL = new JLabel();
		n1StackL.setText("Balance: " + 1000);
		n1StackL.setForeground(Color.white);
		northAI1.removeAll();
		nameL = new JLabel(opponents[0]);
		northAI1.add(n1StackL);
		northAI1.add(nameL);
		northAI1.repaint();
		northAI1.revalidate();
		JLabel n2StackL = new JLabel();
		n2StackL.setText("Balance: " + 1000);
		n2StackL.setForeground(Color.white);
		northAI2.removeAll();
		nameL = new JLabel(opponents[1]);
		northAI2.add(n2StackL);
		northAI2.add(nameL);
		northAI2.repaint();
		northAI2.revalidate();
		JLabel n3StackL = new JLabel();
		n3StackL.setText("Balance: " + 1000);
		n3StackL.setForeground(Color.white);
		northAI3.removeAll();
		nameL = new JLabel(opponents[2]);
		northAI3.add(n3StackL);
		northAI3.add(nameL);
		northAI3.repaint();
		northAI3.revalidate();
		JLabel w1StackL = new JLabel();
		w1StackL.setText("Balance: " + 1000);
		w1StackL.setForeground(Color.white);
		westAI1.removeAll();
		nameL = new JLabel(opponents[3]);
		westAI1.add(w1StackL);
		westAI1.add(nameL);
		westAI1.repaint();
		westAI1.revalidate();
		JLabel w2StackL = new JLabel();
		w2StackL.setText("Balance: " + 1000);
		w2StackL.setForeground(Color.white);
		westAI2.removeAll();
		nameL = new JLabel(opponents[4]);
		westAI2.add(w2StackL);
		westAI2.add(nameL);
		westAI2.repaint();
		westAI2.revalidate();
		JLabel e1StackL = new JLabel();
		e1StackL.setText("Balance: " + 1000);
		e1StackL.setForeground(Color.white);
		eastAI1.removeAll();
		nameL = new JLabel(opponents[5]);
		eastAI1.add(e1StackL);
		eastAI1.add(nameL);
		eastAI1.repaint();
		eastAI1.revalidate();
		JLabel e2StackL = new JLabel();
		e2StackL.setText("Balance: " + 1000);
		e2StackL.setForeground(Color.white);
		eastAI2.removeAll();
		nameL = new JLabel(opponents[6]);
		eastAI2.add(e2StackL);
		eastAI2.add(nameL);
		eastAI2.repaint();
		eastAI2.revalidate();
	}

	/**
	 * Add the poker chips images
	 */
	private void setPokerChips() {
		// poker chips in the pot
		ImageIcon tempIcon = new ImageIcon(chips);
		Image tempImg = tempIcon.getImage();
		Image tempImg2 = tempImg.getScaledInstance(165, 108, java.awt.Image.SCALE_SMOOTH);
		tempIcon = new ImageIcon(tempImg2);
		JLabel chips = new JLabel();
		chips.setBounds(775, 215, 165, 108);
		chips.setIcon(tempIcon);
		add(chips);
		/*
		 * JLabel chipsAmt = new JLabel();
		 * chipsAmt.setText(String.valueOf(blackChip)+"   "+String.valueOf(whiteChip)
		 * +"   "+ String.valueOf(redChip)+"   "+String.valueOf(blueChip));
		 * chipsAmt.setFont(new Font("Consolas", Font.PLAIN, 14));
		 * chipsAmt.setForeground(Color.white); chipsAmt.setBounds(810,300,150,50);
		 * add(chipsAmt);
		 */
		// stack image in the player area
		tempIcon = new ImageIcon(stackImage);
		tempImg = tempIcon.getImage();
		tempImg2 = tempImg.getScaledInstance(65, 80, java.awt.Image.SCALE_SMOOTH);
		tempIcon = new ImageIcon(tempImg2);
		JLabel stacks = new JLabel();
		stacks.setBounds(185, 580, 65, 80);
		stacks.setIcon(tempIcon);
		add(stacks);
	}

	private void displayAICards(int i, JPanel panel, JLabel c1Dis, JLabel c2Dis) {
		Card c1 = players.get(i).getCard1();
		Card c2 = players.get(i).getCard2();
		try {
			Image cardImg = ImageIO.read(getClass().getResource(c1.getIndex() + ".png"));
			ImageIcon tempIcon = new ImageIcon(cardImg);
			Image tempImg = tempIcon.getImage();
			Image tempImg2 = tempImg.getScaledInstance(65, 80, java.awt.Image.SCALE_SMOOTH);
			tempIcon = new ImageIcon(tempImg2);
			c1Dis.setIcon(tempIcon);
			panel.add(c1Dis);

			cardImg = ImageIO.read(getClass().getResource(c2.getIndex() + ".png"));
			tempIcon = new ImageIcon(cardImg);
			tempImg = tempIcon.getImage();
			tempImg2 = tempImg.getScaledInstance(65, 80, java.awt.Image.SCALE_SMOOTH);
			tempIcon = new ImageIcon(tempImg2);
			c2Dis.setIcon(tempIcon);
			panel.add(c2Dis);
		} catch (IOException ioex) {
		}
	}

	/**
	 * Initialize a player
	 */
	private void initAI(JPanel panel, int num, int display) {
		ImageIcon tempIcon = new ImageIcon(back);
		Image tempImg = tempIcon.getImage();
		Image tempImg2 = tempImg.getScaledInstance(65, 80, java.awt.Image.SCALE_SMOOTH);
		tempIcon = new ImageIcon(tempImg2);
		String name;
		int stack;
		Image cardImg;
		JLabel card1Display = new JLabel();
		JLabel card2Display = new JLabel();
		Card c1 = null;
		Card c2 = null;
		if (num != -1) {
			card1Display.setIcon(tempIcon);
			card2Display.setIcon(tempIcon);
			panel.add(card1Display);
			panel.add(card2Display);
			if (display == 1) {
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				displayAICards(num, panel, card1Display, card2Display);
				name = players.get(num).getName();
				stack = players.get(num).getStack();
				JLabel nameL = new JLabel(name);
				JLabel label = new JLabel();
				label.setText("Balance: " + 1000);
				label.setForeground(Color.white);
				panel.add(label);
				panel.add(nameL);
				return;
			}
			c1 = deck.get(cardCount--);
			c2 = deck.get(cardCount--);
			name = players.get(num).getName();
			stack = players.get(num).getStack();
			players.get(num).setCard1(c1);
			players.get(num).setCard2(c2);
			log(name, 0, c1, c2);
			JLabel label = new JLabel();
			label.setText("Balance:" + 1000);
			label.setForeground(Color.white);
			panel.add(label);
		} else {
			c1 = deck.get(cardCount--);
			c2 = deck.get(cardCount--);
			Card userC1 = new Card(c1.getRank(), c1.getSuit());
			Card userC2 = new Card(c2.getRank(), c2.getSuit());
			name = userName;
			stack = user.getStack();
			user.setCard1(userC1);
			user.setCard2(userC2);
			log(name, 0, c1, c2);
			try {
				cardImg = ImageIO.read(getClass().getResource(c1.getIndex() + ".png"));
				tempIcon = new ImageIcon(cardImg);
				tempImg = tempIcon.getImage();
				tempImg2 = tempImg.getScaledInstance(65, 80, java.awt.Image.SCALE_SMOOTH);
				tempIcon = new ImageIcon(tempImg2);
				card1Display.setIcon(tempIcon);

				cardImg = ImageIO.read(getClass().getResource(c2.getIndex() + ".png"));
				tempIcon = new ImageIcon(cardImg);
				tempImg = tempIcon.getImage();
				tempImg2 = tempImg.getScaledInstance(65, 80, java.awt.Image.SCALE_SMOOTH);
				tempIcon = new ImageIcon(tempImg2);
				card2Display.setIcon(tempIcon);

				panel.add(card1Display);
				panel.add(card2Display);
			} catch (IOException ioex) {
			}
			userStack.setText("Balance:" + user.getStack());
			userStack.setForeground(Color.white);
			player.add(userStack);
		}
		JLabel nameL = new JLabel(name);
		panel.add(nameL);
	}

	/**
	 * Set the north AI players
	 */
	private void setNorth() {
		northAI1.setBackground(new Color(43, 151, 0));
		northAI1.setBorder(BorderFactory.createLineBorder(Color.white, 2));
		northAI1.setBounds(95, 30, 310, 130);
		initAI(northAI1, 0, 0);
		add(northAI1);
		if (numOfAI == 2 || numOfAI == 3 || numOfAI > 3) {
			northAI2.setBackground(new Color(43, 151, 0));
			northAI2.setBorder(BorderFactory.createLineBorder(Color.white, 2));
			northAI2.setBounds(481, 30, 310, 130);
			initAI(northAI2, 1, 0);
			add(northAI2);
		}
		if (numOfAI == 3 || numOfAI > 3) {
			northAI3.setBackground(new Color(43, 151, 0));
			northAI3.setBorder(BorderFactory.createLineBorder(Color.white, 2));
			northAI3.setBounds(866, 30, 310, 130);
			initAI(northAI3, 2, 0);
			add(northAI3);
		}
	}

	/**
	 * Set the west AI players
	 */
	private void setWest() {
		westAI1.setBackground(new Color(43, 151, 0));
		westAI1.setBorder(BorderFactory.createLineBorder(Color.white, 2));
		westAI1.setBounds(10, 180, 310, 130);
		initAI(westAI1, 3, 0);
		add(westAI1);
		if (numOfAI == 5 || numOfAI > 5) {
			westAI2.setBackground(new Color(43, 151, 0));
			westAI2.setBorder(BorderFactory.createLineBorder(Color.white, 2));
			westAI2.setBounds(10, 330, 310, 130);
			initAI(westAI2, 4, 0);
			add(westAI2);
		}
	}

	/**
	 * Set the east AI players
	 */
	private void setEast() {
		eastAI1.setBackground(new Color(43, 151, 0));
		eastAI1.setBorder(BorderFactory.createLineBorder(Color.white, 2));
		eastAI1.setBounds(962, 180, 310, 130);
		initAI(eastAI1, 5, 0);
		add(eastAI1);
		if (numOfAI == 7) {
			eastAI2.setBackground(new Color(43, 151, 0));
			eastAI2.setBorder(BorderFactory.createLineBorder(Color.white, 2));
			eastAI2.setBounds(962, 330, 310, 130);
			initAI(eastAI2, 6, 0);
			add(eastAI2);
		}
	}

	/**
	 * Log the event in a text file
	 */
	@SuppressWarnings("static-access")
	private void log(String name, int event, Card c1, Card c2) {
		// event: cards dealt
		if (event == 0) {
			logWriter.print(name + ": ");
			logWriter.print(c1.suitToString(c1.getSuit()) + " " + c1.rankToString(c1.getRank()).toLowerCase() + ", ");
			logWriter.println(c2.suitToString(c2.getSuit()) + " " + c2.rankToString(c2.getRank()).toLowerCase());
		}
		// event: calls
		if (event == 1) {
			logWriter.println(name + " calls");
		}
		// event: folds
		if (event == 2) {
			logWriter.println(name + " folds");
		}
		// pass c1 as the flop/river/turn card, c2 as null
		if (event == 3) {
			Card center1 = centerHand.getCard(0);
			Card center2 = centerHand.getCard(1);
			Card center3 = centerHand.getCard(2);
			logWriter.print("Flop: " + center1.suitToString(c1.getSuit()) + " "
					+ center1.rankToString(center1.getRank()).toLowerCase() + ", ");
			logWriter.print(center2.suitToString(center2.getSuit()) + " "
					+ center2.rankToString(center2.getRank()).toLowerCase() + ", ");
			logWriter.println(center3.suitToString(center3.getSuit()) + " "
					+ center3.rankToString(center3.getRank()).toLowerCase());
		}
		if (event == 4) {
			logWriter.println(
					"River: " + c1.suitToString(c1.getSuit()) + " " + c1.rankToString(c1.getRank()).toLowerCase());
		}
		if (event == 5) {
			logWriter.println(
					"Turn: " + c1.suitToString(c1.getSuit()) + " " + c1.rankToString(c1.getRank()).toLowerCase());
		}
	}
	
	private String handType(Player player) {
		
		int rank = player.getHand().getScore() % 100;
		int type = player.getHand().getScore() - rank;
		String rankString;
		
		switch(rank) {
		case 2: rankString = "Two";
				break;
		case 3: rankString = "Three";
				break;
		case 4: rankString = "Four";
				break;
		case 5: rankString = "Five";
				break;
		case 6: rankString = "Sixe";
				break;
		case 7: rankString = "Seven";
				break;
		case 8: rankString = "Eight";
				break;
		case 9: rankString = "Nine";
				break;
		case 10: rankString = "Ten";
				break;
		case 11: rankString = "Jack";
				break;
		case 12: rankString = "Queen";
				break;
		case 13: rankString = "King";
				break;
		case 14: rankString = "Ace";
				break;
		default: rankString = "Two";
				break;
		}
		
		switch(type) {	
		case 1000 : return "A Royale Flush";
		case 900 : return ("A " + rankString + "High Stright Flush");
		case 800 : return ("A Four of a Kind with " + rankString + "s");
		case 700 : return ("A Full House,  " + rankString + "s High");
		case 600 : return ("A " + rankString + " High Flush");
		case 500 : return ("A " + rankString + " High Straight");
		case 400 : return ("A Three of a Kind with " + rankString + "s");
		case 300 : return ("Two Pair, " + rankString + "s High");
		case 200 : return ("A pair of  " + rankString + "s");
		case 100 : return (rankString + "s High");
		default : return (rankString + "s High");
		}
	}

	private int tieBreaker(Player player1, Player player2) {
		int player1HighCard;
		int player2HighCard;
		int player1Kicker;
		int player2Kicker;
		if (player1.getCard1().getRank() == 1) {

			player1HighCard = 14;
			player1Kicker = player1.getCard2().getRank();
		} 
		else if(player1.getCard2().getRank() == 1) {
			
			player1HighCard = 14;
			player1Kicker = player1.getCard1().getRank();
		}
		else if (player1.getCard1().getRank() > player1.getCard2().getRank()) {

			player1HighCard = player1.getCard1().getRank();
			player1Kicker = player1.getCard2().getRank();
		} 
		else {

			player1HighCard = player1.getCard2().getRank();
			player1Kicker = player1.getCard1().getRank();
		}
		if (player2.getCard1().getRank() == 1) {

			player2HighCard = 14;
			player2Kicker = player2.getCard2().getRank();
		} 
		else if(player2.getCard2().getRank() == 1) {
			
			player2HighCard = 14;
			player2Kicker = player2.getCard1().getRank();
		}
		else if (player2.getCard1().getRank() > player2.getCard2().getRank()) {

			player2HighCard = player2.getCard1().getRank();
			player2Kicker = player2.getCard2().getRank();
		} 
		else {

			player2HighCard = player2.getCard2().getRank();
			player2Kicker = player2.getCard1().getRank();
			
		}
		if(player1HighCard > player2HighCard) {
			
			return 1;
		}
		
		else if(player1HighCard < player2HighCard) {
			
			return 2;
		}
		else if(player1Kicker > player2Kicker) {
			
			return 1;
		}
		else if(player1Kicker < player2Kicker) {
			
			return 2;
		}
		else {
			
			return 0;
		}

	}
}
