
import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {

    public static Scanner sc = new Scanner(System.in);
    private double amount = 0;

    //Get the starting balance from outside
    private double balance;

    private Deck blackJ; //card deck object
    private ArrayList<String> player = new ArrayList<>(); //Player hand
    private ArrayList<String> dealer = new ArrayList<>(); //Dealer hand
    private GameSummary gs;

    //Constructor for BJ game
    public BlackJack(double balance) {
        this.blackJ = new Deck();
        this.balance = balance;
        this.gs = new GameSummary(balance);
    }


    public double getBalance() {
        return this.balance;
    }


    public void startAGame() {
        if (getBalance() <=0 ) {
            System.out.println("You can't start a game with 0 balance!");
            System.exit(0);
        }
        if (bettingAmt() ) {

            System.out.printf("Starting balance %.2f %n", gs.getBalance());
            System.out.printf("Betting amount this game %.2f %n", amount);
            startingHands();
            showHands();
        } else
            gs.close();
    }

    private boolean bettingAmt() {
        boolean toBet = true;
        while (toBet) {
            System.out.println("How much do you want to bet for this game?");
            amount = sc.nextDouble();
            sc.nextLine();
            if (amount > gs.getBalance()) {
                System.out.println("You don't have enough money to make the bet.");
                System.out.println("Do you want to make another bet and continue?");
                if (sc.nextLine().charAt(0) != 'y') {
                    toBet = false;
                    break;
                }
            }
            else
                return toBet;
        }
        return toBet;
    }

    public void startingHands() {
        player.add(blackJ.drawACard());
        player.add(blackJ.drawACard());
        dealer.add(blackJ.drawACard());
        dealer.add(blackJ.drawACard());
    }

    public void bjGame() {

        startAGame();

        while (player.size() != 5 || calculate(player) <21) {
            if (drawPrompt()) {
                player.add(blackJ.drawACard());
                showHands();
                if (calculate(player) >21) {
                    finishGame();
                    break;
                }
                if (player.size() ==5 && calculate(player) <=21) {
                    finishGame();
                    break;
                }
            } else {
                dealerPlay();
                finishGame();
                break;
            }
        }
        gs.close();
    }

    private boolean drawPrompt() {
        char answer;
        do {
            System.out.println("Do you want another card? Yes or No");
            answer = Character.toLowerCase(sc.nextLine().charAt(0));
        } while ( answer != 'y' && answer != 'n');

        if (answer == 'y') {
            return true;
        } else
            return false;
    }

//    private boolean over() {
//        for (int i=0; i<player.size(); i++) {
//
//        }
//    }

    private void dealerPlay() {
        while (calculate(dealer) <18 && dealer.size()<5 && calculate(dealer)<=calculate(player)) {
            dealer.add(blackJ.drawACard());
        }
    }

    private void finishGame() {
        System.out.println("Dealer hand");
        System.out.print("|");
        for (int i=0; i<dealer.size(); i++) {
            System.out.print(dealer.get(i) + "|");
        }

        System.out.println();
        showPlayerHand();
        System.out.println();

        whoWins();

        player.clear();
        dealer.clear();
    }

    private void whoWins () {
        System.out.println("Player got " + calculate(player));
        System.out.println("Dealer got " + calculate(dealer));
        if (calculate(player) > 21) {
            System.out.println("Dealer win.");
            gs.setGamesLost(1);
            gs.addBalance((amount*(-1)));
        }
        else if (player.size() == 5 && calculate(player) <= 21) {
            System.out.println("Player win.");
            gs.setGamesWon(1);
            gs.addBalance(amount);
        }
        else if (calculate(player) == calculate(dealer)) {
            System.out.println("It's a draw.");
            gs.setGamesTied(1);
        }
        else if (calculate(player) <=21 && calculate(dealer) >21) {
            System.out.println("Player win.");
            gs.setGamesWon(1);
            gs.addBalance(amount);
        }
        else {
//            System.out.println(calculate(player) > calculate(dealer) ? "Player win" : "Dealer win");
            if (calculate(player) > calculate(dealer)) {
                System.out.println("Player win.");
                gs.setGamesWon(1);
                gs.addBalance(amount);
            } else {
                System.out.println("Dealer win.");
                gs.setGamesLost(1);
                gs.addBalance((amount*(-1)));
            }
        }
    }

    private void showHands() {
        showDealerFoldedHand();
        System.out.println();
        showPlayerHand();
        System.out.println();
        System.out.println("-------");
    }

    private void showDealerFoldedHand() {
        System.out.println("Dealer hand");
        System.out.print("|");
        for (int i=0; i<1; i++) {
            System.out.print(dealer.get(i) + "|");
        }
    }

    private void showPlayerHand() {
        System.out.println("Player hand");
        System.out.print("|");
        for (int i=0; i<player.size(); i++) {
            System.out.print(player.get(i) + "|");
        }
    }

    //Calculate the current number based on the ranks
    private int calculate (ArrayList<String> als) {
        int sum=0;
        int hasA=0;
        for (int i=0; i<als.size(); i++) {

            if (convertRank(als.get(i)) == 1) {
                hasA++;
            }

            sum += convertRank(als.get(i));
        }
        if (hasA>0) {
            if ( (21-(sum+10))>=0 && (21-(sum+10))<(21-sum) )
                return sum + 10;
        }

        return sum;
    }

    private int convertRank (String rank) {
        String str = rank.substring(1,rank.length());
        switch (str) {
            case "A":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
            case "J":
            case "Q":
            case "K":
                return 10;
            default:
                return 0;
        }
    }


}
