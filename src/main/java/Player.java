import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Player {
	
	private boolean isAI = false;
	private String name = "";
	private int stack = 1000;
	private Hand playerHand;
	private boolean ifFold = false;
	private boolean ifOut = false;
	private boolean allIn = false;
	private boolean hasGone = false;
	
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
	
	public boolean isAllIn() {
		return allIn;
	}

	public boolean isPlayerAi() {
		return isAI;
	}
	
	public void playerHasGone() {
		
		hasGone = true;
	}
	
	public void allIn() {
		
		allIn = true;
	}
	
	public void newRoundUnFold() {
		
		ifFold = false;
	}
	public boolean hasGone() {
		
		return hasGone;
	}
	
	public void newRoundNotGone() {
		
		hasGone = false;
	}
	
	public void newRoundNotAllIn() {
		
		allIn = false;
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

	public void call(int highBet) throws InterruptedException{
		
		stack -= highBet; 	
	}

	public int bet(int highBet) throws InterruptedException{
		Random rand = new Random();
		if(highBet == 0) {
			highBet = 10;
		}
		int betAmt = rand.nextInt(201) + highBet;
		stack = stack - betAmt;
		if(stack == 0) allIn = true;
		return betAmt;
	}
	
	public void blind(int betAmt) throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(560);
		stack -= betAmt;
		if(stack == 0)
			allIn = true;
	}
	
	public void fold() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(560);
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
}
