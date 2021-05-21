package goosegame.config;

import goosegame.Main;

public class ApplicationRunner {

    private final GooseGameAppBuilder appBuilder;

    public ApplicationRunner(GooseGameAppBuilder appBuilder) {
        this.appBuilder = appBuilder;
    }

    public void runApplication() {
        Main.injectedBuilder = appBuilder;

        Thread thread = new Thread(this::invokeMainDetectingCrash);
        thread.setDaemon(true);
        thread.start();

        waitAbit();
    }

    private void invokeMainDetectingCrash() {
        try {
            Main.main(new String[]{});

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void waitAbit() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
