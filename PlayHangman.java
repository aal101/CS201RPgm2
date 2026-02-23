
//import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class PlayHangman {

    // PRE: accepts scanner input from main
    // POST: Reads words from an input file into the wordList
    // should loop while the player wants to play
    // selecting a random word from wordList & allowing the user
    // to guess letters to fill in the words (see assignment for more detail)
    public static void playHangman(Scanner input) {
        System.out.println("\nWELCOME TO HANGMAN!!\n");
        Random random = new Random();
        ArrayList<String> wordList = new ArrayList<>();

        // load words into the ArrayList
        try (Scanner fileScanner = new Scanner(new File("words.txt"))) {
            while (fileScanner.hasNextLine()) {
                String word = fileScanner.nextLine().trim().toLowerCase();
                if (!word.isEmpty()) {
                    wordList.add(word);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: words.txt file not found.");
            return;
        }

        if (wordList.isEmpty()) {
            System.out.println("The words.txt file is empty.");
            return;
        }

        boolean keepPlaying = true;

        System.out.println("Welcome to Hangman!");

        while (keepPlaying) {

            String chosenWord = wordList.get(random.nextInt(wordList.size()));
            char[] guessWord = new char[chosenWord.length()];
            Arrays.fill(guessWord, '?');

            int wrongGuesses = 0;
            int maxGuesses = 6;

            boolean wordGuessed = false;

            while (wrongGuesses < maxGuesses && !wordGuessed) {

                System.out.println("\nWord: " + new String(guessWord));
                System.out.println("Incorrect guesses remaining: " + (maxGuesses - wrongGuesses));
                System.out.print("Guess a letter: ");

                String guessInput = input.nextLine().toLowerCase();

                if (guessInput.length() == 0) {
                    continue;
                }

                char letter = guessInput.charAt(0);
                boolean correctGuess = false;

                for (int i = 0; i < chosenWord.length(); i++) {
                    if (chosenWord.charAt(i) == letter) {
                        guessWord[i] = letter;
                        correctGuess = true;
                    }
                }

                if (!correctGuess) {
                    wrongGuesses++;
                    System.out.println("Incorrect guess!");
                }

                if (chosenWord.equals(new String(guessWord))) {
                    wordGuessed = true;
                }
            }

            if (wordGuessed) {
                System.out.println("\nCongratulations! You guessed the word: " + chosenWord);
            } else {
                System.out.println("\nYou lost! The word was: " + chosenWord);
            }

            System.out.print("\nPlay again? (Y/N): ");
            String again = input.nextLine();
            if (!again.equalsIgnoreCase("Y")) {
                keepPlaying = false;
            }
        }

        System.out.println("Thanks for playing Hangman!");
    }
}