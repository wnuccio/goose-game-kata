package main;

import boundary.UseCaseDispatcher;
import usecase.reset_game.ApplicationSwitch;

public class GooseGameApp {

    private ApplicationSwitch applicationSwitch;
    private InputBoundary input;
    private UseCaseDispatcher useCaseDispatcher;

    public GooseGameApp(ApplicationSwitch applicationSwitch, InputBoundary input, UseCaseDispatcher useCaseDispatcher) {
        this.applicationSwitch = applicationSwitch;
        this.input = input;
        this.useCaseDispatcher = useCaseDispatcher;
    }

    public void run() {
        System.out.println("Application runned");
        while(true) {
            if (applicationSwitch.isOff()) {
                System.out.println("Application stopped");
                break;
            }
            String line = input.readInputLine();
            if (line == null) continue;
            useCaseDispatcher.acceptCommand(line);
        }
    }

}
