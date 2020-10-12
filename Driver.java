/* Ari Takvorian
 * abt 734
 * 16175
 * Fall 2019
 */

package assignment2;

import java.util.Scanner;
import assignment2.Game;
import assignment2.GameConfiguration;

public class Driver {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		boolean finished = false;
		while (!finished) {
			greeting();
			checkReady(scan);
			boolean testing = checkTestingMode(args);
			Game game = new Game(testing, scan);
			//Game game = new Game(true, scan);
			game.runGame();
			
			finished = askNextGame(scan);
		}
		System.out.println("\nThanks for playing! See you next time!");
	}
	
	public static void greeting() {
		System.out.println("Welcome to Mastermind.  Here are the rules.");
		System.out.println();
		System.out.print("The computer will think of a secret code. The code consists of " + GameConfiguration.pegNumber + " colored pegs. The pegs MUST be one of " + GameConfiguration.colors.length
				+ " colors: ");
		for (int i = 0; i < GameConfiguration.colors.length; i++) {
			
			if (i < GameConfiguration.colors.length -1) {
				System.out.print(GameConfiguration.colorNames[i]);
				System.out.print(", ");
			}
			else
			{
				System.out.print("or ");
				System.out.print(GameConfiguration.colorNames[i]);
				System.out.print(". ");
			}
		}
		
		System.out.print("\nA color may appear more than once in the code. You try to guess what colored pegs are in the code and what order they are in. "
				+ "\n\nAfter you make a valid guess, the result (feedback) will be displayed.");
		System.out.print("\nThe result consists of a black peg for each peg you have guessed exactly correct (color and position) in your guess. \nFor each peg in the guess that is the correct color, but is out of position, you get a white peg. For each peg that is fully incorrect, you get no feedback.");
		System.out.print("\n\nOnly the first letter of the color is displayed: ");
		System.out.print(GameConfiguration.colors[0] + " for " + GameConfiguration.colorNames[0] + ", " + GameConfiguration.colors[1] + " for " + GameConfiguration.colorNames[1] + ", and so forth.");
		System.out.print("\nWhen entering guesses, you only need to enter the first character of each color as a capital letter.");
		System.out.print("\n\nYou have " + GameConfiguration.guessNumber + " guesses to figure out the secret code or you lose the game. Are you ready to play? (Y/N): ");
	}
	
	public static void checkReady(Scanner scan) {
		boolean begin = false;
		String ready = "";
		while (!begin) {
			
			ready = scan.next();	
			
			if (ready.equals("N")) {
				System.out.println("\nUser not ready. See you next time!");
				System.exit(0);
			}
			
			else if (ready.equals("Y")) {
				begin = true;
			}
			
			else {
				System.out.println("\nPlease enter either a 'Y' or 'N':");
			}
		}
	}
	
	public static boolean checkTestingMode(String[] argument) {
		if (argument.length > 0) {
			if (argument[0].equals("1")) {
				return true;
			}
			return false;
		}
		else {
			return false;
		}

	}
	
	public static boolean askNextGame(Scanner scan) {
		System.out.println("\n\nAre you ready for another game (Y/N):");
		boolean begin = false;
		String ready = "";
		while (!begin) {
			
			ready = scan.next();	
			
			if (ready.equals("N")) {
				System.out.println("\nUser not ready. See you next time!");
				System.exit(0);
			}
			
			else if (ready.equals("Y")) {
				return false;
			}
			
			else {
				System.out.println("\nPlease enter either a 'Y' or 'N':");
			}
		}
		return false;
	}
	
}
