public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public String returnName() {
        return rank.getName() + suit.getName();
    }



    @Override
    public String toString() {
        return returnName();
    }
}
