
public class Player {
	
	private boolean isAi = false;
	private String name = "";
	private int money = 0;
	private Card card1 = null;
	private Card card2 = null;
	
	public Player(boolean ai, String playerName, int playerMoney) {
		isAi = ai;
		name = playerName;
		money = playerMoney;
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
		return card1;
	}

	public Card getCard2(){
		return card2;
	}

	public void setCard1(Card card){
		card1 = card;
	}

	public void setCard2(Card card){
		card2 = card;
	}
}
