package _1_actions.player.add;

import _2_domain.player.Board;
import _2_domain.player.Players;

public class AddPlayer {
    private final Board board;
    private final Players players;
    private final PlayerPresenter presenter;

    public AddPlayer(Board board, Players players, PlayerPresenter presenter) {
        this.board = board;
        this.players = players;
        this.presenter = presenter;
    }

    public void doAdd(String player) {
        if (players.contains(player)) {
            presenter.presentExistingPlayerError(player);
            return;
        }

        players.setPositionOf(player, board.start());
        presenter.presentPlayers(players.all());
    }
}

