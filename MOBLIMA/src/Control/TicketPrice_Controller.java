package Control;

import java.util.HashMap;

import Entity.Constants;
import Entity.PriceAdjust;
import Entity.Constants.CINEMA_TYPE;
import Entity.Constants.MOVIE_TYPE;
import Entity.Constants.TICKET_TYPE;

import java.io.Serializable;

/**
 * Represents an instance of a ticket price used in the system.
 */

public class TicketPrice_Controller implements Serializable {
    private HashMap<PriceAdjust, Double> TicketPriceMap = new HashMap<>();

    public TicketPrice_Controller() {
        populatePriceMap(TicketPriceMap);
    }

    private void populatePriceMap(HashMap<PriceAdjust, Double> TicketPriceMap) {
        TicketPriceMap.put(Constants.TICKET_TYPE.MON_TO_THU, 9.50);
        TicketPriceMap.put(Constants.TICKET_TYPE.FRI_WEEKEND_PH, 11.00);
        TicketPriceMap.put(Constants.TICKET_TYPE.STUDENT, 7.00);
        TicketPriceMap.put(Constants.TICKET_TYPE.SENIOR, 4.00);
        TicketPriceMap.put(Constants.MOVIE_TYPE.TWO_D, 0.00);
        TicketPriceMap.put(Constants.MOVIE_TYPE.THREE_D, 4.00);
        TicketPriceMap.put(Constants.MOVIE_TYPE.BLOCKBUSTER, 1.00);
        TicketPriceMap.put(Constants.CINEMA_TYPE.STANDARD, 0.0);
        TicketPriceMap.put(Constants.CINEMA_TYPE.PLATIUM, 5.0);
    }

    public HashMap<PriceAdjust, Double> getTicketPriceMap() {
        return TicketPriceMap;
    }

    public void setTicketPriceMap(HashMap<PriceAdjust, Double> ticketPriceMap) {
        TicketPriceMap = ticketPriceMap;
    }

    public double getPrice(PriceAdjust key) {
        return this.TicketPriceMap.getOrDefault(key, 0.00);
    }

    public void addTicketPriceItem(PriceAdjust newKey, double price) {
        this.TicketPriceMap.put(newKey, price);
    }

    public void updateTicketPriceItem(PriceAdjust key, double newprice) {
        if (this.TicketPriceMap.containsKey(key)) {
            this.TicketPriceMap.replace(key, newprice);
        }
    }

    public void removeTicketPriceItem(PriceAdjust key) {
        this.TicketPriceMap.remove(key);
    }

    /**
     * This method calculates the total price of the ticket based on the TicketType
     * and
     * MovieType
     */
    public double calcPrice(Constants.MOVIE_TYPE movieType, Constants.TICKET_TYPE ticketType,
            Constants.CINEMA_TYPE cinemaType) {
        double totalPrice = 0;

        if (ticketType.equals(Constants.TICKET_TYPE.SENIOR)) {
            return getPrice(ticketType);
        }

        if (ticketType.equals(Constants.TICKET_TYPE.STUDENT)) {
            return getPrice(ticketType);
        }

        totalPrice = getPrice(ticketType) + getPrice(movieType) + getPrice(cinemaType);

        return totalPrice;
    }
}
