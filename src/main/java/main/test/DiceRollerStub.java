package main.test;

import boundary.Dice;
import usecase.move_player.DiceRoller;

public class DiceRollerStub implements DiceRoller {
    private static DiceRollerStub instance = new DiceRollerStub();
    private Dice dice;

    public static DiceRollerStub instance() {
        return instance;
    }

    public static void onNextRollReturns(int first, int second) {
        instance.dice = new Dice(first, second);
    }

    @Override
    public Dice roll() {
        return dice;
    }

    private DiceRollerStub() {
        this.dice = new Dice(1, 1);
    }
}
