package main.test;

import boundary.RealDice;
import usecase.move_player.Dice;
import usecase.move_player.DiceRoller;

public class DiceRollerForTest implements DiceRoller {
    private static DiceRollerForTest instance = new DiceRollerForTest();
    private RealDice dice;

    public static DiceRollerForTest instance() {
        return instance;
    }

    public static void onNextRollReturns(int first, int second) {
        instance.dice = new RealDice(first, second);
    }

    @Override
    public Dice roll() {
        return dice;
    }

    private DiceRollerForTest() {
        this.dice = new RealDice(1, 1);
    }
}
