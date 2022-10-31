package MOBLIMA.Entity;

import MOBLIMA.Control.Holiday_Controller;

import java.util.HashMap;
import java.io.Serializable;

public class TicketPrice implements Serializable {
    private HashMap<Constants.MOVIE_TYPE, Double> priceMap_MovieType = new HashMap<Constants.MOVIE_TYPE, Double>();
    private HashMap<Constants.TICKET_TYPE, Double> priceMap_TicketType = new HashMap<Constants.TICKET_TYPE, Double>();

    public TicketPrice() {
        createDefaultPriceMap_MovieType(this.priceMap_MovieType);
        createDefaultPriceMap_TicketType(this.priceMap_TicketType);
    }

    private void createDefaultPriceMap_MovieType(HashMap<Constants.MOVIE_TYPE, Double> priceMap_MovieType) {
        priceMap_MovieType.put(Constants.MOVIE_TYPE.TWO_D, 0.00);
        priceMap_MovieType.put(Constants.MOVIE_TYPE.DOLBY_ATMOS_TWO_D, 1.00);
        priceMap_MovieType.put(Constants.MOVIE_TYPE.ELITE_CLUB_2D, 5.00);
        priceMap_MovieType.put(Constants.MOVIE_TYPE.ULTIMA_TWO_D, 5.00);
    }

    private void createDefaultPriceMap_TicketType(HashMap<Constants.TICKET_TYPE, Double> priceMap_TicketType) {
        priceMap_TicketType.put(Constants.TICKET_TYPE.MON_TO_THU, 10.00);
        priceMap_TicketType.put(Constants.TICKET_TYPE.MON_TO_THU_OPENING, 10.50);
        priceMap_TicketType.put(Constants.TICKET_TYPE.FRI_WEEKEND_PH, 14.50);
        priceMap_TicketType.put(Constants.TICKET_TYPE.SENIOR, 5.00);
        priceMap_TicketType.put(Constants.TICKET_TYPE.STUDENT, 7.00);
    }

    public double getPrice_TicketType(Constants.TICKET_TYPE type) {
        return priceMap_TicketType.get(type);
    }

    public double getPrice_MovieType(Constants.MOVIE_TYPE type) {
        return priceMap_MovieType.get(type);
    }

    public void changePriceMap_MovieType(Constants.MOVIE_TYPE type, double value) {
        if (this.priceMap_MovieType.containsKey(type)) {
            priceMap_MovieType.replace(type, value);
        }
    }

    public void changePriceMap_TicketType(Constants.TICKET_TYPE type, double value) {
        if (this.priceMap_TicketType.containsKey(type)) {
            priceMap_TicketType.replace(type, value);
        }
    }

    public HashMap<Constants.MOVIE_TYPE, Double> getPriceMap_MovieType() {
        return priceMap_MovieType;
    }

    public HashMap<Constants.TICKET_TYPE, Double> getPriceMap_TicketType() {
        return priceMap_TicketType;
    }

    public double calcPrice(Constants.MOVIE_TYPE movieType,
            Constants.TICKET_TYPE ticketType) {
        double totalvalue = getPrice_TicketType(ticketType) + getPrice_MovieType(movieType);

        return totalvalue;
    }
}
