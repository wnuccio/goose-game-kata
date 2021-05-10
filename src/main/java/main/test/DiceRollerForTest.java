package main.test;

import boundary.Dice;
import usecase.move_player.DiceRoller;

public class DiceRollerForTest implements DiceRoller {
    private static DiceRollerForTest instance = new DiceRollerForTest();
    private Dice dice;

    public static DiceRollerForTest instance() {
        return instance;
    }

    public static void onNextRollReturns(int first, int second) {
        instance.dice = new Dice(first, second);
    }

    @Override
    public Dice roll() {
        return dice;
    }

    private DiceRollerForTest() {
        this.dice = new Dice(1, 1);
    }
}
