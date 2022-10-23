package MOBLIMA;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * This class contains all the constant values, enum classes, date and time
 * formats used throughout the system
 */

public class Constants {

    /**
     * Date Formats
     */
    public static final String FORMAT_TIME_CLOCK = "HH:mm";
    public static final String FORMAT_TIME_HOUR = "hh:mma";
    public static final String FORMAT_DATE_SHORT = "dd/MM/yyyy";
    public static final String FORMAT_DATE_LONG = "dd MMM yyyy";
    public static final String FORMAT_BOOKING_ID = "yyyyMMddHHmm";
    public static final String FORMAT_HOLIDAY = "dd MMM";

    public static SimpleDateFormat dateFormatShort = new SimpleDateFormat(FORMAT_DATE_SHORT);
    public static SimpleDateFormat dateFormatLong = new SimpleDateFormat(FORMAT_DATE_LONG);
    public static SimpleDateFormat clockFormat = new SimpleDateFormat(FORMAT_TIME_CLOCK);
    public static SimpleDateFormat timeFormat = new SimpleDateFormat(FORMAT_TIME_HOUR);

    public static SimpleDateFormat bookingFormat = new SimpleDateFormat(FORMAT_BOOKING_ID);
    public static SimpleDateFormat holidayFormat = new SimpleDateFormat(FORMAT_HOLIDAY);
    public static SimpleDateFormat datetimeFormat = new SimpleDateFormat("dd MMM yyyy, hh:mma");

    // Date Time format : DD/MM/YY hh:mm PM
    public static DateFormat dateTimeFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

    /**
     * Enums Values
     */

    /**
     * Ticket types
     */
    public enum TicketType {
        MON_TO_THU("Monday to Thursday"),
        FRI_WEEKEND_PH("Friday, Weekends & Public Holidays"),
        MON_TO_THU_OPENING("Monday to Thursday (Sneak Preview)"),
        FRI_WEEKEND_PH_OPENING("Friday, Weekends & Public Holidays (Sneak Preview)"),
        SENIOR("Senior Citizen"),
        STUDENT("Student");

        private String type;

        TicketType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return this.type;
        }
    }

    /**
     * Movie Types
     */

    public enum MovieType {
        TWO_D("2D Movies"),
        THREE_D("3D Movies"),
        PLAT("Platium Movie Suite");

        private String type;

        MovieType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return this.type;
        }
    }

    /**
     * Age ratings for the movie.
     */
    public enum Classification {
        /**
         * General.
         */
        G("General"),

        /**
         * Parental Guidence.
         */
        PG("Parental Guidance"),

        /**
         * Parental Guidence 13.
         */
        PG13("Parental Guidance 13"),

        /**
         * No Children under 16.
         */
        NC16("No Children under 16"),

        /**
         * Mature 18.
         */
        M18("Mature 18"),

        /**
         * Restricted 21.
         */
        R21("Restricted 21");

        private String Classification;

        Classification(String data) {
            this.Classification = data;
        }

        public String toString() {
            return this.Classification;
        }
    }

    /**
     * Status for our seats
     */
    public enum Status {
        /**
         * Occupied -> Seat cannot be booked
         * Vacant -> Seat can be booked
         */
        Occupied,
        Vacant
    }
}
