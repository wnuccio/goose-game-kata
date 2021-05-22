package _2_domain;

public interface Movement {
    Position startPosition();

    Position finalPosition();

    default boolean isVictory() {
        return finalPosition().isWin();
    }

}
