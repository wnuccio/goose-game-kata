package app.domain.movement;

import app.domain.player.Players;
import app.domain.presenter.StringBuilderPresenter;
import app.domain.rules.RuleProcessor;

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

        PlayerTurn playerTurn = new PlayerTurn(players, command);
        ruleProcessor.computeMovementsFor(playerTurn);
        playerTurn.present(presenter);
    }
}
