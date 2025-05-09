import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

 //The Deck class represents a standard 52-card deck used in the WAR card game.
public class Deck {

    private ArrayList<Card> cards;

    /**
     * Constructs a new, unshuffled deck of 52 standard playing cards.
     * Also randomly inserts a few power-up cards.
     */
    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10",
                          "Jack", "Queen", "King", "Ace"};

        // Add standard cards
        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                String rank = ranks[i];
                int value = i + 2; // 2 -> 2, ..., Jack -> 11, Queen -> 12, King -> 13, Ace -> 14
                cards.add(new Card(suit, rank, value));
            }
        }

        // Inject a few power-up cards
        addPowerUpCards();
    }

     //Adds 2-4 random power-up cards to the deck.
    private void addPowerUpCards() {
        Random rand = new Random();
        int powerUpCount = rand.nextInt(3) + 2; // 2 to 4 power-up cards

        for (int i = 0; i < powerUpCount; i++) {
            String suit = "Power";
            String rank = "Boost " + (i + 1);
            int value = 20 + rand.nextInt(6); // Value between 20–25
            Card powerCard = new Card(suit, rank, value, true);
            cards.add(powerCard);
        }
    }

    //Shuffles the deck randomly.
    public void shuffle() {
        Collections.shuffle(cards);
    }

    
 //Deals half the deck (26 cards) for a two-player WAR game.
    public ArrayList<Card> dealHalf() {
        ArrayList<Card> halfDeck = new ArrayList<>();
        for (int i = 0; i < 26 && !cards.isEmpty(); i++) {
            halfDeck.add(cards.remove(0));
        }
        return halfDeck;
    }
}
