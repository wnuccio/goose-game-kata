package _1_actions.player.move;

import _1_actions.player.move.rules.RuleProcessor;
import _2_domain.movement.MoveCommand;
import _2_domain.player.Players;
import _2_domain.presenter.MovementPresenter;
import _2_domain.presenter.PlayerTurnView;
import _2_domain.presenter.PresentableMovement;

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

        PlayerTurnView playerTurnView = buildPlayerTurnViewFrom(command);
        presenter.init(playerTurnView);
        for (PresentableMovement m: movements) {
            m.present(presenter);
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
