package app.domain.movement;

import app.domain.player.Dice;
import app.domain.player.Player;
import app.domain.player.PlayerOnTurn;
import app.domain.player.Players;

public class FindPlayer {

    private final Players players;
    private final MovePlayer movePlayer;

    public FindPlayer(Players players, MovePlayer movePlayer) {
        this.players = players;
        this.movePlayer = movePlayer;
    }

    public void acceptCommand(String playerName, Dice dice) {
        if ( ! players.contains(playerName)) return;

        Player player = players.find(playerName);

        PlayerOnTurn playerOnTurn = new PlayerOnTurn(player, dice);

        movePlayer.doMove(playerOnTurn);
    }
}
