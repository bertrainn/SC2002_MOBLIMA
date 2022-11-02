package MOBLIMA.Entity;

import MOBLIMA.Control.Holiday_Controller;

import java.util.HashMap;
import java.io.Serializable;

/**
 * Represents an instance of a ticket price used in the system.
 */

public class TicketPrice implements Serializable {

    public TicketPrice() {

    }

    /**
     * This method calculates the total price of the ticket based on the TicketType and
     * MovieType
     */
    public double calcPrice(Constants.MOVIE_TYPE movieType,
            Constants.TICKET_TYPE ticketType) {
        double totalvalue = getPrice_TicketType(ticketType) + getPrice_MovieType(movieType);

        return totalvalue;
    }
}
