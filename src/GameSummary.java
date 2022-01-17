
public class GameSummary {
    private double balance;
    private int gamesWon;
    private int gamesLost;
    private int gamesTied;

    public GameSummary(double balance) {
        this.balance = balance;
        this.gamesWon = 0;
        this.gamesLost = 0;
        this.gamesTied = 0;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addBalance(double amt) {
        this.balance += amt;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon += gamesWon;
    }

    public void setGamesLost(int gamesLost) {
        this.gamesLost += gamesLost;
    }

    public void setGamesTied(int gamesLost) { this.gamesTied += gamesLost; }

    public double getBalance() {
        return this.balance;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public int getGamesTied() { return gamesTied; }

    public void close() {
        System.out.println("Before you leave the table, here's your game summary:");
        System.out.println("Games won " + getGamesWon());
        System.out.println("Games lost " + getGamesLost());
        System.out.println("Games tied " + getGamesTied());
        System.out.printf("Your final balance %.2f %n", this.balance);
    }
}
