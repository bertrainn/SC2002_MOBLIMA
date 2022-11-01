package MOBLIMA.Entity;

import MOBLIMA.Control.Holiday_Controller;

import java.util.HashMap;
import java.io.Serializable;

public class TicketPrice implements Serializable {

    public TicketPrice() {

    }

    public double calcPrice(Constants.MOVIE_TYPE movieType,
            Constants.TICKET_TYPE ticketType) {
        double totalvalue = getPrice_TicketType(ticketType) + getPrice_MovieType(movieType);

        return totalvalue;
    }
}
