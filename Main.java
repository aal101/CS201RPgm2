/*
Name: Anas Ali
Date: (Enter Today's Date)
Program: CS201R Program 2 – Games

Description:
This program displays a menu of games and allows
the user to choose which game to play. The program
continues until the user chooses Q to quit.
*/

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        System.out.println("\n\nWELCOME TO YOUR GAMES!!");
        Scanner scanInput = new Scanner(System.in);
        char choice;
        choice = menu(scanInput);

        while (choice != 'Q') {
            // test for choice type and call appropriate Game

            if (choice == '1') {
                Games.lotteryGame(scanInput);
            } else if (choice == '2') {
                Games.playCraps(scanInput);
            } else if (choice == '3') {
                Games.playScraps(scanInput);
            } else if (choice == '4') {
                Games.playRPS(scanInput);
            } else if (choice == '5') {
                Games.playRPSSpock(scanInput);
            } else if (choice == '6') {
                PlayBlackjack.playBlackjack(scanInput);
            } else if (choice == '7') {
                PlayHangman.playHangman(scanInput);
            } else {
                System.out.println("Invalid choice.");
            }

            choice = menu(scanInput);
        }

        scanInput.close();
        System.out.println("Thank you for playing!");
    }

    public static char menu(Scanner input) {
        char choice = 'Z';
        String inputString;

        // menu loop
        // print menu
        // get response & convert to upper case

        boolean valid = false;

        while (!valid) {

            System.out.println("\nChoose a Game:");
            System.out.println("1 - Lottery");
            System.out.println("2 - Craps");
            System.out.println("3 - Scraps");
            System.out.println("4 - Rock Paper Scissors");
            System.out.println("5 - Rock Paper Scissors Spock");
            System.out.println("6 - Blackjack");
            System.out.println("7 - Hangman");
            System.out.println("Q - Quit");

            inputString = input.nextLine().toUpperCase();

            if (inputString.length() > 0) {
                choice = inputString.charAt(0);

                if (choice == '1' || choice == '2' || choice == '3' ||
                        choice == '4' || choice == '5' || choice == '6' ||
                        choice == '7' || choice == 'Q') {

                    valid = true;
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            }
        }

        return choice;
    }
}