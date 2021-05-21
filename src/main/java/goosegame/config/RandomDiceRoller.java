package goosegame.config;

import goosegame.domain.Dice;
import goosegame.domain.DiceRoller;

public class RandomDiceRoller implements DiceRoller {
    @Override
    public Dice roll() {
        return new Dice(randomNumber(), randomNumber());
    }

    private int randomNumber() {
        return (int) ((Math.random() * 6) + 1);
    }
}
