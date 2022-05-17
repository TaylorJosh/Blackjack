public enum Suit {
    CLUBS( "Clubs"),
    DIAMONDS( "Diamonds"),
    HEARTS( "Hearts"),
    SPADES( "Spades");

    private final String Name;

    Suit(String Name) {
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }


    @Override
    public String toString() {
        return Name;
    }
}
