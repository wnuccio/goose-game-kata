package usecase.move_player;

public class SimpleMovement implements Movement {
    public static final int START = 0;
    public static final int BRIDGE = 6;
    public static final int BRIDGE_TARGET = 12;
    public static final int WIN_POSITION = 63;

    String player;
    int fromPosition;
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
    public int fromPosition() {
        return fromPosition;
    }

    @Override
    public int toPosition() {
        return isBouncing() ? WIN_POSITION - (candidatePosition() - WIN_POSITION) : candidatePosition();
    }

    @Override
    public boolean isBouncing() {
        return candidatePosition() > WIN_POSITION;
    }

    @Override
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

    @Override
    public boolean isJumpOnBridge() {
        return false;
    }

    @Override
    public boolean isToBridge() {
        return toPosition() == BRIDGE;
    }

    @Override
    public int intermediatePosition() {
        return isBouncing() ? WIN_POSITION : toPosition();
    }
}