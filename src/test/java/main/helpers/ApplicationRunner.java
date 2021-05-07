package main.helpers;

import main.Main;

public class ApplicationRunner {
    private Thread thread;

    public void runApplication() {
        thread = new Thread(() -> Main.main(Main.ARGS_FOR_TEST));
        thread.setDaemon(true);
        thread.start();
    }
}
