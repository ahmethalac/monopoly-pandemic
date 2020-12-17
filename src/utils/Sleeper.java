package utils;

import java.util.concurrent.TimeUnit;

public class Sleeper {
    // sleep main thread for seconds
    public static void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
