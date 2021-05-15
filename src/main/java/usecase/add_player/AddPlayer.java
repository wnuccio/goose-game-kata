package usecase.add_player;

import domain.Players;

public class AddPlayer {
    private Players players;
    private PlayerPresenter presenter;

    public AddPlayer(Players players, PlayerPresenter presenter) {
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

