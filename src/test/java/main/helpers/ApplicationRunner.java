package main.helpers;

import main.Main;

public class ApplicationRunner {
    private static Thread thread = null;

    public void runApplication() {
        if (thread != null) return;

        thread = new Thread(() -> Main.main(Main.ARGS_FOR_TEST));
        thread.setDaemon(true);
        thread.start();
    }
}
