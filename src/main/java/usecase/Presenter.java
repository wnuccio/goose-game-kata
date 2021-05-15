package usecase;

public interface Presenter {
    void presentPlayers(String... players);

    void presentExistingPlayerError(String player);
}
