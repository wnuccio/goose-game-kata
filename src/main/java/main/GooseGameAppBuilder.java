package main;

import boundary.application.InputOutput;
import boundary.output.SystemInputOutput;
import boundary.random.RandomDiceRoller;
import domain.DiceRoller;

public class GooseGameAppBuilder {

    protected InputOutput getInputOutput() {
        return new SystemInputOutput();
    }

    protected DiceRoller diceRoller() {
        return new RandomDiceRoller();
    }
}
