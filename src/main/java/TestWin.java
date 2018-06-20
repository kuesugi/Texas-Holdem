
public class TestWin {

	public static void main(String[] args) {
		int score = 0;
		Card card1 = new Card(7,1);
		Card card2 = new Card(3,2);
		Card card3 = new Card(13,1);
		Card card4 = new Card(9,4);
		Card card5 = new Card(1,1);
		Card card6 = new Card(4,3);
		Card card7 = new Card(3,10);
		Hand user = new Hand();
		Hand hand1 = new Hand();
		
		user.addCard(card1);
		user.addCard(card2);
		hand1.addCard(card5);
		hand1.addCard(card6);
		hand1.addCard(card7);
		hand1.addCard(card3);
		hand1.addCard(card4);
		
		System.out.println(user.checkScore(hand1, false));

	}

}
