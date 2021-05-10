package usecase.move_player;

import domain.Dice;
import domain.Position;

import java.util.List;

import static java.util.Arrays.asList;

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

    public boolean endsOnGoose() {
        List<Integer> positionsWithGoose = asList(5, 9, 14, 18, 23, 27);
        return positionsWithGoose.contains(finalPosition());
    }
}