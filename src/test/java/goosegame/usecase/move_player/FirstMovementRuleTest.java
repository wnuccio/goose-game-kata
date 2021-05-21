package goosegame.usecase.move_player;

import goosegame.domain.Players;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static goosegame.domain.Dice.dice;
import static goosegame.domain.Position.START;
import static goosegame.domain.Position.position;
import static org.assertj.core.api.Assertions.assertThat;

class FirstMovementRuleTest {
    Players players = new Players();
    FirstMovementRule rule = new FirstMovementRule(players);

    @Test
    void build_a_movement_with_player_name_initial_position_and_dice_values() {
        players.setPositionOf("Pippo", position(0));

        List<Movement> movements = new ArrayList<>();
        rule.apply(move("Pippo", 4, 3), movements);

        assertThat(players.positionOf("Pippo")).isEqualTo(position(7));

        assertThat(movements.size()).isEqualTo(1);
        Movement movement = movements.get(0);
        assertThat(movement.startPosition()).isEqualTo(START);
        assertThat(movement.finalPosition()).isEqualTo(position(7));
    }

    public static MoveCommand move(String player, int first, int second) {
        return new MoveCommand(player, dice(first, second));
    }
}