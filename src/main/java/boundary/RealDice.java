package boundary;

import usecase.move_player.Dice;

public class RealDice implements Dice {
    private int first;
    private int second;

    public RealDice(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public static Dice dice(int first, int second) {
        return new RealDice(first, second);
    }

    public int first() {
        return first;
    }

    public int second() {
        return second;
    }
}
