package app.domain.movement;

import app.domain.player.Player;
import app.domain.player.Players;

public class FindPlayer {

    private final Players players;
    private final MovePlayer movePlayer;

    public FindPlayer(Players players, MovePlayer movePlayer) {
        this.players = players;
        this.movePlayer = movePlayer;
    }

    public void acceptCommand(MoveCommand command) {
        if ( ! players.contains(command.player())) return;

        Player player = players.find(command.player());

        movePlayer.doMove(player, command.dice());
    }
}
