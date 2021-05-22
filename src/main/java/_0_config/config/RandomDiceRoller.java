package _0_config.config;

import _1_actions.player.rollmove.DiceRoller;
import _2_domain.movement.Dice;

public class RandomDiceRoller implements DiceRoller {
    @Override
    public Dice roll() {
        return new Dice(randomNumber(), randomNumber());
    }

    private int randomNumber() {
        return (int) ((Math.random() * 6) + 1);
    }
}
