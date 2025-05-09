// Derived class: PowerUpCard
public class PowerUpCard extends Card {
    private boolean isPowerUp;

    // Constructor for a PowerUpCard
    public PowerUpCard(String suit, String rank, int value, boolean isPowerUp) {
        super(suit, rank, value);  // Call the constructor of the Card class
        this.isPowerUp = isPowerUp;
    }

    public boolean isPowerUp() {
        return isPowerUp;
    }

    @Override
    public String toString() {
        if (isPowerUp) {
            return super.toString() + " (POWER-UP)";
        }
        return super.toString();
    }

    // Optional: Override compareTo for specific behavior for PowerUp cards
    @Override
    public int compareTo(Card other) {
        int valueComparison = super.compareTo(other);
        if (valueComparison != 0) {
            return valueComparison;
        }
        if (this.isPowerUp() && !(other instanceof PowerUpCard)) {
            return 1;  // Power-up cards are considered "greater" than regular cards
        }
        if (!(this.isPowerUp()) && (other instanceof PowerUpCard)) {
            return -1;  // Regular cards are considered "lesser" than power-up cards
        }
        return 0;
    }
}