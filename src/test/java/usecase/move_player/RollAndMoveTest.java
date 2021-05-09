package usecase.move_player;

import main.test.DiceForTest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RollAndMoveTest {
    DiceRoller diceRoller = mock(DiceRoller.class);
    MovePlayer movePlayer = mock(MovePlayer.class);
    RollAndMove rollAndMove = new RollAndMove(diceRoller, movePlayer);
    private ArgumentCaptor<MoveCommand> moveCommand = ArgumentCaptor.forClass(MoveCommand.class);

    @Test
    void add_a_rolled_dice_to_move_command_when_not_specified() {
        DiceForTest rolledDice = new DiceForTest(3, 4);
        when(diceRoller.roll()).thenReturn(rolledDice);

        rollAndMove.acceptCommand(new MoveCommand("Pippo"));

        verify(movePlayer).acceptCommand(moveCommand.capture());
        assertThat(moveCommand.getValue().player()).isEqualTo("Pippo");
        assertThat(moveCommand.getValue().dice()).isEqualTo(Optional.of(rolledDice));
    }

    @Test
    void preserve_original_dice_when_specified() {
        DiceForTest originalDice = new DiceForTest(5, 6);

        rollAndMove.acceptCommand(new MoveCommand("Pippo", originalDice));

        verify(movePlayer).acceptCommand(moveCommand.capture());
        assertThat(moveCommand.getValue().dice()).isEqualTo(Optional.of(originalDice));
    }
}