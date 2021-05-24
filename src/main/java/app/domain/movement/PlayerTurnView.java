package app.domain.movement;


public class PlayerTurnView {
    private final String player;
    private final int firstDice;
    private final int secondDice;

    public PlayerTurnView(String player, int firstDice, int secondDice) {
        this.player = player;
        this.firstDice = firstDice;
        this.secondDice = secondDice;
    }

    public String player() {
        return player;
    }

    public int firstDice() {
        return firstDice;
    }

    public int secondDice() {
        return secondDice;
    }
}
