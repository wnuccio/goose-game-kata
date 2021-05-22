package _0_config.config;

import _2_domain.movement.DiceRoller;

public class GooseGameAppBuilder {

    protected InputOutput getInputOutput() {
        return new SystemInputOutput();
    }

    protected DiceRoller diceRoller() {
        return new RandomDiceRoller();
    }
}
