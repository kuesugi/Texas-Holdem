import java.util.ArrayList;

public class Hand {

	private ArrayList<Card> hand;
	private int score;

	
	public Hand(Card card1, Card card2) {

		hand = new ArrayList<>();
		hand.add(card1);
		hand.add(card2);
		score = 0;
	}

	public void addCard(Card newCard) {

		hand.add(newCard);
	}
	//Main method for checking and returning scores
	public int checkScore() {

		//score of 1000
		if (checkRoyalFlush() != 0) {

			score = checkRoyalFlush();
			return score;
		}
		
		//score of 900 + high rank (accept ace, as that would be a royal flush)
		else if(checkStraightFlush() != 0) {
			
			score = checkStraightFlush();
			return score;
		}
		
		//score of 800 + rank
		else if(checkFourOfAKind() != 0) {
			
			score = checkFourOfAKind();
			return score;
		}
		
		//score of 700 + rank of three of a kind
		else if(checkFullHouse() != 0) {
			
			score = checkFullHouse();
			return score;
		}
		
		//score of 600 + high rank
		else if(checkFlush() != 0) {
			
			score = checkFlush() ;
			return score;
		}
		
		//score of 500 + high card
		else if(checkStraight() != 0) {
			
			score = checkStraight() ;
			return score;
		}
		
		//score of 400 + rank
		else if(checkThreeOfAKind() != 0) {
			
			score = checkThreeOfAKind() ;
			return score;
		}
		
		//score of 300 + highest rank of pair
		else if(checkTwoPair() != 0) {
			
			score = checkTwoPair() ;
			return score;
		}
		
		//score of 200 + rank
		else if(checkPair(0) != 0) {
			
			score = checkPair(0) ;
			return score;
		}
		
		//score of 100 + high card
		else {
			
			score = checkHighCard();
			return score;
		}
		
	}

	public int checkRoyalFlush() {

		int suit = 0;
		for (int i = 0; i < hand.size(); i++) {

			//searches for an ace, than sets the royal flush rank accordingly
			if (hand.get(i).getRank() == 1) {

				suit = hand.get(i).getSuit();
				for (i = 0; i < hand.size(); i++) {
					
					//we proceed to search the rest of the hand for the cards needed in the suit needed
					if (hand.get(i).getRank() == 10 && hand.get(i).getSuit() == suit) {

						for (i = 0; i < hand.size(); i++) {

							if (hand.get(i).getRank() == 11 && hand.get(i).getSuit() == suit) {

								for (i = 0; i < hand.size(); i++) {

									if (hand.get(i).getRank() == 12 && hand.get(i).getSuit() == suit) {

										for (i = 0; i < hand.size(); i++) {

											if (hand.get(i).getRank() == 13 && hand.get(i).getSuit() == suit) {

												return 1000;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return 0;

	}

	public int checkStraightFlush() {

		int suit = 0;
		int rank = 0;

		for (int i = 0; i < hand.size(); i++) {

			rank = hand.get(i).getRank();
			suit = hand.get(i).getSuit();
			//similar sorting to the royal flush, except the starting card is irrelevant
			for (i = 0; i < hand.size(); i++) {

				if (hand.get(i).getRank() == rank + 1 && hand.get(i).getSuit() == suit) {

					for (i = 0; i < hand.size(); i++) {

						if (hand.get(i).getRank() == rank + 2 && hand.get(i).getSuit() == suit) {

							for (i = 0; i < hand.size(); i++) {

								if (hand.get(i).getRank() == rank + 3 && hand.get(i).getSuit() == suit) {

									for (i = 0; i < hand.size(); i++) {

										//if we were to happen to get a card ending on an ace, we would have a royal flush
										//therefore, the straight flush cannot end with a 14 score
										if (hand.get(i).getRank() == rank + 4 && hand.get(i).getSuit() == suit) {

											return 900 + (rank + 4);
										} 
									}
								}
							}
						}
					}
				}
			}
		}

		return 0;
	}

	public int checkFourOfAKind() {

		int rank = 0;

		for (int i = 0; i < hand.size(); i++) {

			rank = hand.get(i).getRank();
			int count = 0;
			//we check a card rank, and see if we can find four of them in the deck
			for (int x = 0; x < hand.size(); x++) {

				if (hand.get(x).getRank() == rank && x != i) {

					count++;
				}

				if (count == 3) {

					if (rank == 1) {

						return 814;
					} else {

						return 800 + rank;
					}
				}
			}
		}
		return 0;
	}

	public int checkThreeOfAKind() {

		int rank = 0;

		for (int i = 0; i < hand.size(); i++) {

			rank = hand.get(i).getRank();
			int count = 0;
			for (int x = 0; x < hand.size(); x++) {

				if (hand.get(x).getRank() == rank && x != i) {

					count++;
				}

				if (count == 2) {

					if (rank == 1) {

						return 414;
					} else {

						return 400 + rank;
					}
				}
			}
		}
		return 0;
	}

	public int checkPair(int bRank) {

		//we add in checking for a bad rank for looking
		//at full houses and two pairs, to make sure we don't repeat
		int badRank = bRank;
		int rank = 0;

		for (int i = 0; i < hand.size(); i++) {

			rank = hand.get(i).getRank();

			if (rank != badRank) {

				int count = 0;
				for (int x = 0; x < hand.size(); x++) {

					if (hand.get(x).getRank() == rank && x != i) {

						count++;
					}

					if (count == 1) {

						if (rank == 1) {

							return 214;
						} else {

							return 200 + rank;
						}
					}
				}
			}
		}
		return 0;
	}

	public int checkHighCard() {

		int rank = hand.get(0).getRank();

		for (int i = 1; i < hand.size(); i++) {

			if (hand.get(i).getRank() > rank) {

				rank = hand.get(i).getRank();
			}
		}

		if (rank == 1) {

			return 114;
		}

		else {

			return rank;
		}
	}

	public int checkFullHouse() {

		int threeKind = checkThreeOfAKind();
		int pair = 0;
		if(threeKind != 0) {
			
			threeKind = threeKind -400;
			if(threeKind == 14) {
				
				threeKind = 1;
			}
			pair = checkPair(threeKind);
			
			if(pair != 0) {
				if (threeKind == 1) {
					
					threeKind = 14;
				}
				return (threeKind)+ 700;
			}
			else {
				
				return 0;
			}

		}
		else {
			
			return 0;
		}
	}
	
	public int checkFlush() {

		int suit = 0;
		int rank = 0;

		for (int i = 0; i < hand.size(); i++) {

			suit = hand.get(i).getSuit();
			if(hand.get(i).getRank() == 1) {
				
				rank = 14;
			}
			rank = hand.get(i).getRank();
			int count = 0;
			for (int x = 0; x < hand.size(); x++) {

				if (hand.get(x).getSuit() == suit && x != i) {

					if(hand.get(x).getRank() > rank) {
						
						rank = hand.get(x).getRank();
					}
					
					if(hand.get(x).getRank() == 1) {
						
						rank = 14;
					}
					count++;
				}

				if (count == 4) {
					
					if(rank == 1) {
						
						return 614;
					}
					
					else {
						
						return 600 + rank;
					}

				}
			}
		}
		return 0;
	}
	
	public int checkStraight() {

		int rank = 0;

		for (int i = 0; i < hand.size(); i++) {

			rank = hand.get(i).getRank();

			for (i = 0; i < hand.size(); i++) {

				if (hand.get(i).getRank() == rank + 1) {

					for (i = 0; i < hand.size(); i++) {

						if (hand.get(i).getRank() == rank + 2) {

							for (i = 0; i < hand.size(); i++) {

								if (hand.get(i).getRank() == rank + 3) {

									for (i = 0; i < hand.size(); i++) {

										if (hand.get(i).getRank() == rank + 4) {

											return 500 + (rank + 4);
										} 
										else if ((rank + 4) == 14) {

											if (hand.get(i).getRank() == 1) {

												return 514;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return 0;
	}
	
	int checkTwoPair() {
		
		int pair1 = checkPair(0);
		int pair2 = 0;
		if(pair1 != 0) {
			
			pair2 = checkPair(pair1-200);
			
			if(pair2 != 0) {
				
				if(pair1 > pair2) {
					
					return (pair1-200)+ 300;
				}
				else {
					
					return (pair2-200)+ 300;
				}
			}
			else {
				
				return 0;
			}

		}
		else {
			
			return 0;
		}
	}
}
