import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;

public class Wordle {

	public static void main(String[] args) {

		DUDraw.setCanvasSize(500, 700);
		DUDraw.setXscale(0, 500);
		DUDraw.setYscale(0, 700);

		Scanner input = new Scanner(System.in);
		boolean userWon = false;
		boolean playAgain = false;


		Scanner fileReader = null;
		File dictionary = null;

		String word = null;

		ArrayList<String> words = new ArrayList<String>();

		try {
			dictionary = new File("Dictionary.txt");

			fileReader = new Scanner(dictionary);

			while(fileReader.hasNextLine()) {
				word = fileReader.nextLine();
				if(word.length() == 5) {
					words.add(word);
				}
			}
		}

		catch(FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		}

		do {
			userWon = false;
			
			DUDraw.clear();
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 5; j++) {
					DUDraw.square(50+(100*j), 150+(100*i), 30);
				}
			}
			
			//randomly generate a word, convert it to a char[] to access individual elements
			int randomIndex = (int) (Math.random()*words.size());
			String randomString = words.get(randomIndex);
			char[] stringArray = randomString.toCharArray();
			//System.out.println(randomString);

			for(int i = 1; i <= 6; i++) {
				String userInput = null; 
				do {
					System.out.println("Guess " + i + "/6: ");
					userInput = input.nextLine();

					if(userInput.length() != 5) {
						System.out.println("Pick a word with 5 letters.");
					}
					else if(!wordExists(userInput, words)) {
						System.out.println("This word is not in the word bank.");
					}

				} while(userInput.length() != 5 || !wordExists(userInput, words));

				char[] userInputArray = userInput.toCharArray();

				for(int j = 0; j < 5; j++) {

					if(contains(stringArray, userInputArray[j])) {
						DUDraw.setPenColor(DUDraw.YELLOW);
						DUDraw.filledSquare(50+(100*j), 750-(100*i), 30);
					}
					if(userInputArray[j] == stringArray[j]) {
						DUDraw.setPenColor(0, 255, 0);
						DUDraw.filledSquare(50+(100*j), 750-(100*i), 30);
					}

					DUDraw.setPenColor(DUDraw.BLACK);
					DUDraw.text(50+(100*j), 750-(100*i), "" + userInputArray[j]);
				}

				if(equals(userInputArray,stringArray)) {
					userWon = true;
					break;
				}
			}

			if(userWon) {
				DUDraw.text(250, 50, "You guessed the word!");
			}
			else if(!userWon) {
				DUDraw.text(250, 50, "Out of turns. The word was " + randomString);
			}

			System.out.println("Do you want to play again? Y or N: ");
			String play = input.nextLine();
			play = play.toUpperCase();

			if(play.equals("Y")) {
				playAgain = true;
			}
			else {
				playAgain = false;
			}
		} while(playAgain);
		
	}

	public static boolean contains(char[] a, char b) {
		boolean isLetterFound = false;

		for(int i = 0; i < a.length; i++) {
			if(a[i] == b) {
				isLetterFound = true;
			}
		}

		return isLetterFound;
	}

	public static boolean equals(char[] a, char[] b) {
		boolean equals = true;
		for(int i = 0; i < 5; i++) {
			if(a[i] != b[i]) {
				equals = false;
			}
		}
		return equals;
	}

	public static boolean wordExists(String word, ArrayList<String> list) {
		boolean wordExists = false;
		if(list.contains(word)) {
			wordExists = true;
		}
		return wordExists;
	}
}
