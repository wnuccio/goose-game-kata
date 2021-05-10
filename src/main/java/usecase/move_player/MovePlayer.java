package usecase.move_player;

import domain.Dice;
import domain.Players;
import domain.Position;
import usecase.Presenter;

import static domain.Position.WIN_POSITION;
import static usecase.move_player.GenericRepeatedMovement.after;

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

    private Movement repeatMovementOnSpecialPositions(SimpleMovement movement) {
        if (movement.finalPosition() > WIN_POSITION) {
            return after(movement)
                    .becauseOf(MovementType.BOUNCING)
                    .insteadOf(WIN_POSITION)
                    .goToPosition(() -> WIN_POSITION - (movement.finalPosition() - WIN_POSITION));
        }

        if (movement.finalPosition() == Position.BRIDGE) {
            return new JumpOnBridgeMovement(movement);
        }

        if (movement.endsOnGoose()) {
            return new RepeatOnGooseMovement(movement);
        }

        return movement;
    }

    private SimpleMovement buildMovementFor(String player, Dice dice) {
        return SimpleMovement
                .of(player)
                .from(players.positionOf(player))
                .givenRoll(dice)
                .end();
    }

}
