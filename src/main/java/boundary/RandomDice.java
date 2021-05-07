package boundary;

import usecase.move_player.Dice;

public class RandomDice implements Dice {
    private int first;
    private int second;

    public RandomDice(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int first() {
        return first;
    }

    public int second() {
        return second;
    }

    public RandomDice roll() {
        throw new UnsupportedOperationException();
    }
}
