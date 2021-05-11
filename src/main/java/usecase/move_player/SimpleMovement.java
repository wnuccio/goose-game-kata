package usecase.move_player;

import domain.Dice;
import domain.Position;

public class SimpleMovement implements Movement {
    int startPosition;
    Dice dice;

    public static MovementBuilder movement() {
        return new MovementBuilder();
    }

    @Override
    public int startPosition() {
        return startPosition;
    }

    @Override
    public int diceTotal() {
        return dice.total();
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