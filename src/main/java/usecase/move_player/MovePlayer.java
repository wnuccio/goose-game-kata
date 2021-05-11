package usecase.move_player;

import domain.Dice;
import domain.Players;
import usecase.Presenter;

import static domain.Position.*;
import static usecase.move_player.MovementType.*;
import static usecase.move_player.RepeatedMovementBuilder.after;
import static usecase.move_player.SimpleMovement.movement;

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

        MovementView movementView = buildMovementViewFrom(command, movement);

        presenter.presentMovement(movementView);
    }

    private MovementView buildMovementViewFrom(MoveCommand command, Movement movement) {
        return new MovementView(
                movement,
                command.player(),
                command.dice().first(),
                command.dice().second()
        );
    }

    private Movement repeatMovementOnSpecialPositions(SimpleMovement firstMovement) {
        if (firstMovement.finalPosition() > WIN_POSITION) {
            return after(firstMovement)
                    .becauseOf(BOUNCING)
                    .goToPosition(WIN_POSITION - (firstMovement.finalPosition() - WIN_POSITION));
        }

        if (firstMovement.finalPosition() == BRIDGE) {
            return after(firstMovement)
                    .becauseOf(JUMP_ON_BRIDGE)
                    .goToPosition(BRIDGE_TARGET);
        }

        return repeatIfEndsOnGoose(firstMovement);
    }

    private Movement repeatIfEndsOnGoose(Movement previousMovement) {
        if (! previousMovement.endsOnGoose()) return previousMovement;

        Movement repeatedMovement = after(previousMovement)
                .becauseOf(REPEAT_ON_GOOSE)
                .goToPosition(previousMovement.finalPosition() + previousMovement.diceTotal());

        return repeatIfEndsOnGoose(repeatedMovement);
    }

    private SimpleMovement buildMovementFor(String player, Dice dice) {
        return movement()
                .from(players.positionOf(player))
                .givenRoll(dice)
                .end();
    }

}
