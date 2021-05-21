package app.domain.movement.rules.first;

import app.domain.movement.MoveCommand;
import app.domain.movement.Movement;
import app.domain.player.Players;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static app.domain.movement.Dice.dice;
import static app.domain.player.Position.position;
import static org.assertj.core.api.Assertions.assertThat;

public class FirstMovementRuleTest {
    Players players = new Players();
    FirstMovementRule rule = new FirstMovementRule(players);
    List<Movement> movements = new ArrayList<>();

    @Test
    void build_a_movement_with_start_and_final_positions() {
        players.setPositionOf("Pippo", position(10));

        rule.apply(move("Pippo", 4, 3), movements);

        assertThat(players.positionOf("Pippo")).isEqualTo(position(17));

        Movement movement = movements.get(0);
        assertThat(movement.startPosition()).isEqualTo(position(10));
        assertThat(movement.finalPosition()).isEqualTo(position(17));
    }

    public static MoveCommand move(String player, int first, int second) {
        return new MoveCommand(player, dice(first, second));
    }
}