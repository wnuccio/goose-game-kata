package app.domain.player;

public class Dice {
    private final int first;
    private final int second;

    public Dice(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public static Dice dice(int first, int second) {
        return new Dice(first, second);
    }

    public int first() {
        return first;
    }

    public int second() {
        return second;
    }

    public int total() { return first() + second(); }
}
