package goosegame.config;

import goosegame.boundary.application.InputOutput;
import goosegame.boundary.output.SystemInputOutput;
import goosegame.boundary.random.RandomDiceRoller;
import goosegame.domain.DiceRoller;

public class GooseGameAppBuilder {

    protected InputOutput getInputOutput() {
        return new SystemInputOutput();
    }

    protected DiceRoller diceRoller() {
        return new RandomDiceRoller();
    }
}
