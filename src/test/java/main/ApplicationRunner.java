package main;

public class ApplicationRunner {
    private static ApplicationDriver driver = null;

    public static void runApplication() {
        if (driver != null) return;

        Main.injectedBuilder = new GooseGameAppBuilderForTest();

        Thread thread = new Thread(ApplicationRunner::invokeMainDetectingCrash);
        thread.setDaemon(true);
        thread.start();

    }

    private static void invokeMainDetectingCrash() {
        try {
            driver = new ApplicationDriver(); // start: the application thread is running
            Main.main(new String[]{});
            driver = null;                    // normal termination: the application thread ends

        } catch (Exception e) {
            e.printStackTrace();
            driver = null;                    // crash: the application thread ends
        }
    }

    public static boolean isApplicationRunning() {
        waitAbit();
        return driver != null;
    }

    private static void waitAbit() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }


//    no crash detection
//
//    private static void invokeMain() {
//        applicationRunning = true;
//        Main.main(Main.ARGS_FOR_TEST);
//        applicationRunning = false; // explicit stop
//    }


}
