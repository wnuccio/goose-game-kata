package usecase.move_player;

import usecase.Presenter;
import usecase.add_player.Players;

public class MovePlayerUseCase {
    private Players players;
    private Dice dice;
    private Presenter presenter;

    public MovePlayerUseCase(Players players, Dice dice, Presenter presenter) {
        this.players = players;
        this.dice = dice;
        this.presenter = presenter;
    }

    public void acceptCommand(MoveCommand command) {
        String player = command.player();

        if ( ! players.contains(player)) {
            presenter.presentNoSuchPlayerError(player);
            return;
        }

        Movement movement = buildMovementFor(player, diceFrom(command));

        movement = evaluateJumpOnBridge(movement);

        players.setPositionOf(player, movement.toPosition());

        presenter.presentMovement(movement);
    }

    private Movement evaluateJumpOnBridge(Movement movement) {
        if (movement.toPosition() == 6) {
            return new JumpOnBridgeMovement(movement);
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

    private Dice diceFrom(MoveCommand command) {
        return command.dice().orElse(dice.roll());
    }

}
