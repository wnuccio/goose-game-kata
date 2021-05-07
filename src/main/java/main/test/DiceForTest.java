package main.test;

import usecase.move_player.Dice;

import java.util.Objects;

public class DiceForTest implements Dice {
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
        this.modifiableFirst = first;
        this.modifiableSecond = second;
    }

    public static Dice dice(int first, int second) {
        return new DiceForTest(first, second);
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

    @Override
    public Dice from(int first, int second) {
        return new DiceForTest(first, second);
    }

    public void values(int first, int second) {
        this.modifiableFirst = first;
        this.modifiableSecond = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiceForTest that = (DiceForTest) o;
        return modifiableFirst == that.modifiableFirst && modifiableSecond == that.modifiableSecond;
    }

    @Override
    public int hashCode() {
        return Objects.hash(modifiableFirst, modifiableSecond);
    }
}
