
public class Player {
	
	private boolean isAi = false;
	private String name = "";
	private int money = 0;
	private int stack = 1000;
	private Hand playerHand;
	private boolean ifFold = false;
	
	public Player(boolean ai, String playerName, int playerMoney) {
		isAi = ai;
		name = playerName;
		money = playerMoney;
		playerHand = new Hand();
	}
	
	public  String getName() {
		return name;
	}
	
	public int getMoney() {
		return money;
	}
	
	public int getStack() {
		return stack;
	}
	
	public boolean getFold() {
		return ifFold;
	}

	public boolean isPlayerAi() {
		return isAi;
	}

	public Card getCard1(){
		return playerHand.getCard(0);
	}

	public Card getCard2(){
		return playerHand.getCard(1);
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

	public void call(){
		
	}

	public void bet(){
		
	}
}
