package _1_actions.player.move;

import _1_actions.player.move.rules.RuleProcessor;
import _2_domain.movement.MoveCommand;
import _2_domain.movement.PlayerTurn;
import _2_domain.player.Players;
import _2_domain.presenter.StringBuilderPresenter;

public class MovePlayer {
    private final Players players;
    private final RuleProcessor ruleProcessor;
    private final StringBuilderPresenter presenter;

    public MovePlayer(Players players, RuleProcessor ruleProcessor, StringBuilderPresenter presenter) {
        this.players = players;
        this.ruleProcessor = ruleProcessor;
        this.presenter = presenter;
    }

    public void acceptCommand(MoveCommand command) {
        if ( ! players.contains(command.player())) return;

        PlayerTurn playerTurn = new PlayerTurn(command);
        ruleProcessor.computeMovementsFor(playerTurn);
        playerTurn.present(presenter);
    }
}
