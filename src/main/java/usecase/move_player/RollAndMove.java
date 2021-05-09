package usecase.move_player;

public class RollAndMove implements MovePlayerUseCase {
    private Dice dice;
    private MovePlayer movePlayer;

    public RollAndMove(Dice dice, MovePlayer movePlayer) {
        this.dice = dice;
        this.movePlayer = movePlayer;
    }

    @Override
    public void acceptCommand(MoveCommand command) {
        if (command.dice().isPresent()) {
            movePlayer.acceptCommand(command);
            return;
        }

        MoveCommand commandWithDice = new MoveCommand(command.player(), dice.roll());
        movePlayer.acceptCommand(commandWithDice);
    }
}
