package usecase.move_player;

public class RollAndMove implements MovePlayerUseCase {
    private DiceRoller diceRoller;
    private MovePlayer movePlayer;

    public RollAndMove(DiceRoller diceRoller, MovePlayer movePlayer) {
        this.diceRoller = diceRoller;
        this.movePlayer = movePlayer;
    }

    @Override
    public void acceptCommand(MoveCommand command) {
        if (command.dice().isPresent()) {
            movePlayer.acceptCommand(command);
            return;
        }

        MoveCommand commandWithDice = new MoveCommand(command.player(), diceRoller.roll());
        movePlayer.acceptCommand(commandWithDice);
    }
}
