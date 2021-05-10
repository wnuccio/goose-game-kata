package domain;

public class Dice {
    private int first;
    private int second;

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
