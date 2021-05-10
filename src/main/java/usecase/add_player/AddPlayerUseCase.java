package usecase.add_player;

import domain.Players;
import usecase.Presenter;

public class AddPlayerUseCase {
    private Players players;
    private Presenter presenter;

    public AddPlayerUseCase(Players players, Presenter presenter) {
        this.players = players;
        this.presenter = presenter;
    }

    public void doAdd(String player) {
        if (players.contains(player)) {
            presenter.presentExistingPlayerError(player);
            return;
        }

        players.addPlayer(player);
        presenter.presentPlayers(players.all());
    }
}

