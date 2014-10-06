package assignment2;

public class Blackjack {
	// Create an int array for the player's hand
	static int[] playerHand = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
	// Create an int array for the dealer's hand
	static int[] dealerHand = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
	// Create a static DeckOfCards object that is used throughout the BlackJack class
	static DeckOfCards deck = new DeckOfCards();
	
	// Constructor for Blackjack object
	public Blackjack() {
	}
	/**
	 * setupNewGame method shuffles the deck, deals 2 cards to the player and the dealer
	 * and resets the rest of each player's hands to -1
	 */
	public static void setupNewGame() {
		
		// Assign a new DeckOfCards object each time
		deck = new DeckOfCards();
		
		// Shuffle the deck each time
		DeckOfCards.shuffle();
		
		// Deal 2 cards to player each time and reset rest of hand to -1
		playerHand[0] = DeckOfCards.drawCard();
		playerHand[1] = DeckOfCards.drawCard();
		playerHand[2] = -1;
		playerHand[3] = -1;
		playerHand[4] = -1;
		playerHand[5] = -1;
		playerHand[6] = -1;
		playerHand[7] = -1;
		playerHand[8] = -1;
		playerHand[9] = -1;
		playerHand[10] = -1;
		
		
		// Deal 2 cards to dealer each time and reset rest of hand to -1
		dealerHand[0] = DeckOfCards.drawCard();
		dealerHand[1] = DeckOfCards.drawCard();
		dealerHand[2] = -1;
		dealerHand[3] = -1;
		dealerHand[4] = -1;
		dealerHand[5] = -1;
		dealerHand[6] = -1;
		dealerHand[7] = -1;
		dealerHand[8] = -1;
		dealerHand[9] = -1;
		dealerHand[10] = -1;
	}
	
	/**
	 * The hit method adds a new card to the player's hand
	 */
	
	public static void hit() {
		int nextIndex = 0;
		/* Create a for loop to cycle through playerHand array and determine next index
		 * location to receive a card by locating next index location of -1 (i.e. no
		 * card stored at this index) */
		for (int i = 0; i < playerHand.length; i++) {
			if (playerHand[i] == -1) {
				nextIndex = i;
				break;
			}
		}
		// Invoke drawCard method from DeckOfCards class and assign it to next index location
		playerHand [nextIndex] = DeckOfCards.drawCard();
	}
	
	/**
	 * printHand method displays the rank and suit of each card in the hand passed to it
	 * @param hand takes in an int array for a hand
	 */
	public static void printHand (int[] hand) {
		// Create for loop to cycle through the int array passed to the method
		for (int i = 0; i < hand.length; i++) {
			/* Create if statement that only operates on index locations that are not -1 
			 * (i.e. where a card has been stored */
			if (hand [i] >= 0) {
				/* If index location contains a card then the rank and suit for that 
				 * card is determined and displayed by referencing the suits and hands 
				 * arrays from the DeckOfCards class */
				String suit = DeckOfCards.suits [hand[i] / 13];
				String rank = DeckOfCards.ranks[hand[i] % 13];
				System.out.print(rank + " of " + suit + ", ");	
			}
		}
	}
	
	/**
	 * calculateScore method calculates the score for the hand passed to it
	 * @param hand takes in an int array for a hand
	 * @return returns an int value for the score represented by a hand
	 */
	public static int calculateScore (int[] hand) {
		
		int totalScore = 0;
		int noOfAces = 0;
		
		// Create for loop to cycle through the int array passed to the method
		for (int i = 0; i < hand.length; i++) {
			/* Create for loop that only operates on index locations that are not -1 
			 * (i.e. where a card has been stored */
			if (hand[i] >= 0) {
				// Each card in the hand is assigned it's rank
				String rank = DeckOfCards.ranks[hand[i] % 13];
				
				int score = 0;
				
				/* Nested switch statement assigns the appropriate value to each card
				 * in the hand, and updates the total score of the hand by that value */
				switch (rank) {
					/* Value of Ace is taken to be 11 by default and noOfAces counter
					 * is incremented for each ace in the hand */
					case "Ace": score = 11; totalScore += score; noOfAces += 1;
						continue;
					case "2": score = 2; totalScore += score;
						continue;
					case "3": score = 3; totalScore += score;
						continue;
					case "4": score = 4; totalScore += score;
						continue;
					case "5": score = 5; totalScore += score;
						continue;
					case "6": score = 6; totalScore += score;
						continue;
					case "7": score = 7; totalScore += score;
						continue;
					case "8": score = 8; totalScore += score;
						continue;
					case "9": score = 9; totalScore += score;
						continue;
					case "10": score = 10; totalScore += score;
						continue;
					case "Jack": score = 10; totalScore += score;
						continue;
					case "Queen": score = 10; totalScore += score;
						continue;
					case "King": score = 10; totalScore += score;
						continue;
				}	
			}
		}
		/* Create if statement that if total score is greater then 21, reduces total 
		 * score for hand by 10 for each Ace in the hand */
		if (totalScore > 21) {
			for (int j = 1; j <= noOfAces; j++) {
				totalScore -= 10;
			}
		}
		return totalScore;
	}
	
	/**
	 * finishDealersPlay method continues to supply card to the dealer's hand until 
	 * the dealer reaches a score of 17
	 * @param hand takes in an int array
	 */
	public static void finishDealersPlay(int[] hand) {
		int totalDealerScore = calculateScore(hand);
		while (totalDealerScore < 17) {
			int nextIndex = 0;
			for (int i = 0; i < hand.length; i++) {
				if (hand[i] == -1) {
					nextIndex = i;
					break;
				}
			}
			hand[nextIndex] = DeckOfCards.drawCard();
			totalDealerScore = calculateScore(hand);
		}
	}
	
	/**
	 * isBlackJack boolean method determines if the hand passed to it is a blackjack 
	 * hand, i.e. one card is a picture card and other card is an Ace 
	 * @param hand takes in an int array
	 * @return returns true if a blackjack hand, false otherwise
	 */
	public static boolean isBlackjack (int[] hand) {
		String rankFirstCard = DeckOfCards.ranks[hand[0] % 13];
		String rankSecondCard = DeckOfCards.ranks[hand[1] % 13];
		
		if ((((rankFirstCard.equals("10")) || (rankFirstCard.equals("Jack")) || (rankFirstCard.equals("Queen"))        
			|| (rankFirstCard.equals("King"))) && (rankSecondCard.equals("Ace"))) || ((rankFirstCard.equals("Ace"))
				&& ((rankSecondCard.equals("10")) || (rankSecondCard.equals("Jack")) || 
					(rankSecondCard.equals("Queen")) || (rankSecondCard.equals("King"))))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * calculateWinnings method contains an if, if-else, else statement that determines
	 * if player is a winner, dealer is a winner, or if a draw
	 * @return returns int value 1 if player is winner, 0 if a draw, and -1 if dealer wins
	 */
	public static int calculateWinnings() {
		int totalPlayerScore = calculateScore(playerHand);
		int totalDealerScore = calculateScore(dealerHand);
		
		if (((totalPlayerScore <= 21) && (totalPlayerScore > totalDealerScore)) ||
			((totalPlayerScore <= 21) && (totalDealerScore > 21)) || ((isBlackjack(playerHand))
				&& (!isBlackjack(dealerHand)))) {
			return 1;
		}
		else if ((totalPlayerScore == totalDealerScore) || ((isBlackjack(playerHand))
			&& (isBlackjack(dealerHand)))) {
			return 0;
		}
		else {
			return -1;
		}
	}
}
