package me.mayhem.util.time;

import java.time.Duration;

public class UtilTime {

    public static String formatTime(long difference) {
        Duration duration = Duration.ofMillis(difference);

        return String.format("%d:%02d:%02d",
                duration.toHours(),
                duration.toMinutesPart(),
                duration.toSecondsPart());
    }
}
