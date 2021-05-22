package _1_actions.player.move.presenter;


import _2_domain.Movement;

import java.util.List;

public class MovementView {
    private final List<Movement> movements;
    private final String player;
    private final int firstDice;
    private final int secondDice;

    public MovementView(List<Movement> movements, String player, int firstDice, int secondDice) {
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

    public List<Movement> movements() { return movements;
    }
}
