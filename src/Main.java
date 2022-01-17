

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Let's start a BlackJack game. How much do you bring?");

        double balance = sc.nextDouble();
        sc.nextLine();

        System.out.printf("You have %.2f %n", balance);

        BlackJack bj = new BlackJack(balance);


        do {
            bj.bjGame();
            System.out.println();
            if (bj.getBalance() > 0) {
                System.out.println("Want to play again? Yes or No");
            } else {
                System.out.println("You are out of money!");
                break;
            }
        } while (Character.toLowerCase(sc.nextLine().charAt(0)) == 'y');



    }
}
