public enum Rank {
    ACE("Ace", 1),
    TWO( "Two", 2),
    THREE( "Three", 3),
    FOUR( "Four", 4),
    FIVE("Five", 5),
    SIX( "Six", 6),
    SEVEN( "Seven", 7),
    EIGHT( "Eight", 8),
    NINE( "Nine", 9),
    TEN( "Ten", 10),
    JACK( "Jack", 10),
    QUEEN( "Queen", 10),
    KING( "King", 10);

    private final String Name;

    private final int value;

    Rank(String Name, int value) {
        this.Name = Name;
        this.value = value;
    }

    public String getName() {
        return Name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Name;
    }
}
