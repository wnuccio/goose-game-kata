package app.domain.player;

public class Player {
    private final String name;
    private Position position;

    public Player(String name) {
        this.name = name;
    }

    public Player position(Position position) {
        this.position = position;
        return this;
    }

    public Position position() {
        return position;
    }
}
