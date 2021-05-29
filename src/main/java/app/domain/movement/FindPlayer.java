package app.domain.movement;

import app.domain.player.Player;
import app.domain.player.Players;

public class FindPlayer {

    private final Players players;
    private final MovePlayer movePlayer;
    private PlayerOnTurnFactory playerOnTurnFactory;

    public FindPlayer(Players players, MovePlayer movePlayer, PlayerOnTurnFactory playerOnTurnFactory) {
        this.players = players;
        this.movePlayer = movePlayer;
        this.playerOnTurnFactory = playerOnTurnFactory;
    }

    public void acceptCommand(MoveCommand command) {
        if ( ! players.contains(command.player())) return;

        Player player = players.find(command.player());

        movePlayer.doMove(playerOnTurnFactory.createPlayerOnTurn(player, command.dice()));
    }
}
