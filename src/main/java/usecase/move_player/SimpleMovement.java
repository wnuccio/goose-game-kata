package usecase.move_player;

import domain.Dice;
import domain.Position;

public class SimpleMovement implements Movement {

    String player;
    int startPosition;
    Dice dice;

    SimpleMovement(String player) {
        this.player = player;
    }

    public static MovementBuilder of(String player) {
        return new MovementBuilder(player);
    }

    @Override
    public String player() {
        return player;
    }

    @Override
    public int firstDice() {
        return dice.first();
    }

    @Override
    public int secondDice() {
        return dice.second();
    }

    @Override
    public int startPosition() {
        return startPosition;
    }

    @Override
    public int finalPosition() {
        return startPosition + dice.total();
    }

    @Override
    public boolean isVictory() {
        return finalPosition() == Position.WIN_POSITION;
    }

    @Override
    public int intermediatePosition() {
        return finalPosition();
    }

    @Override
    public MovementType type() {
        return MovementType.SIMPLE;
    }

    @Override
    public Movement previousMovement() {
        throw new IllegalStateException();
    }
}