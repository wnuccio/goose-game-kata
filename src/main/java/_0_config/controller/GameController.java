package _0_config.controller;

import _2_domain.game.Game;

public class GameController {

    private final Game game;
    private final Input input;
    private final CommandLineProcessor processor;

    public GameController(Game game, Input input, CommandLineProcessor processor) {
        this.game = game;
        this.input = input;
        this.processor = processor;
    }

    public void run() {
        while(game.isOn()) {
            String line = input.readInputLine();
            processor.acceptCommand(line);
        }
    }

}
