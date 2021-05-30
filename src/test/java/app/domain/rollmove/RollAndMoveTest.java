package app.domain.rollmove;

import app.domain.movement.FindPlayer;
import app.domain.player.Dice;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class RollAndMoveTest {
    DiceRoller diceRoller = mock(DiceRoller.class);
    FindPlayer findPlayer = mock(FindPlayer.class);
    RollAndMove rollAndMove = new RollAndMove(diceRoller, findPlayer);

    @Test
    void add_a_rolled_dice_to_move_command_when_not_specified() {
        Dice rolledDice = new Dice(3, 4);
        when(diceRoller.roll()).thenReturn(rolledDice);

        rollAndMove.acceptCommand("Pippo");

        verify(findPlayer).acceptCommand("Pippo", rolledDice);
    }
}