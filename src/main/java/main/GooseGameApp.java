package main;

import usecase.UseCaseDispatcher;

public class GooseGameApp {

    private InputBoundary input;
    private UseCaseDispatcher useCaseDispatcher;

    public GooseGameApp(InputBoundary input, UseCaseDispatcher useCaseDispatcher) {
        this.input = input;
        this.useCaseDispatcher = useCaseDispatcher;
    }

    public void run() {
        System.out.println("Application runned");
        while(true) {
            String line = input.readInputLine();
            if (line == null) continue;
            if ("exit".equals(line)) {
                System.out.println("Application stopped");
                break;
            }
            useCaseDispatcher.acceptCommand(line);
        }
    }

}
