package app.domain.player;

public interface PlayerObserver {
    void playerPositionChanged(Movement movement);
}
