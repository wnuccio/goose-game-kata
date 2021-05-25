package app.domain.rules.first;

import app.domain.movement.Dice;
import app.domain.movement.MoveCommand;
import app.domain.movement.Movement;
import app.domain.movement.PlayerOnTurn;
import app.domain.player.Board;
import app.domain.player.Players;
import app.domain.player.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstMovementRuleTest {
    Players players = new Players();
    Board board = new Board();
    FirstMovementRule rule = new FirstMovementRule();

    @Test
    void build_a_movement_with_start_and_final_positions() {
        players.setPositionOf("Pippo", position(10));

        PlayerOnTurn turn = playerOnTurn(players, "Pippo", 4, 3);

        rule.apply(turn);

        assertThat(players.positionOf("Pippo")).isEqualTo(position(17));

        Movement movement = turn.movements().get(0);
        assertThat(movement.startPosition()).isEqualTo(position(10));
        assertThat(movement.finalPosition()).isEqualTo(position(17));
    }

    public static MoveCommand move(String player, int first, int second) {
        return new MoveCommand(player, Dice.dice(first, second));
    }

    public static PlayerOnTurn playerOnTurn(Players players, String player, int first, int second) {
        return new PlayerOnTurn(players, new MoveCommand(player, Dice.dice(first, second)));
    }

    private Position position(int i) {
        return board.position(i);
    }
}