/**
 * Author: Garrett Coleman
 * Student No.: 96344598
 * Course: MSc Computer Science (Conversion) 2013-2014 
 * Module: Introduction to Java Programming
 * Lecturer: Mark Scanlon
 * Assignment 2, Question 4 
 * Date: 28 Nov 2013
 * This program simulates a game of blackjack
 */

package assignment2;

public class TestBlackjack {
	public static void main(String[] args) {
		int[] playerGameHand;
		int[] dealerGameHand;
		char anotherGame = 'a';
		
		// Set up the player's starting bank
		int bank = 100;
		// Create a scanner object
		java.util.Scanner input = new java.util.Scanner(System.in);
				
		// Create do-while loop that executes code for game until user says no
		do {
			// Prompt user to enter a bet
			System.out.println("Your current balance is: €" + bank);
			System.out.print("Bets can be made in €5 increments up to a max of €25. "
				+ "Please enter a bet : ");
			String s = input.nextLine();
			// Parse the string input to an integer
			int bet = Integer.parseInt(s);
	        // Create while loop to ensure that a valid bet is input
	        while (bet %5 != 0 || bet > 25 || bet/5==0) {
				System.out.print("Please enter a valid stake: ");
				s = input.nextLine();
		        bet = Integer.parseInt(s);
				}
			
			System.out.println("");
			
			int playerScore = 0;
			int dealerScore = 0;
			
			Blackjack currGame = new Blackjack();
			
			/* Invoke setupNewGame method from Blackjack class whereby a deck is
			 * shuffled and two cards are dealt to both the dealer and the player */
			Blackjack.setupNewGame();
			
			/* Assign dealerHand created with setupNewGame method to dealerGameHand 
			 * variable and display dealer hand and score after game is setup */
			dealerGameHand = Blackjack.dealerHand;
			System.out.print("\nDealer hand: ");
			Blackjack.printHand(dealerGameHand);
			dealerScore = Blackjack.calculateScore(dealerGameHand);
			System.out.print("\nHouse score is: " + dealerScore);
			
			/* Assign playerHand created with setupNewGame method to playerGameHand 
			 * variable and display player hand and score after game is setup */
			playerGameHand = Blackjack.playerHand;
			System.out.print("\nPlayer hand: ");
			Blackjack.printHand(playerGameHand);
			playerScore = Blackjack.calculateScore(playerGameHand);
			System.out.print("\nPlayer score is: " + playerScore);
			
			/* Create if, else-if, else-if else statement. If statement and two if-else 
			 * statements determine if hand is won after first 2 cards are dealt 
			 * (i.e. in case of blackjack). 
			 * Else statement determines actions & results when further cards are dealt. 
			 * If statement invokes isBlackjack method from Blackjack class and declares
			 * House to be winner if house has blackjack and player doesn't and bank 
			 * balance is displayed reduced by amount of bet */
			if ((Blackjack.isBlackjack(dealerGameHand)) && 
				(!Blackjack.isBlackjack(playerGameHand))) {
				System.out.println("\nBlackjack! House wins");
				bank -= bet;
				System.out.println("\nYour current balance is " + bank);
			}
			
			/* Else-if statement invokes isBlackjack method from Blackjack class and 
			 * declares player to be winner if player has blackjack and player doesn't 
			 * and bank balance is displayed increased by amount of bet */
			else if ((Blackjack.isBlackjack(playerGameHand)) && 
				(!Blackjack.isBlackjack(dealerGameHand))) {
				System.out.println("\nBlackjack! Player wins");
				bank += bet;
				System.out.println("\nYour current balance is " + bank);
			}

			/* Else-if statement invokes isBlackjack method from Blackjack class and 
			 * declares draw to be winner if both player and dealer have blackjack and 
			 * player doesn't and bank balance is displayed reduced by amount of bet */
			else if ((Blackjack.isBlackjack(playerGameHand)) && 
				(Blackjack.isBlackjack(dealerGameHand))) {
				System.out.println("\nDraw");
				System.out.println("\nYour current balance is " + bank);
			}
					
			//Else statement determines actions & results when further cards are dealt.
			else {
				// Prompt player to take another card or to stand on dealt cards
				System.out.println("\nEnter h to hit or s to stand> ");		
				char playerMove = input.nextLine().charAt(0);
				
				// Create nested if statement for if player takes another card
				if (playerMove == 'h') {
					/* Create nested do-while loop that will continue to issue a new 
					 * card to player as long as player hits */
					do {	
						// Invoke hit method from blackjack class that issues a card to player
						Blackjack.hit();
						
						// Display dealer hand and score after each hit to player
						System.out.print("\nDealer hand: ");
						Blackjack.printHand(dealerGameHand);
						dealerScore = Blackjack.calculateScore(dealerGameHand);
						System.out.print("\nHouse score is: " + dealerScore);
						
						// Display player hand and score after each hit to player
						System.out.print("\nPlayer hand: ");
						Blackjack.printHand(playerGameHand);
						playerScore = Blackjack.calculateScore(playerGameHand);
						System.out.print("\nPlayer score is: " + playerScore);
						
						/* Create nested if statement for if player's score is > 21
						 * after a hit, in which case "House wins" message is 
						 * displayed, bank balance is displayed reduced by amount of 
						 * bet, and do-while loop for player hitting is broken */
						if (playerScore > 21) {
							System.out.println("\nHouse wins");
							bank -= bet;//decrease the bank by the value of the original bet
							System.out.println("\nYour current balance is " + bank);
							break;
						}
						/* Create nested else-if statement for if player's score is 21
						 * after a hit, in which case do-while loop for player hitting 
						 * is broken */
						else if (playerScore == 21){
							break;
						}
						/* Create nested else statement that prompts player to hit or 
						 * stand if their score is < 21 (i.e. if and if-else statements
						 * above are false)  */
						else {
							System.out.println("\nEnter h to hit or s to stand> ");		
							playerMove = input.nextLine().charAt(0);
						}
			
					// Do-while loop iterates as long as player chooses to hit
					} while (playerMove == 'h');
				}
				/* Create nested if statement that will carry out operations for
				 * dealer's play if player chooses to stand or if player's score is 21 */
				if ((playerMove == 's') || (playerScore == 21)) {
				
					/* Pass dealer's hand to finishDealersPlay method from Blackjack
					 * class which deals card to the dealer until the dealer's score
					 * reaches 17 or more */
					Blackjack.finishDealersPlay(dealerGameHand);
					
					/* Invoke calculateWinnings method from Blackjack class that returns an
					 * integer value determined by who has won or if there is a draw, which is
					 * assigned to the integer variable 'result' */
					int result = Blackjack.calculateWinnings();
					
					// Display dealer's hand and calculate and display dealer's score
					System.out.print("\nDealer hand: ");
					Blackjack.printHand(dealerGameHand);
					dealerScore = Blackjack.calculateScore(dealerGameHand);
					System.out.print("\nHouse score is: " + dealerScore);
					
					// Display player's hand and score
					System.out.print("\nPlayer hand: ");
					Blackjack.printHand(playerGameHand);
					System.out.print("\nPlayer score is: " + playerScore);
					
					/* Create nested if, else-if, else statement that displays winner,
					 * updates bank balance and displays bank balance, depending on the
					 * value of the result variable (which was earlier returned by the
					 * calculateWinnings method from the Blackjack class */
					if (result == 1) {
						System.out.println("\nPlayer wins");
						bank += bet;
						System.out.println("\nYour current balance is " + bank);
					}
					else if (result == -1) {
						System.out.println("\nHouse Wins");
						bank -= bet;//decrease the bank by the value of the original bet
						System.out.println("\nYour current balance is " + bank);
					}
					else if (result == 0) {
						System.out.println("\nDraw");
						System.out.println("\nYour current balance is " + bank);
					}	
				}
			}
			// Invite player to play another hand
			System.out.print("\nDo you want to play again? Enter y or n> ");
			anotherGame = input.nextLine().charAt(0);
		
		// Continue do-while loop if player enters y and bank balance is > 0
		} while (anotherGame == 'y' && bank > 0);
		
		// Close scanner object
		input.close();	
	}		
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
