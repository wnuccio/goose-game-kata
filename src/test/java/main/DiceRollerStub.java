package main;

import domain.Dice;
import domain.DiceRoller;

public class DiceRollerStub implements DiceRoller {
    private Dice dice;

    public void onNextRollReturns(int first, int second) {
        dice = new Dice(first, second);
    }

    @Override
    public Dice roll() {
        return dice;
    }

    public DiceRollerStub() {
        this.dice = new Dice(1, 1);
    }
}
