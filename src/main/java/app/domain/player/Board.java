package app.domain.player;

public class Board {
    private final Position START = new Position(0, this);
    private final Position BRIDGE = new Position(6, this);
    private final Position BRIDGE_TARGET = new Position(12, this);
    private final Position WIN = new Position(63, this);

    public Position position(int value) {
        return new Position(value, this);
    }

    public Position start() {
        return START;
    }

    public Position bridge() {
        return BRIDGE;
    }

    public Position bridgeTarget() {
        return BRIDGE_TARGET;
    }

    public Position win() {
        return WIN;
    }
}
