public class Card {

    private String suit;
    private String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Card() {
        this.suit = null;
        this.rank = null;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public String getCard() {
        return suit + rank;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
