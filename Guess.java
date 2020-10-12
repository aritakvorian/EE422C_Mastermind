/* Ari Takvorian
 * abt 734
 * 16175
 * Fall 2019
 */

package assignment2;

public class Guess {
	public String input;
	public int black;
	public int white;
	
	public Guess() {
		input = "";
		black = 0;
		white = 0;
	}
	
	public Guess(String input) {
		this.input = input;
		black = 0;
		white = 0;
	}
		
	public boolean isLegalGuess() { //checks all non-history
		if (input.length() > GameConfiguration.pegNumber || input.length() < GameConfiguration.pegNumber) { //length check
			return false;
		}
		for (int i = 0; i <GameConfiguration.pegNumber; i++) { //check characters
			String character = String.valueOf(input.charAt(i));
			boolean exists = false;
			for (int j = 0; j<GameConfiguration.colors.length; j++) {
				String check = GameConfiguration.colors[j];
				if (character.equals(check)) {
					exists = true;
					break;
				}
			}
			if (!exists) {
				return false;
			}
		}
		
		return true;
	}

	public void processGuess(String secretCode) { //calculates black and white pegs
		int black = 0; 
		int white = 0;
		char[] codeChars = secretCode.toCharArray();
		char[] inputChars = this.input.toCharArray();
		
		for (int i = 0; i<GameConfiguration.pegNumber; i++) {
			if (inputChars[i] == codeChars[i]) {
				black++;
				codeChars[i] = '-';
				inputChars[i] = '/';
				continue;
			}
		}
		
		for (int i = 0; i <GameConfiguration.pegNumber; i++) {
			for (int j = 0; j<GameConfiguration.pegNumber; j++) {
				if (inputChars[i] == codeChars[j]) {
					white++;
					codeChars[j] ='-';
					inputChars[i] = '/';
					break;
				}
			}
		}
		this.black = black;
		this.white = white;
		this.printResut();
	}

	public void printResut() {
		System.out.print("\nResult: " + this.black + " black peg(s) and " + this.white + " white peg(s)");
	}
	
	public boolean checkWin() {
		if (this.black == GameConfiguration.pegNumber) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
