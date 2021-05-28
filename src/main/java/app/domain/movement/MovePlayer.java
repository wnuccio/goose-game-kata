package app.domain.movement;

import app.domain.player.Player;
import app.domain.player.Players;
import app.domain.rules.RuleProcessor;

public class MovePlayer {
    private final Players players;
    private final PlayerOnTurnFactory playerOnTurnFactory;
    private final RuleProcessor ruleProcessor;

    public MovePlayer(Players players, RuleProcessor ruleProcessor, PlayerOnTurnFactory playerOnTurnFactory) {
        this.players = players;
        this.ruleProcessor = ruleProcessor;
        this.playerOnTurnFactory = playerOnTurnFactory;
    }

    public void acceptCommand(MoveCommand command) {
        if ( ! players.contains(command.player())) return;

        Player player = players.find(command.player());

        doMove(player, command.dice());
    }

    public void doMove(Player player, Dice dice) {
        PlayerOnTurn playerOnTurn = playerOnTurnFactory.createPlayerOnTurn(player, dice);

        ruleProcessor.computeMovementsFor(playerOnTurn);

        playerOnTurn.present();
    }
}
