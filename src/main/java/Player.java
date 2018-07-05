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

	public void call() throws InterruptedException{
		TimeUnit.MILLISECONDS.sleep(560);
			
	}

	public int bet() throws InterruptedException{
		TimeUnit.MILLISECONDS.sleep(560);
		Random rand = new Random();
		int betAmt = rand.nextInt(201) + 10;
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
