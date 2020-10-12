/* Ari Takvorian
 * abt 734
 * 16175
 * Fall 2019
 */

package assignment2;

import assignment2.Player;
import java.util.Scanner;
import assignment2.SecretCodeGenerator;

public class Game {
	
	public boolean testingMode;
	Scanner scan; //scanner
	Player user; //class that contains History
	public String secretCode; //secret code
	public boolean win; //checks if game is won
	public boolean outOfGuesses; //checks for loss on guesses
	
	
	public Game(boolean testing, Scanner sc) {
		this.testingMode = testing;
		scan = sc;
		user = new Player();
		secretCode = SecretCodeGenerator.getInstance().getNewSecretCode();
		win = false;
		outOfGuesses = false;
	}
	
	
	public void runGame()
	{
		this.start(); //initial greeting
		while (!this.isGameOver()) {
			Guess current = this.getNextGuess(); //gets next valid guess
			current.processGuess(this.secretCode); //computes black and white peg amounts
			user.addToHistory(current); //add guess to user guess history
			win = current.checkWin(); //checks for win
		}
		this.endGame(); //processes end of game
	}
	
	public Guess getNextGuess() { //only exits once it receives a valid guess
		boolean validGuess = false;
		Guess current = new Guess();
		while (!validGuess) {
			System.out.println("\n\nYou have " + (GameConfiguration.guessNumber-user.guessesUsed) + " guesses left.");
			if (testingMode) {
				System.out.println("TESTING MODE ON - Secret Code: " + secretCode);
			}
			System.out.println("To see your guess history, type 'HISTORY'");
			System.out.println("What is your next guess?");
			System.out.println("Type in the characters for your guess and press enter.");
			System.out.println("Enter guess: ");
			
			Guess g = new Guess(this.scan.next());
			
			if (g.input.equals("HISTORY")) {
				user.printHistory();
			}
			else {
				if(!g.isLegalGuess()) {
					System.out.print("!!INVALID GUESS!!");
				}
				else {
					validGuess = true;
				}
			}
			current = g;
		}
		return current;
	}
	
	public boolean isGameOver() { //checks for end of game
		if (win) { //if there is a win
			return true;
		}
		
		if (!win && user.guessesUsed == GameConfiguration.guessNumber) { //check for guesses being out
			this.outOfGuesses = true;
			return true;
		}
		return false;
	}
	
	public void start() { //starts game
		System.out.print("\nGenerating secret code ...");
		if (this.testingMode) {
			System.out.print("\n\nTESTING MODE ON - Secret code: " + this.secretCode);
		}
	}

	public void endGame() { //processes end of game
		if (win) {
			System.out.print("\nCongratulations! You win!!");
		}
		else {
			System.out.print("\nSorry, you are out of guesses. You lose, boo-hoo.");
			System.out.print("\nThe correct code was: " + secretCode);
		}
	}
}
