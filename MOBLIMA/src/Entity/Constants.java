package Entity;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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

    public static DateTimeFormatter dateFormatLong = DateTimeFormatter.ofPattern(FORMAT_DATE_LONG);
    public static DateTimeFormatter dateFormatShort = DateTimeFormatter.ofPattern(FORMAT_DATE_SHORT);
    public static DateTimeFormatter clockFormat = DateTimeFormatter.ofPattern(FORMAT_TIME_CLOCK);
    public static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern(FORMAT_TIME_HOUR);

    public static DateTimeFormatter bookingFormat = DateTimeFormatter.ofPattern(FORMAT_BOOKING_ID);
    public static DateTimeFormatter holidayFormat = DateTimeFormatter.ofPattern(FORMAT_HOLIDAY);
    public static DateTimeFormatter datetimeFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma");

    // Date Time format : DD/MM/YY hh:mm PM
    public static DateFormat dateTimeFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

    /**
     * Enums Values
     */

    public enum ACCOUNT_TYPE {
        ADMIN,
        MOVIEGOER
    }

    /**
     * Ticket types
     */
    public enum TICKET_TYPE implements PriceAdjust {
        MON_TO_THU("Monday to Thursday"),
        FRI_WEEKEND_PH("Friday, Weekends & Public Holidays"),
        SENIOR("Senior Citizen"),
        STUDENT("Student");

        private String type;

        TICKET_TYPE(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return this.type;
        }
    }

    /**
     * Ticket types
     */
    public enum CINEMA_TYPE implements PriceAdjust {
        STANDARD("Standard Cinemas"),
        PLATIUM("Platium");

        private String type;

        CINEMA_TYPE(String type) {
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

    public enum MOVIE_TYPE implements PriceAdjust {
        TWO_D("2D Movies"),
        THREE_D("3D Movies"),
        BLOCKBUSTER("Blockbuster Movies");

        private String type;

        MOVIE_TYPE(String type) {
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
    public enum AGE_CLASSIFICATION {
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

        AGE_CLASSIFICATION(String data) {
            this.Classification = data;
        }

        @Override
        public String toString() {
            return this.Classification;
        }
    }

    /**
     * Showing status for the movie.
     */
    public enum SHOWING_STATUS {
        EOS("End Of Showing"),
        NS("Now Showing"),
        P("Preview"),
        CS("Coming Soon");

        private String status;

        SHOWING_STATUS(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return this.status;
        }
    }
}
