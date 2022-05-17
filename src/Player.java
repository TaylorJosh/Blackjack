public class Player {
    private final Hand softHand;
    private final Hand hardHand;

    public Player() {
        softHand = new Hand();
        hardHand = new Hand();
    }

    public Hand playSoftHand() {
        return softHand;
    }

    public Hand playHardHand() {
        return hardHand;
    }
}