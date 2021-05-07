package main;

public class ApplicationRunner {
    private static Thread thread = null;

    public static void runApplicationOnFirstDemand() {
        if (thread != null) return;

        thread = new Thread(() -> Main.main(Main.ARGS_FOR_TEST));
        thread.setDaemon(true);
        thread.start();
    }
}
