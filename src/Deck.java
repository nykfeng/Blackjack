

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck;

    public static String[] suits = {"♠","♥","♣","♦"};

    public Deck() {
        this.deck = new ArrayList<Card>();
        makeADeck();
        shuffleDeck();
    }

    public void makeADeck () {
        for (int i=1; i<=13; i++)
            suitAndRank(i);
    }

    private void suitAndRank(int numb) {
        switch(numb) {
            case 1:
                for(int i=0;i<4;i++)
                    this.deck.add(new Card(suits[i], "A"));
                break;
            case 11:
                for(int i=0;i<4;i++)
                    this.deck.add(new Card(suits[i], "J"));
                break;
            case 12:
                for(int i=0;i<4;i++)
                    this.deck.add(new Card(suits[i], "Q"));
                break;
            case 13:
                for(int i=0;i<4;i++)
                    this.deck.add(new Card(suits[i], "K"));
                break;
            default:
                for (int i=0; i<4; i++) {
                    this.deck.add(new Card(suits[i], Integer.toString(numb)));
                }
        }
    }

    public void printDeck() {
        for(int i=0; i<deck.size(); i++) {
            System.out.println(deck.get(i).getCard());
        }
        System.out.println("There are a total of " + deck.size() + " cards in the deck.");
    }

    public void shuffleDeck() {
        Random rand = new Random();
        Card temp = new Card();

        for(int i=0; i<52; i++) {
            temp.setRank(deck.get(i).getRank());
            temp.setSuit(deck.get(i).getSuit());

            int rNum = rand.nextInt(52);

            deck.get(i).setRank(deck.get(rNum).getRank());
            deck.get(i).setSuit(deck.get(rNum).getSuit());

            deck.get(rNum).setRank(temp.getRank());
            deck.get(rNum).setSuit(temp.getSuit());
        }
    }

    public String drawACard() {
        String card = deck.get(0).getCard();
        deck.remove(0);
        return card;
    }
}
