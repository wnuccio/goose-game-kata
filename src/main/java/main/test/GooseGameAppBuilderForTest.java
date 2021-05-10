package main.test;

import boundary.OutputBoundary;
import boundary.RealDice;
import main.GooseGameAppBuilder;
import main.InputBoundary;
import usecase.move_player.Dice;
import usecase.move_player.DiceRoller;

public class GooseGameAppBuilderForTest extends GooseGameAppBuilder {

    protected InputBoundary getInputBoundary() {
        return TestSystemInputOuput.instance();
    }

    protected OutputBoundary getOutputBoundary() {
        return TestSystemInputOuput.instance();
    }

    @Override
    protected Dice dice() {
        return new RealDice(1, 1); // DiceForTest.instance();
    }

    @Override
    protected DiceRoller diceRoller() {
        return DiceForTest.instance();
    }
}
