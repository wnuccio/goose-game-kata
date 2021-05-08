package usecase.move_player;

import java.util.Objects;

public class Movement {
    public static final int WIN_POSITION = 63;

    String player;
    int firstDice;
    int secondDice;
    int fromPosition;
    int toPosition;
    boolean bouncing;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movement movement = (Movement) o;
        return firstDice == movement.firstDice &&
                secondDice == movement.secondDice &&
                fromPosition == movement.fromPosition &&
                toPosition == movement.toPosition &&
                bouncing == movement.bouncing &&
                player.equals(movement.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, firstDice, secondDice, fromPosition, toPosition, bouncing);
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
        return firstDice;
    }

    public int secondDice() {
        return secondDice;
    }

    public int fromPosition() {
        return fromPosition;
    }

    public int toPosition() {
        return toPosition;
    }

    public boolean isBouncing() {
        return this.bouncing;
    }

    public boolean isVictory() {
        return toPosition() == WIN_POSITION;
    }

    @Override
    public String toString() {
        return "Movement{" +
                "player='" + player + '\'' +
                ", firstDice=" + firstDice +
                ", secondDice=" + secondDice +
                ", fromPosition=" + fromPosition +
                ", toPosition=" + toPosition +
                ", victory=" + isVictory() +
                ", bouncing=" + bouncing +
                '}';
    }

    public Movement setBouncing(boolean isBouncing) {
        this.bouncing = isBouncing;
        return this;
    }

    public Movement to(int toPosition) {
        this.toPosition = toPosition;
        return this;
    }
}