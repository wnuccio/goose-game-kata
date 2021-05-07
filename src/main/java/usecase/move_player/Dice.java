package usecase.move_player;

public class Dice {
    private int first;
    private int second;

    Dice(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int first() {
        return first;
    }

    public int second() {
        return second;
    }
}
