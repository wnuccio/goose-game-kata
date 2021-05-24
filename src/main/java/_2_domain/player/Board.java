package _2_domain.player;

public class Board {
    private Position START;
    private Position BRIDGE;
    private Position BRIDGE_TARGET;
    private Position WIN;

    public Position position(int value) {
        return new Position(value, this);
    }

    public Position start() {
        if (START == null) START = new Position(0, this);
        return START;
    }

    public Position bridge() {
        if (BRIDGE == null) BRIDGE = new Position(6, this);
        return BRIDGE;
    }

    public Position bridgeTarget() {
        if (BRIDGE_TARGET == null) BRIDGE_TARGET = new Position(12, this);
        return BRIDGE_TARGET;
    }

    public Position winPosition() {
        if (WIN == null) WIN = new Position(63, this);
        return WIN;
    }
}
