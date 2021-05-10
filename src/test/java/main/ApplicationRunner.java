package main;

public class ApplicationRunner {
    private static Thread thread = null;

    public static void runApplicationOnFirstDemand() {
        if (isApplicationRunning()) return;

        thread = new Thread(ApplicationRunner::invokeMain);
        thread.setDaemon(true);
        thread.start();
    }

    private static void invokeMain() {
        try {
            Main.main(Main.ARGS_FOR_TEST);
            thread = null; // explicit stop

        } catch (Exception e) {
            e.printStackTrace();
            thread = null; // stop due to an error
        }
    }

    public static boolean isApplicationRunning() {
        return thread != null;
    }

//    Invokes main in a not safe manner: a crash is not detected
//      so the application cannot be restarted
//
//    private static void invokeMain_() {
//        Main.main(Main.ARGS_FOR_TEST);
//    }
}
