
public class TestWin {

	public static void main(String[] args) {
		int score = 0;
		
		Card card1 = new Card(4,1);
		Card card2 = new Card(11,1);
		
		Card card3 = new Card(5,2);
		Card card4 = new Card(1,2);
		Card card5 = new Card(3,2);
		Card card6 = new Card(2,2);
		Card card7 = new Card(1,4);
		//Card card8 = new Card(5,3);
		//Card card9 = new Card(10,1);
		Hand user = new Hand();
		Hand hand1 = new Hand();
		
		user.addCard(card1);
		user.addCard(card2);
		hand1.addCard(card5);
		hand1.addCard(card6);
		hand1.addCard(card7);
		hand1.addCard(card3);
		hand1.addCard(card4);
		
		//System.out.println(user.checkScore(hand1, false));

	}

}
