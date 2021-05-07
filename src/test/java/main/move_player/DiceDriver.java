package main.move_player;

import main.test.DiceForTest;

public class DiceDriver {
    public void onNextRollReturns(int dice1, int dice2) {
        DiceForTest.setValues(dice1, dice2);
    }
}
