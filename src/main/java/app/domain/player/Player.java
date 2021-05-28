package app.domain.player;

public class Player {
    private final String name;
    private Position position;

    public Player(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    public String name() {
        return name;
    }

    public Player position(Position position) {
        this.position = position;
        return this;
    }

    public Position position() {
        return position;
    }
}