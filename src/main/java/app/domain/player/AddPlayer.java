package app.domain.player;

public class AddPlayer {
    private final Players players;
    private final PlayerPresenter presenter;

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

