package me.mayhem.util.time;

import java.time.Duration;

/**
 *
 * A static utility class for handling converting a millis time into a formatted text
 *
 */
public class UtilTime {

    private static final String FORMAT = "%d:%02d:%02d";

    /**
     *
     * Converts the specified difference in milliseconds to hours, minutes, and seconds using the {@link UtilTime#FORMAT}
     *
     * @param difference The difference in milliseconds
     * @return The formatted time
     */
    public static String formatTime(long difference) {
        Duration duration = Duration.ofMillis(difference);

        return String.format(FORMAT,
                duration.toHours(),
                duration.toMinutesPart(),
                duration.toSecondsPart());
    }
}
