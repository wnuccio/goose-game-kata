package app.domain.rules.first;

import app.domain.movement.Dice;
import app.domain.movement.MoveCommand;
import app.domain.movement.Movement;
import app.domain.movement.PlayerOnTurn;
import app.domain.player.Board;
import app.domain.player.Players;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class FirstMovementRuleTest {
    Board board = new Board();
    FirstMovementRule rule = new FirstMovementRule();

    PlayerOnTurn playerOnTurn = mock(PlayerOnTurn.class);

    ArgumentCaptor<Movement> movement = ArgumentCaptor.forClass(Movement.class);


    @Test
    void build_a_movement_with_start_and_final_positions() {
        when(playerOnTurn.positionOfPlayer()).thenReturn(board.position(10));
        when(playerOnTurn.diceTotal()).thenReturn(7);

        rule.apply(playerOnTurn);

        verify(playerOnTurn).applyMovement(movement.capture());

        assertThat(movement.getValue().startPosition()).isEqualTo(board.position(10));
        assertThat(movement.getValue().finalPosition()).isEqualTo(board.position(17));
    }

    public static PlayerOnTurn playerOnTurn(Players players, String player, int first, int second) {
        return new PlayerOnTurn(players, new MoveCommand(player, Dice.dice(first, second)), new LinkedList<>());
    }

}