package app.domain.movement;

import app.domain.player.Player;
import app.domain.rules.RuleProcessor;

public class MovePlayer {
    private final PlayerOnTurnFactory playerOnTurnFactory;
    private final RuleProcessor ruleProcessor;

    public MovePlayer(RuleProcessor ruleProcessor, PlayerOnTurnFactory playerOnTurnFactory) {
        this.ruleProcessor = ruleProcessor;
        this.playerOnTurnFactory = playerOnTurnFactory;
    }

    public void doMove(Player player, Dice dice) {
        PlayerOnTurn playerOnTurn = playerOnTurnFactory.createPlayerOnTurn(player, dice);

        ruleProcessor.computeMovementsFor(playerOnTurn);

        playerOnTurn.present();
    }
}
