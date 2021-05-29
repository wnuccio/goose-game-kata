package app.domain.movement;

import app.domain.player.PlayerOnTurn;
import app.domain.rules.RuleProcessor;

public class MovePlayer {
    private final RuleProcessor ruleProcessor;

    public MovePlayer(RuleProcessor ruleProcessor) {
        this.ruleProcessor = ruleProcessor;
    }

    public void doMove(PlayerOnTurn playerOnTurn) {
        ruleProcessor.computeMovementsFor(playerOnTurn);
    }
}
