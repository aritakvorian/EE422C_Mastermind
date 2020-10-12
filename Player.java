/* Ari Takvorian
 * abt 734
 * 16175
 * Fall 2019
 */

package assignment2;

import assignment2.Guess;

public class Player {
	public String guessList[];
	public String results[];
	public int guessesUsed;
	
	public Player() {
		this.guessList= new String[GameConfiguration.guessNumber];
		this.results = new String[GameConfiguration.guessNumber];
		this.guessesUsed = 0;
	}
	
	public void addToHistory(Guess currentguess) {
		guessList[this.guessesUsed] = currentguess.input;
		String format = currentguess.black+ "B_" + currentguess.white + "W";
		results[this.guessesUsed] = format;
		this.guessesUsed++;
	}
	
	public void printHistory(){
		if (this.guessesUsed == 0) {
			System.out.println("You have not made any guesses");
		}
		else {
			for (int i = 0; i<this.guessesUsed; i++) {
				System.out.print("\n" + this.guessList[i]);
				System.out.print("		" + this.results[i]);
			}
		}
	}
}
