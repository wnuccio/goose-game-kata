package _1_actions.player.move;

import _1_actions.player.move.rules.RuleProcessor;
import _2_domain.movement.MoveCommand;
import _2_domain.player.Players;
import _2_domain.presenter.PlayerTurnView;
import _2_domain.presenter.PresentableMovement;
import _2_domain.presenter.StringBuilderPresenter;

import java.util.List;

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
        if ( ! players.contains(command.player())) {
            return;
        }

        List<PresentableMovement> movements = ruleProcessor.fromCommand(command);

        PlayerTurnView playerTurnView = buildPlayerTurnViewFrom(command);
        presenter.init();
        for (PresentableMovement m: movements) {
            m.present(presenter, playerTurnView);
        }
        presenter.writeOutput();
    }

    private PlayerTurnView buildPlayerTurnViewFrom(MoveCommand command) {
        return new PlayerTurnView(
                command.player(),
                command.dice().first(),
                command.dice().second()
        );
    }
}
