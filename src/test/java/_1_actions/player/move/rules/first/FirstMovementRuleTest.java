package _1_actions.player.move.rules.first;

import _2_domain.movement.Dice;
import _2_domain.movement.MoveCommand;
import _2_domain.movement.Movement;
import _2_domain.movement.PlayerTurn;
import _2_domain.player.Players;
import org.junit.jupiter.api.Test;

import static _2_domain.player.Position.position;
import static org.assertj.core.api.Assertions.assertThat;

public class FirstMovementRuleTest {
    Players players = new Players();
    FirstMovementRule rule = new FirstMovementRule(players);

    @Test
    void build_a_movement_with_start_and_final_positions() {
        players.setPositionOf("Pippo", position(10));

        PlayerTurn turn = turn("Pippo", 4, 3);

        rule.apply(turn);

        assertThat(players.positionOf("Pippo")).isEqualTo(position(17));

        Movement movement = turn.movements().get(0);
        assertThat(movement.startPosition()).isEqualTo(position(10));
        assertThat(movement.finalPosition()).isEqualTo(position(17));
    }

    public static MoveCommand move(String player, int first, int second) {
        return new MoveCommand(player, Dice.dice(first, second));
    }

    public static PlayerTurn turn(String player, int first, int second) {
        return new PlayerTurn(new MoveCommand(player, Dice.dice(first, second)));
    }
}