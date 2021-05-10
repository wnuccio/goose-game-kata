package usecase.move_player;

import domain.Dice;
import domain.Players;
import usecase.Presenter;

import static domain.Position.*;
import static usecase.move_player.MovementType.*;
import static usecase.move_player.RepeatedMovementBuilder.after;

public class MovePlayer implements MovePlayerUseCase {
    private Players players;
    private Presenter presenter;

    public MovePlayer(Players players, Presenter presenter) {
        this.players = players;
        this.presenter = presenter;
    }

    @Override
    public void acceptCommand(MoveCommand command) {
        String player = command.player();

        if ( ! players.contains(player)) {
            presenter.presentNoSuchPlayerError(player);
            return;
        }

        SimpleMovement firstMovement = buildMovementFor(player, diceFrom(command));

        Movement movement = repeatMovementOnSpecialPositions(firstMovement);

        players.setPositionOf(player, movement.finalPosition());

        presenter.presentMovement(movement);
    }

    private Dice diceFrom(MoveCommand command) {
        return command.dice().orElseThrow(() -> new IllegalStateException("No dice"));
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

        if (firstMovement.endsOnGoose()) {
            return after(firstMovement)
                    .becauseOf(REPEAT_ON_GOOSE)
                    .insteadOf(firstMovement.finalPosition())
                    .goToPosition(firstMovement.finalPosition() + firstMovement.diceTotal());

        }

        return firstMovement;
    }

    private SimpleMovement buildMovementFor(String player, Dice dice) {
        return SimpleMovement
                .of(player)
                .from(players.positionOf(player))
                .givenRoll(dice)
                .end();
    }

}
