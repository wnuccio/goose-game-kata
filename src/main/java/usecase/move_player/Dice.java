package usecase.move_player;

public class Dice {
    private int first;
    private int second;

    public Dice(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int first() {
        return first;
    }

    public int second() {
        return second;
    }

    public Dice roll() {
        throw new UnsupportedOperationException();
    }
}
