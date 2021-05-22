package _1_actions.player.move;

import _2_domain.movement.Dice;
import _2_domain.movement.MoveCommand;
import _2_domain.movement.PlayerTurnView;
import _2_domain.player.Players;
import _2_domain.player.Position;
import _2_domain.presenter.StringBuilderPresenter;
import _2_domain.rules.RuleProcessor;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

class MovePlayerTest {
    private final Players players = new Players();
    private final StringBuilderPresenter presenter = mock(StringBuilderPresenter.class);
    RuleProcessor ruleProcessor = mock(RuleProcessor.class);
    private final MovePlayer movePlayer = new MovePlayer(players, ruleProcessor, presenter);
    private final ArgumentCaptor<PlayerTurnView> movementCaptor = ArgumentCaptor.forClass(PlayerTurnView.class);

    @Test
    void computes_a_movement_list_and_present_them_all() {
        players.setPositionOf("Pippo", Position.START);
        MoveCommand moveCommand = move("Pippo", 4, 3);

        movePlayer.acceptCommand(moveCommand);

        verify(ruleProcessor).computeMovementsFor(any());
        verify(presenter).init();
        verify(presenter).writeOutput();
    }

    private MoveCommand move(String player, int first, int second) {
        return new MoveCommand(player, Dice.dice(first, second));
    }
}