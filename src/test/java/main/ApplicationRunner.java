package main;

public class ApplicationRunner {
    private boolean appRunning = false;

    private GooseGameAppBuilder appBuilder;

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
            appRunning = true;                    // normal termination: the application thread ends
            Main.main(new String[]{});
            appRunning = false;                    // normal termination: the application thread ends

        } catch (Exception e) {
            e.printStackTrace();
            appRunning = false;                    // crash: the application thread ends
        }
    }

    public boolean isApplicationRunning() {
        waitAbit();
        return appRunning;
    }

    private static void waitAbit() {
        try {
            Thread.sleep(100);
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
