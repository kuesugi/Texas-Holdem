import java.util.Random;

public class Player {
	
	private boolean isAI = false;
	private String name = "";
	private int stack = 1000;
	private Hand playerHand;
	private boolean ifFold = false;
	private boolean ifOut = false;
	
	public Player(boolean ai, String playerName, int playerMoney) {
		isAI = ai;
		name = playerName;
		stack = playerMoney;
		playerHand = new Hand();
	}
	
	public String getName() {
		return name;
	}
	
	public int getStack() {
		return stack;
	}
	
	public boolean getFold() {
		return ifFold;
	}

	public boolean isPlayerAi() {
		return isAI;
	}

	public Card getCard1(){
		return playerHand.getCard(0);
	}

	public Card getCard2(){
		return playerHand.getCard(1);
	}
	
	public void outOfGame() {
		ifOut = true;
	}

	public void setCard1(Card card){
		playerHand.addCard(card, 0);
	}

	public void setCard2(Card card){
		playerHand.addCard(card, 1);
	}

	public void setFold(){
		ifFold = true;
	}
	
	public void setStack(int newStack){
		stack = newStack;
	}

	public void call(){
		
			
	}

	public void bet(){
		
	}
	
	public void fold() {
		setFold();
	}
	
	public Hand getHand() {
		return playerHand;
	}
	
	public void clearHand() {
		
		playerHand = new Hand();
	}
	
	public boolean ifOutOfGame() {
		return ifOut;
	}
	// 0 - raise; 1 - fold; 2 - call;
	public String aiRandomAction(int round) {
		String action = "";
		Random rand = new Random();
		int moves = -1;
		
		// To determine the AI's action randomly
		// if not the first round
		if(round >= 1) {
			moves = rand.nextInt(3) + 1;
			if(moves == 2) {
				fold();
				action = name + " Has Folded.";
			}
			else if (moves == 3){
				call();
				action = name + " Has Called.";
			}
			else {}
		}
		// if in the first round
		else {
			moves = rand.nextInt(3);
			if(moves == 0) {
				// TODO: raised how much money
				action = name + " Has Raised ";
			}
			else if(moves == 1) {
				fold();
				action = name + " Has Folded.";
			}
			else {
				call();
				action = name + " Has Called.";
			}
		}
		// System.out.println(moves);
		return action;
	}
}
