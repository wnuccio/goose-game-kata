package usecase.move_player;

public class Movement {
    public static final int WIN_POSITION = 63;

    String player;
    int fromPosition;
    Dice dice;

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

    public boolean isJumpOnBridge() {
        return false;
    }
}