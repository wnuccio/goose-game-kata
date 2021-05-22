package _1_actions.game;

public class Game {

    private final GameSwitch gameSwitch;
    private final Input input;
    private final CommandLineProcessor processor;

    public Game(GameSwitch gameSwitch, Input input, CommandLineProcessor processor) {
        this.gameSwitch = gameSwitch;
        this.input = input;
        this.processor = processor;
    }

    public void run() {
        while(gameSwitch.isOn()) {
            String line = input.readInputLine();
            processor.acceptCommand(line);
        }
    }

}
