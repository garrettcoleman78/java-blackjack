package assignment2;

public class DeckOfCards {
	// Create deck int array with 52 index locations
	static int[] deck = new int[52];
	// Create string array of suits
	static String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
	// Create string array of ranks
	static String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10",
		"Jack", "Queen", "King", };
	// Create int variable drawNo
	static int drawNo = 0;
	
	// Constructor
	public DeckOfCards(){
		// Initialise cards
		for (int i = 0; i < deck.length; i++) {
			deck[i] = i;
		}
	}
	
	/**
	 * shuffle method shuffles the deck by cycling though the deck and swapping the value
	 * at each index location with the value at another randomly generated index location
	 */
	public static void shuffle(){
		for (int i = 0; i < deck.length; i++){
			int index = (int)(Math.random() * deck.length);
			int temp = deck[i];
			deck[i] = deck[index];
			deck[index] = temp;
		}
	}
	
	/**
	 * drawCard method retrieves the value in the deck array at the next drawNo index 
	 * location. drawNo is incremented each time drawCard method is invoked to ensure
	 * the same card is not returned twice
	 * @return the int value at the next index location in the deck array
	 */
	public static int drawCard() {
		int newCard = deck[drawNo];
		drawNo ++;
		return newCard;	
	}
	
}

