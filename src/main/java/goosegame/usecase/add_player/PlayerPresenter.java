package goosegame.usecase.add_player;

public interface PlayerPresenter {
    void presentPlayers(String... players);

    void presentExistingPlayerError(String player);
}
