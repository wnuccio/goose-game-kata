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

        SimpleMovement firstMovement = buildMovementFor(player, diceFrom(command));

        Movement movement = jumpIfToBridge(firstMovement);

        players.setPositionOf(player, movement.toPosition());

        presenter.presentMovement(movement);
    }

    private Movement jumpIfToBridge(SimpleMovement movement) {
        if (movement.isToBridge()) {
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
