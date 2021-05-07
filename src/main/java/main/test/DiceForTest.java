package main.test;

import usecase.move_player.Dice;

public class DiceForTest extends Dice {
    private static DiceForTest instance = new DiceForTest(1, 1);

    public static Dice instance() {
        return instance;
    }

    public static void setValues(int first, int second) {
        instance.values(first, second);
    }

    private int modifiableFirst;
    private int modifiableSecond;

    public DiceForTest(int first, int second) {
        super(first, second);
        this.modifiableFirst = first;
        this.modifiableSecond = second;
    }

    @Override
    public int first() {
        return modifiableFirst;
    }

    @Override
    public int second() {
        return modifiableSecond;
    }

    @Override
    public Dice roll() {
        return this;
    }

    public void values(int first, int second) {
        this.modifiableFirst = first;
        this.modifiableSecond = second;
    }
}
