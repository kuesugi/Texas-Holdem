
public class Player {
	
	private boolean isAi = false;
	private String name = "";
	private int money = 0;
	
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

}
