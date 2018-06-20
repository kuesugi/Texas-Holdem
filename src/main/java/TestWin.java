
public class TestWin {

	public static void main(String[] args) {
		int score = 0;
		Card card1 = new Card(2,1);
		Card card2 = new Card(7,1);
		Card card3 = new Card(13,1);
		Card card4 = new Card(9,1);
		Card card5 = new Card(1,1);
		Card card6 = new Card(4,3);
		Card card7 = new Card(3,10);
		Hand hand1 = new Hand(card1,card2,card3);
		
		hand1.addCard(card3);
		hand1.addCard(card4);
		hand1.addCard(card5);
		hand1.addCard(card6);
		hand1.addCard(card7);
		
		System.out.println(score);

	}

}
