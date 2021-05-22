package _1_actions.player.rollmove;

import _1_actions.player.move.MovePlayer;
import _2_domain.movement.Dice;
import _2_domain.movement.MoveCommand;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RollAndMoveTest {
    DiceRoller diceRoller = mock(DiceRoller.class);
    MovePlayer movePlayer = mock(MovePlayer.class);
    RollAndMove rollAndMove = new RollAndMove(diceRoller, movePlayer);
    private final ArgumentCaptor<MoveCommand> moveCommand = ArgumentCaptor.forClass(MoveCommand.class);

    @Test
    void add_a_rolled_dice_to_move_command_when_not_specified() {
        Dice rolledDice = new Dice(3, 4);
        when(diceRoller.roll()).thenReturn(rolledDice);

        rollAndMove.acceptCommand("Pippo");

        verify(movePlayer).acceptCommand(moveCommand.capture());
        assertThat(moveCommand.getValue().player()).isEqualTo("Pippo");
        assertThat(moveCommand.getValue().dice()).isEqualTo(rolledDice);
    }
}