package MOBLIMA;

import java.time.LocalDateTime;
import java.time.Duration;

public class MOBLIMA {
    public static void main(String[] args) {
        LocalDateTime from = LocalDateTime.of(2020, 10, 10,
                10, 20, 55);
        LocalDateTime to = LocalDateTime.of(2020, 10, 7,
                10, 21, 1);

        Duration duration = Duration.between(from, to);

        System.out.println(-10 > -9);

        // days between from and to
        System.out.println(duration.toDays() + " days");

        // hours between from and to
        System.out.println(duration.toHours() + " hours");

        // minutes between from and to
        System.out.println(duration.toMinutes() + " minutes");

        // seconds between from and to
        System.out.println(duration.toSeconds() + " seconds");
        System.out.println(duration.getSeconds() + " seconds");
    }
}
