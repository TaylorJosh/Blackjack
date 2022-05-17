import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int cash = 1000;

        try (Scanner scanner = new Scanner(System.in)) {


            while (cash > 0) {
                System.out.println("Your balance is " + cash);
                System.out.println("How much would you like to bet? (enter 0 to quit)");
                int bet = playerBet(scanner, cash);

                if (bet != 0) {
                    int betAmount = Game.play(scanner, bet) - bet;

                    cash += betAmount;
                    System.out.println();
                } else {
                    break;
                }
            }
        }

    }

        private static int playerBet(Scanner scanner,int cash){
            while (true) {
                System.out.print("$");

                int bet = scanner.nextInt();

                if (bet < 0) {
                    System.out.print("You must bet at least $1");
                } else {
                    return bet;
                }
            }
        }

        private static void winBet ( int betAmount){
            if (betAmount > 0) {
                System.out.println("You win: " + betAmount);
            } else if (betAmount < 0) {
                System.out.println("You lost: " + betAmount);
            } else {
                System.out.println("You break even.");
            }
        }

        private static void printConclusion ( int cash){
            if (cash > 0) {
                System.out.println("Yor final balance: $" + cash);
            } else if
            (cash <= 0) {
                System.out.println("Sorry. You lost everything.");
            } else {
                System.out.println("You owe the dealer: " + cash + "\nPlease settle your debt.");
            }

        }
    }
