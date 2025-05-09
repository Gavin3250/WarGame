 //The Card class represents a standard playing card with a suit and rank.
 //It can be compared to other cards to determine which one is stronger.
 
public class Card implements Comparable<Card> {

    private String suit;
    private String rank;
    private int value;
    private boolean isPowerUp;

    
     //Constructs a regular Card with a given suit, rank, and value.
      public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
        this.isPowerUp = false; // default
    }

     //Constructs a Card with optional power-up ability.
     
    public Card(String suit, String rank, int value, boolean isPowerUp) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
        this.isPowerUp = isPowerUp;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }

    public boolean isPowerUp() {
        return isPowerUp;
    }

    @Override
    public int compareTo(Card other) {
        return this.value - other.value;
    }

    @Override
    public String toString() {
        if (isPowerUp) {
            return rank + " of " + suit + " (POWER-UP)";
        }
        return rank + " of " + suit;
    }
}
