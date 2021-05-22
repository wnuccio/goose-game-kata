package _1_actions.controller;

import _2_domain.game.GameSwitch;

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
