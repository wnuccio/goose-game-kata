package usecase.move_player;

public interface Dice {
    int first();

    int second();

    Dice from(int first, int second);

    default int total() { return first() + second(); }
}
