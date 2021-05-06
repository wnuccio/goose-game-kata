package usecase;

public interface Presenter {
    void presentMovement(Movement movement);

    void presentNoSuchPlayerError(String player);

    void presentPlayers(String... players);

    void presentExistingPlayerError(String player);
}
