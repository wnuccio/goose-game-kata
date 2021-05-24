package app.config;


import app.domain.rollmove.DiceRoller;
import app.domain.rollmove.RandomDiceRoller;

public class GooseGameAppBuilder {

    protected InputOutput getInputOutput() {
        return new SystemConsole();
    }

    protected DiceRoller diceRoller() {
        return new RandomDiceRoller();
    }
}
