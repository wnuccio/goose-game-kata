package app.domain.player;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class PositionBuilderTest {

    @Test
    void build_a_position_with_specified_attributes() {
        Board board = new Board();
        PositionBuilder positionBuilder = new PositionBuilder(board);

        int aValue = 7;
        String aName = "aName";
        PositionRule aRule = mock(PositionRule.class);

        Position position = positionBuilder
                .withValue(aValue)
                .withName(aName)
                .withRule(aRule)
                .build();

        assertThat(position.isEqualTo(new Position(board, aValue, aName).attachRule(aRule))).isTrue();
    }
}