import java.util.*;

public class PlayBlackjack {

    // PRE: accepts scanner input from main
    // POST: Setps up the deck for users
    // should loop while the player wants to play
    // selecting a random word from wordList & allowing the user
    // to guess letters to fill in the words (see assignment for more detail)
    public static void playBlackjack(Scanner scanner) {
        System.out.println("\nWELCOME TO BLACKJACK!!\n");

        boolean keepPlaying = true;

        while (keepPlaying) {

            List<Integer> deck = new ArrayList<>();

            // Initialize deck
            for (int i = 1; i <= 52; i++) {
                deck.add(i);
            }

            Collections.shuffle(deck);
            int deckIndex = 0;

            List<Integer> playerHand = new ArrayList<>();
            List<Integer> dealerHand = new ArrayList<>();

            // Deal initial two cards each
            playerHand.add(deck.get(deckIndex++));
            dealerHand.add(deck.get(deckIndex++));
            playerHand.add(deck.get(deckIndex++));
            dealerHand.add(deck.get(deckIndex++));

            // Player turn
            boolean playerBust = false;
            boolean playerTurn = true;

            while (playerTurn) {

                System.out.println("\nYour hand: " + getHandString(playerHand));
                System.out.println("Your total: " + getHandValue(playerHand));

                if (getHandValue(playerHand) > 21) {
                    System.out.println("You busted!");
                    playerBust = true;
                    break;
                }

                System.out.print("Would you like another card? (Y/N): ");
                String answer = scanner.nextLine();

                if (answer.equalsIgnoreCase("Y")) {
                    playerHand.add(deck.get(deckIndex++));
                } else {
                    playerTurn = false;
                }
            }

            // Dealer turn
            boolean dealerBust = false;

            if (!playerBust) {

                while (getHandValue(dealerHand) <= 17) {
                    dealerHand.add(deck.get(deckIndex++));
                }

                System.out.println("\nDealer hand: " + getHandString(dealerHand));
                System.out.println("Dealer total: " + getHandValue(dealerHand));

                if (getHandValue(dealerHand) > 21) {
                    System.out.println("Dealer busted!");
                    dealerBust = true;
                }
            }

            // Determine winner
            if (playerBust) {
                System.out.println("Dealer wins.");
            } else if (dealerBust) {
                System.out.println("You win!");
            } else {

                int playerTotal = getHandValue(playerHand);
                int dealerTotal = getHandValue(dealerHand);

                if (playerTotal > dealerTotal) {
                    System.out.println("You win!");
                } else if (dealerTotal > playerTotal) {
                    System.out.println("Dealer wins.");
                } else {
                    System.out.println("Push!");
                }
            }

            System.out.print("\nPlay again? (Y/N): ");
            String again = scanner.nextLine();
            if (!again.equalsIgnoreCase("Y")) {
                keepPlaying = false;
            }
        }

        System.out.println("Thanks for playing!");
    }

    // PRE: accepts the # of the card (0-51)
    // POST: determines the 'value' of the card based on position in the deck
    // NOTE: cards 2-10 are face value, 11-13 (J, Q, K) are worth 10 each
    public static int getCardValue(int cardNumber) {
        int faceValue = (cardNumber - 1) % 13 + 1;

        if (faceValue >= 10) {
            return 10;
        }

        return faceValue;
    }

    // PRE: accepts the card values for a hand
    // POST: returns the equivalent hand in string form ("Ace of Hearts")
    public static String getHandString(List<Integer> hand) {
        List<String> cards = new ArrayList<>();

        for (int card : hand) {
            cards.add(cardToString(card));
        }

        return String.join(", ", cards);
    }

    // PRE: Accepts the cardnumber
    // POST: Converts a card number to suit and face (e.g., "Ace of Hearts")
    public static String cardToString(int cardNumber) {
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        String[] faces = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };

        int faceIndex = (cardNumber - 1) % 13;
        int suitIndex = (cardNumber - 1) / 13;

        return faces[faceIndex] + " of " + suits[suitIndex];
    }

    // PRE: accepts the hand
    // POST: computes the Blackjack hand value with Ace handling (1 or 11)
    // can use the getCardValue method to do this
    // and returns the hand total (ex: if user has J and 10 the total is 20)
    public static int getHandValue(List<Integer> hand) {

        int total = 0;
        int aceCount = 0;

        for (int card : hand) {

            int value = getCardValue(card);

            if (value == 1) {
                total += 11;
                aceCount++;
            } else {
                total += value;
            }
        }

        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }

        return total;
    }
}