package _1_actions.player.move;

import _1_actions.player.move.presenter.MovementPresenter;
import _1_actions.player.move.presenter.MovementView;
import _1_actions.player.move.presenter.PresentableMovement;
import _1_actions.player.move.rules.RuleProcessor;
import _2_domain.MoveCommand;
import _2_domain.Players;

import java.util.List;

public class MovePlayer {
    private final Players players;
    private final RuleProcessor ruleProcessor;
    private final MovementPresenter presenter;

    public MovePlayer(Players players, RuleProcessor ruleProcessor, MovementPresenter presenter) {
        this.players = players;
        this.ruleProcessor = ruleProcessor;
        this.presenter = presenter;
    }

    public void acceptCommand(MoveCommand command) {
        if ( ! players.contains(command.player())) {
            return;
        }

        List<PresentableMovement> movements = ruleProcessor.fromCommand(command);

        MovementView movementView = buildMovementViewFrom(command, movements);
        presenter.init(movementView);
        for (PresentableMovement m: movements) {
            m.present(presenter);
        }
        presenter.writeOutput();
    }

    private MovementView buildMovementViewFrom(MoveCommand command, List<PresentableMovement> movements) {
        return new MovementView(
                movements,
                command.player(),
                command.dice().first(),
                command.dice().second()
        );
    }
}
