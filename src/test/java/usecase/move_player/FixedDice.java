package usecase.move_player;

public class FixedDice extends Dice {
    FixedDice(int first, int second) {
        super(first, second);
    }

    @Override
    public Dice roll() {
        return this;
    }
}
