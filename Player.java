import java.util.ArrayList;
import java.util.Collections;


 //The Player class represents a player in the WAR card game.
public class Player {

    private String name;
    private ArrayList<Card> deck;

    
     //Constructs a new Player with a name and a starting deck.
    public Player(String name, ArrayList<Card> startingDeck) {
        this.name = name;
        this.deck = startingDeck;
    }

     // Gets/returns the player's name.
    public String getName() {
        return name;
    }

    
      //Plays (removes) the top card from the player's deck.
     //return the card played, or null if no cards are left
    public Card playCard() {
        if (deck.isEmpty()) {
            return null;
        }
        return deck.remove(0);
    }

    
     // Adds won cards to the bottom of the player's deck.
     // The cards are added in a shuffled order.
    public void addCards(Card card1, Card card2) {
        ArrayList<Card> wonCards = new ArrayList<>();
        wonCards.add(card1);
        wonCards.add(card2);
        Collections.shuffle(wonCards); // Optional: mix up the cards before adding
        deck.addAll(wonCards);
    }
    
        
     // Adds a single card to the bottom of the player's deck.
    public void addCards(Card card) {
        deck.add(card);
    }

    
     // Gets the number of cards left in the player's deck.
    public int getDeckSize() {
        return deck.size();
    }
}
