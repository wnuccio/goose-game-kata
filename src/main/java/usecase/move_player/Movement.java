package usecase.move_player;

import java.util.Objects;

public class Movement {
    public static final int WIN_POSITION = 63;

    String player;
    int fromPosition;
    Dice dice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movement movement = (Movement) o;
        return firstDice() == movement.firstDice() &&
                secondDice() == movement.secondDice() &&
                fromPosition == movement.fromPosition &&
                player.equals(movement.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, firstDice(), secondDice(), fromPosition);
    }

    Movement(String player) {
        this.player = player;
    }

    public static MovementBuilder of(String player) {
        return new MovementBuilder(player);
    }

    public String player() {
        return player;
    }

    public int firstDice() {
        return dice.first();
    }

    public int secondDice() {
        return dice.second();
    }

    public int fromPosition() {
        return fromPosition;
    }

    public int toPosition() {
        return isBouncing() ? WIN_POSITION - (candidatePosition() - WIN_POSITION) : candidatePosition();
    }

    public boolean isBouncing() {
        return candidatePosition() > WIN_POSITION;
    }

    public boolean isVictory() {
        return toPosition() == WIN_POSITION;
    }

    @Override
    public String toString() {
        return "Movement{" +
                "player='" + player + '\'' +
                ", fromPosition=" + fromPosition +
                ", firstDice=" + firstDice() +
                ", secondDice=" + secondDice() +
                ", candidatePosition=" + candidatePosition() +
                ", toPosition=" + toPosition() +
                ", victory=" + isVictory() +
                ", bouncing=" + isBouncing() +
                '}';
    }

    private int candidatePosition() {
        return fromPosition + dice.total();
    }

}