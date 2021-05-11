package usecase.move_player;

import domain.Dice;
import domain.Players;
import usecase.Presenter;

import static domain.Position.*;
import static usecase.move_player.MovementType.*;
import static usecase.move_player.RepeatedMovementBuilder.after;

public class MovePlayer {
    private Players players;
    private Presenter presenter;

    public MovePlayer(Players players, Presenter presenter) {
        this.players = players;
        this.presenter = presenter;
    }

    public void acceptCommand(MoveCommand command) {
        String player = command.player();

        if ( ! players.contains(player)) {
            presenter.presentNoSuchPlayerError(player);
            return;
        }

        SimpleMovement firstMovement = buildMovementFor(player, command.dice());

        Movement movement = repeatMovementOnSpecialPositions(firstMovement);

        players.setPositionOf(player, movement.finalPosition());

        presenter.presentMovement(movement);
    }

    private Movement repeatMovementOnSpecialPositions(SimpleMovement firstMovement) {
        if (firstMovement.finalPosition() > WIN_POSITION) {
            return after(firstMovement)
                    .becauseOf(BOUNCING)
                    .insteadOf(WIN_POSITION)
                    .goToPosition(WIN_POSITION - (firstMovement.finalPosition() - WIN_POSITION));
        }

        if (firstMovement.finalPosition() == BRIDGE) {
            return after(firstMovement)
                    .becauseOf(JUMP_ON_BRIDGE)
                    .insteadOf(BRIDGE)
                    .goToPosition(BRIDGE_TARGET);
        }

        return repeatIfEndsOnGoose(firstMovement);
    }

    private Movement repeatIfEndsOnGoose(Movement previousMovement) {
        if (! previousMovement.endsOnGoose()) return previousMovement;

        Movement repeatedMovement = after(previousMovement)
                .becauseOf(REPEAT_ON_GOOSE)
                .insteadOf(previousMovement.finalPosition())
                .goToPosition(previousMovement.finalPosition() + previousMovement.diceTotal());

        return repeatIfEndsOnGoose(repeatedMovement);
    }

    private SimpleMovement buildMovementFor(String player, Dice dice) {
        return SimpleMovement
                .of(player)
                .from(players.positionOf(player))
                .givenRoll(dice)
                .end();
    }

}
