package usecase;

import java.util.Objects;

public class Movement {

    private String player;
    private int firstDice;
    private int secondDice;
    private int fromPosition;
    private int toPosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movement movement = (Movement) o;
        return firstDice == movement.firstDice && secondDice == movement.secondDice && fromPosition == movement.fromPosition && toPosition == movement.toPosition && player.equals(movement.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, firstDice, secondDice, fromPosition, toPosition);
    }

    public Movement(String player) {
        this.player = player;
    }

    public static Movement of(String player) {
        return new Movement(player);
    }

    public Movement givenRoll(int firstDice, int secondDice) {
        this.firstDice = firstDice;
        this.secondDice = secondDice;
        return this;
    }

    public Movement from(int fromPosition) {
        this.fromPosition = fromPosition;
        return this;
    }

    public Movement to(int toPosition) {
        this.toPosition = toPosition;
        return this;
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
}
