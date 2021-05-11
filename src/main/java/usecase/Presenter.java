package usecase;

import usecase.move_player.MovementView;

public interface Presenter {
    void presentMovement(MovementView movement);

    void presentNoSuchPlayerError(String player);

    void presentPlayers(String... players);

    void presentExistingPlayerError(String player);
}
