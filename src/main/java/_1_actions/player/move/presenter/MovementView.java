package _1_actions.player.move.presenter;


import java.util.List;

public class MovementView {
    private final List<PresentableMovement> movements;
    private final String player;
    private final int firstDice;
    private final int secondDice;

    public MovementView(List<PresentableMovement> movements, String player, int firstDice, int secondDice) {
        this.movements = movements;
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
