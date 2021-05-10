package usecase.move_player;

public enum MovementType {
    SIMPLE(true, false, false, false),
    BOUNCING(false, true, false, false),
    JUMP_ON_BRIDGE(false, false, true, false),
    REPEAT_ON_GOOSE(false, false, false, true);

    boolean simple;
    boolean bouncing;
    boolean jumpOnBridge;
    boolean repeatOnGoose;

    MovementType(boolean simple, boolean bouncing, boolean jumpOnBridge, boolean repeatOnGoose) {
        this.simple = simple;
        this.bouncing = bouncing;
        this.jumpOnBridge = jumpOnBridge;
        this.repeatOnGoose = repeatOnGoose;
    }

    public boolean isBouncing() {
        return bouncing;
    }

    public boolean isJumpOnBridge() {
        return jumpOnBridge;
    }

    public boolean isRepeatOnGoose() {
        return repeatOnGoose;
    }
}
