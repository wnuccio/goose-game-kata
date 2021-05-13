package main;

import main.test.ApplicationDriver;

public class ApplicationRunner {
    private static boolean applicationRunning  = false;

    public static void runApplication() {
        if (isApplicationRunning()) return;

        Thread thread = new Thread(ApplicationRunner::invokeMainDetectingCrash);
        thread.setDaemon(true);
        thread.start();
    }

    private static void invokeMainDetectingCrash() {
        try {
            applicationRunning = true; // start: the application thread is running
            Main.main(Main.ARGS_FOR_TEST);
            applicationRunning = false; // normal termination: the application thread ends

        } catch (Exception e) {
            e.printStackTrace();
            applicationRunning = false; // crash: the application thread ends
        }
    }

    public static boolean isApplicationRunning() {
        new ApplicationDriver().waitAbit();
        return applicationRunning;
    }

//    no crash detection
//
//    private static void invokeMain() {
//        applicationRunning = true;
//        Main.main(Main.ARGS_FOR_TEST);
//        applicationRunning = false; // explicit stop
//    }


}
