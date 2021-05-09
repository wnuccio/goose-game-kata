package usecase.move_player;

import main.test.DiceForTest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RollAndMoveTest {
    Dice dice = mock(Dice.class);
    MovePlayerUseCase movePlayer = mock(MovePlayerUseCase.class);
    RollAndMove rollAndMove = new RollAndMove(dice, movePlayer);

    @Test
    void add_a_rolled_dice_to_move_command_when_not_specified() {
        DiceForTest rolledDice = new DiceForTest(3, 4);
        when(dice.roll()).thenReturn(rolledDice);

        rollAndMove.acceptCommand(new MoveCommand("Pippo"));

        ArgumentCaptor<MoveCommand> moveCommand = ArgumentCaptor.forClass(MoveCommand.class);
        verify(movePlayer).acceptCommand(moveCommand.capture());
        assertThat(moveCommand.getValue().player()).isEqualTo("Pippo");
        assertThat(moveCommand.getValue().dice()).isEqualTo(Optional.of(rolledDice));
    }

    @Test
    void preserve_original_dice_when_specified() {
        DiceForTest originalDice = new DiceForTest(3, 4);

        rollAndMove.acceptCommand(new MoveCommand("Pippo", originalDice));

        ArgumentCaptor<MoveCommand> moveCommand = ArgumentCaptor.forClass(MoveCommand.class);
        verify(movePlayer).acceptCommand(moveCommand.capture());
        assertThat(moveCommand.getValue().dice()).isEqualTo(Optional.of(originalDice));
    }
}