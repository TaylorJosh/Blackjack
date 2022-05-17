import java.util.ArrayList;

public class Hand extends ArrayList<Card> {
    public boolean drawBlackjack() {
        if (size() == 2) {
            Rank r1 = get(0).getRank();
            Rank r2 = get(1).getRank();
            return (r1 == Rank.ACE || r2 == Rank.ACE) && (r1.getValue() == 10 || r2.getValue() == 10);
        }
        return false;
    }

    public boolean bust() {
        return getLowAceValue() > 21;
    }

    public int getLowAceValue() {
        int total = 0;
        for (Card card : this) {
            total += card.getRank().getValue();
        }
        return total;
    }

    public int highAceValue() {
        int total = getLowAceValue();
        for (Card card : this) {
            if (card.getRank() == Rank.ACE) {
                if (total + 10 > 21) {
                    return total;
                }
                total += 10;
            }
        }
        return total;
    }
}