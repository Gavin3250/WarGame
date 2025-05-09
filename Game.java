import java.util.Scanner;
import java.util.Random;

/**
 * The Game class controls the flow of the WAR card game.
 * It manages rounds, player input, and checks for game over conditions.
 */
public class Game {

    private Player player1;
    private Player player2;
    private boolean isGameOver;
    private Scanner scanner;
    private int roundCount = 0; // Track number of rounds played

    
     //Constructs a Game with two players and initializes input scanner.
    public Game() {
        this.isGameOver = false;
        this.scanner = new Scanner(System.in);
    }

    
     // Starts the WAR card game by initializing players and distributing cards.
    public void startGame() {
        System.out.println("Welcome to the WAR card game!");

        // Create and shuffle the deck
        Deck deck = new Deck();
        deck.shuffle();

        // Deal cards to two players
        player1 = new Player("Player 1", deck.dealHalf());
        player2 = new Player("Player 2", deck.dealHalf());

        System.out.println("Cards have been dealt. Let the WAR begin!");

        // Start game loop
        while (!isGameOver) {
            playRound();
        }

        scanner.close();
    }

    // Plays a single round of WAR. Tracks Round for a method
    private void playRound() {
    roundCount++;

    boolean doubleOrNothing = false;

    // Ask Double or Nothing method
    if (roundCount % 8 == 0) {
        System.out.print("Round " + roundCount + ": Double or Nothing? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        if (response.equals("yes")) {
            doubleOrNothing = true;
            System.out.println("Double or Nothing accepted! Winner steals 2 extra cards!");
        } else {
            System.out.println("Double or Nothing declined.");
        }
    }

    // Wait for the player to hit enter to start the round
    System.out.println("\nPress Enter to play the round...");
    scanner.nextLine();

    // Each player plays top card
    Card card1 = player1.playCard();
    Card card2 = player2.playCard();

    // If either player runs out of cards, end the game
    if (card1 == null || card2 == null) {
        checkGameOver();
        return;
    }

    // Show the cards that were played
    System.out.println(player1.getName() + " plays: " + card1);
    System.out.println(player2.getName() + " plays: " + card2);

    // Start with base values
    int value1 = card1.getValue();
    int value2 = card2.getValue();

    Random rand = new Random();

    // Apply power-up effect if during accurate round
    if (card1.isPowerUp()) {
        int boost = rand.nextInt(20) + 1; // Random boost 1–20
        value1 += boost;
        System.out.println(player1.getName() + "'s POWER-UP card gets +" + boost + " value!");
    }

    if (card2.isPowerUp()) {
        int boost = rand.nextInt(20) + 1;
        value2 += boost;
        System.out.println(player2.getName() + "'s POWER-UP card gets +" + boost + " value!");
    }

    // Determine the winner of the round based on card values
    if (value1 > value2) {
        System.out.println(player1.getName() + " wins the round!");
        player1.addCards(card1, card2); // Winner takes the cards
        if (doubleOrNothing) {
            // Transfer 2 extra cards from the loser
            transferExtraCards(player1, player2);
        }
    } else if (value2 > value1) {
        System.out.println(player2.getName() + " wins the round!");
        player2.addCards(card1, card2); // Winner takes the cards
        if (doubleOrNothing) {
            // Transfer 2 extra cards from the loser
            transferExtraCards(player2, player1);
        }
    } else {
        System.out.println("It's a tie! Each card is discarded.");
        // No cards are added to the winners' decks
    }

    checkGameOver();
    }

    
    
    //Transfers up to 2 extra cards from the loser's deck to the winner's deck.
    private void transferExtraCards(Player winner, Player loser) {
    for (int i = 0; i < 2; i++) {
        Card extraCard = loser.playCard();
        if (extraCard != null) {
            winner.addCards(extraCard);
            System.out.println(winner.getName() + " steals an extra card: " + extraCard);
        } else {
            System.out.println(loser.getName() + " has no more cards to steal.");
            break;
        }
    }
    }      



    
    //Plays a standard one-card round of WAR.
    private void playNormalRound() {
        Card card1 = player1.playCard();
        Card card2 = player2.playCard();

        if (card1 == null || card2 == null) return;

        System.out.println(player1.getName() + " plays: " + card1);
        System.out.println(player2.getName() + " plays: " + card2);

        int value1 = getCardValue(card1, player1.getName());
        int value2 = getCardValue(card2, player2.getName());

        if (value1 > value2) {
            System.out.println(player1.getName() + " wins the round!");
            player1.addCards(card1, card2);
        } else if (value2 > value1) {
            System.out.println(player2.getName() + " wins the round!");
            player2.addCards(card1, card2);
        } else {
            System.out.println("It's a tie! Each card is discarded.");
        }
        }


     //Plays a special two-card round of WAR.
    private void playSpecialRound() {
        Card card1a = player1.playCard();
        Card card1b = player1.playCard();
        Card card2a = player2.playCard();
        Card card2b = player2.playCard();

        if (card1a == null || card1b == null || card2a == null || card2b == null) return;

        System.out.println(player1.getName() + " plays: " + card1a + " and " + card1b);
        System.out.println(player2.getName() + " plays: " + card2a + " and " + card2b);

        int value1 = getCardValue(card1a, player1.getName()) + getCardValue(card1b, player1.getName());
        int value2 = getCardValue(card2a, player2.getName()) + getCardValue(card2b, player2.getName());

        if (value1 > value2) {
            System.out.println(player1.getName() + " wins the SPECIAL round!");
            player1.addCards(card1a, card1b);
            player1.addCards(card2a, card2b);
        } else if (value2 > value1) {
            System.out.println(player2.getName() + " wins the SPECIAL round!");
            player2.addCards(card1a, card1b);
            player2.addCards(card2a, card2b);
        } else {
            System.out.println("It's a tie! All four cards are discarded.");
        }
    }

    
     //Applies power-up logic and returns the modified card value.
    private int getCardValue(Card card, String playerName) {
        int baseValue = card.getValue();
        if (card.isPowerUp()) {
            int boost = new Random().nextInt(20) + 1;
            System.out.println(playerName + "'s POWER-UP card (" + card + ") gets +" + boost + " value!");
            return baseValue + boost;
        }
        return baseValue;
    }

    
     // Checks if the game is over and announces the winner.
    private void checkGameOver() {
        if (player1.getDeckSize() == 0 || player2.getDeckSize() == 0) {
            isGameOver = true;
            System.out.println("\nGame Over!");

            if (player1.getDeckSize() > player2.getDeckSize()) {
                System.out.println(player1.getName() + " wins the game!");
            } else if (player2.getDeckSize() > player1.getDeckSize()) {
                System.out.println(player2.getName() + " wins the game!");
            } else {
                System.out.println("It's a draw!");
            }
        }
    }
}
