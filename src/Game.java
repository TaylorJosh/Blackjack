import java.util.Scanner;

public class Game {
    public static int play(Scanner scanner, int bet) {
        return new Game(scanner, bet).startRound();
    }

    private final Deck deck;
    private final Dealer dealer;
    private final Player player;
    private int bet;
    private final Scanner scanner;

    private Game(Scanner scanner, int bet) {
        deck = new Deck();
        deck.shuffle();
        dealer = new Dealer();
        player = new Player();
        this.bet = bet;
        this.scanner = scanner;
    }

    private int startRound() {
        drawCards();

        boolean dealerDrawBlackjack = dealer.getHand().drawBlackjack();
        boolean playerDrawBlackjack = player.playSoftHand().drawBlackjack();
        if (dealerDrawBlackjack || playerDrawBlackjack) {
            showCards(false);
            return drawsBlackjack(dealerDrawBlackjack, playerDrawBlackjack);
        } else {
            showCards(true);
            System.out.println();
            int playerValue = player.playSoftHand().getLowAceValue();
            boolean doubleDown = false;
            if (playerValue >= 9 && playerValue <= 11) {
                System.out.println("Would you like to double down?");
                doubleDown = inputDoubleDown();
                if (doubleDown) {
                    bet *= 2;
                    Card card = deck.draw();
                    System.out.println("You drew :" + card.returnName());
                    player.playSoftHand().add(card);

                }
            }
            return playRound(doubleDown);
        }
    }

    private void drawCards() {
        player.playSoftHand().add(deck.draw());
        dealer.getHand().add(deck.draw());
        player.playSoftHand().add(deck.draw());
        dealer.getHand().add(deck.draw());
    }

    private int drawsBlackjack(boolean dealerDrawBlackjack, boolean playerDrawBlackjack) {
        if (dealerDrawBlackjack && playerDrawBlackjack) {
            System.out.println("Dealer and player both have Blackjack.\nDraw.");
            return bet;
        } else if (dealerDrawBlackjack) {
            System.out.println("Dealer drew a Backjack.\nDealer wins!");
            return 0;
        } else if (playerDrawBlackjack) {
            System.out.println("Player drew a Blackjack. \nPlayer wins!");
            return (int) Math.floor(bet * 2.5);
        }
            throw new IllegalStateException("Error");
        }


    private boolean inputDoubleDown() {
        while (true) {
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("no")) {
                return choice.equals("yes");
            }
            System.out.println("Please enter yes or no");

        }
    }

    private int playRound(boolean doubleDown) {
        if (!doubleDown) {
            System.out.println("Player's turn.");
            while (true) {
                String choice = playerChoice();
                if (choice.equalsIgnoreCase("stand")) {
                    break;
                }

                Card card = deck.draw();
                System.out.println("You drew :"+card.returnName());
                player.playSoftHand().add(card);
                if (player.playSoftHand().bust()) {
                    showCards(false);
                    System.out.println("Player busts!");
                    return 0;
                } else {
                    showCards(true);
                    
                    System.out.println();
                }
            }
        }

        System.out.println();
        System.out.println("Dealer's turn");
        showCards(false);
        System.out.println();
        while (dealer.getHand().highAceValue() < 17) {
            Card card = deck.draw();
            System.out.println("Dealer draws: " + card.returnName());
            dealer.getHand().add(card);
            showCards(false);
            if (dealer.getHand().bust()) {
                System.out.println("Dealer busts!");
                return bet * 2;
            }
            System.out.println();
        }

        int playerValue = player.playSoftHand().highAceValue();
        int dealerValue = dealer.getHand().highAceValue();
        if (playerValue > dealerValue) {
            System.out.println("Player wins!");
            return bet * 2;
        } else if (playerValue < dealerValue) {
            System.out.println("Player loses!");
            return 0;
        } else {
            System.out.println("Draw");
            return bet;
        }
    }

    private String playerChoice() {
        System.out.println("Would you like to hit or stand?");
        while (true) {
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("hit") || choice.equalsIgnoreCase("stand")) {
                return choice;
            }
        }
    }

    private void showCards(boolean dealerHidden) {
        System.out.print("Dealer's hand: ");
        if (dealerHidden) {
            System.out.print(dealer.getHand().get(0).returnName());
            System.out.println(", second card face down");
        } else {
            printHand(dealer.getHand());
        }

        if (player.playHardHand().size() == 0) {
            System.out.print("Player's hand: ");
            printHand(player.playSoftHand());
        } else {
            System.out.print("Player's first card:  ");
            printHand(player.playSoftHand());
            System.out.print("Player's second card: ");
            printHand(player.playHardHand());
        }
    }

    private static void printHand(Hand hand) {
        System.out.println(String.join(", ", hand.stream().map(card -> card.returnName()).toList()));
    }
}
