package main.move_player;

import main.test.DiceRollerStub;

public class DiceDriver {
    public void onNextRollReturns(int dice1, int dice2) {
        DiceRollerStub.onNextRollReturns(dice1, dice2);
    }
}
