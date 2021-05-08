package usecase;

import usecase.move_player.SimpleMovement;

public interface Presenter {
    void presentMovement(SimpleMovement movement);

    void presentNoSuchPlayerError(String player);

    void presentPlayers(String... players);

    void presentExistingPlayerError(String player);
}
