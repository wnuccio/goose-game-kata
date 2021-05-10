package main.test;

import boundary.RealDice;
import usecase.move_player.Dice;
import usecase.move_player.DiceRoller;

import java.util.Objects;

public class DiceForTest implements DiceRoller {
    private static DiceForTest instance = new DiceForTest(1, 1);

    public static DiceForTest instance() {
        return instance;
    }

    public static void onNextRollReturns(int first, int second) {
        instance.modifiableFirst = first;
        instance.modifiableSecond = second;
    }

    private int modifiableFirst;
    private int modifiableSecond;

    public DiceForTest(int first, int second) {
        this.modifiableFirst = first;
        this.modifiableSecond = second;
    }

    @Override
    public Dice roll() {
        return new RealDice(modifiableFirst, modifiableSecond);
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
