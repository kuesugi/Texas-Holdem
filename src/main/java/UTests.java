import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

class UTests {
	
	private Player player;
	private Hand hand;
	private ArrayList<Card> deck;
	
	public void setUp() {
		player = new Player(false, "Tester", 1000);
		hand = new Hand();
		deck = buildDeck();
		
	}

	@Test
	void testPlayerName() {
		setUp();
		assertEquals("Tester", player.getName());
	}
	@Test
	void testPlayerStack() {
		setUp();
		assertEquals(1000, player.getStack());
	}
	private static ArrayList<Card> buildDeck() {
		ArrayList<Card> deck = new ArrayList<>();
		// # of suits
		for (int i = 1; i < 5; i++) {
			// # of ranks
			for (int j = 1; j < 14; j++) {
				Card c = new Card(j, i);
				c.setCardIndex(i, j);
				deck.add(c);
			}
		}
		Collections.shuffle(deck);
		return deck;
	}

}
