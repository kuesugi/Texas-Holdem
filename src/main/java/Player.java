
public class Player {
	
	private boolean isAi = false;
	private String name = "";
	private int money = 0;
	private Card card1 = null;
	private Card card2 = null;
	private Hand playerHand;
	
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
}
