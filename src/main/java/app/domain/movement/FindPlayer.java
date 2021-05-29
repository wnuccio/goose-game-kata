package app.domain.movement;

import app.domain.player.Player;
import app.domain.player.PlayerOnTurn;
import app.domain.player.Players;

public class FindPlayer {

    private final Players players;
    private PlayerOnTurnFactory playerOnTurnFactory;
    private final MovePlayer movePlayer;

    public FindPlayer(Players players, PlayerOnTurnFactory playerOnTurnFactory, MovePlayer movePlayer) {
        this.players = players;
        this.playerOnTurnFactory = playerOnTurnFactory;
        this.movePlayer = movePlayer;
    }

    public void acceptCommand(MoveCommand command) {
        if ( ! players.contains(command.player())) return;

        Player player = players.find(command.player());

        PlayerOnTurn playerOnTurn = playerOnTurnFactory.createPlayerOnTurn(player, command.dice());

        movePlayer.doMove(playerOnTurn);
    }
}
