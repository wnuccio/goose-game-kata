package main;

import domain.Dice;
import domain.DiceRoller;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class DiceRollerStub implements DiceRoller {
    private BlockingQueue<Dice> dice;

    public void onNextRollReturns(int first, int second) {
        try {
            dice.put(new Dice(first, second));

        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Dice roll() {
        try {
            Dice result = dice.poll(15, TimeUnit.MILLISECONDS);
            if (result == null) throw new IllegalStateException("Dice values not set");
            return result;

        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    public DiceRollerStub() {
        this.dice = new LinkedBlockingDeque<>();
    }
}
