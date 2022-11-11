package Control;

import java.util.HashMap;
import Entity.Constants;
import Entity.PriceAdjust;
import Entity.Constants.CINEMA_TYPE;
import Entity.Constants.MOVIE_TYPE;
import Entity.Constants.TICKET_TYPE;
import java.io.Serializable;

/**
 * Represents an instance of a ticket price controller used in the system. Which
 * will control the calculation of ticket pricing in the application.
 */

public class TicketPrice_Controller implements Serializable {

    /**
     * Initalising a hashmap to store the key value pairs to keep track of ticketing
     * prices.
     */
    private HashMap<PriceAdjust, Double> TicketPriceMap = new HashMap<>();

    /**
     * Constructor for the TicketPrice_Controller, where it will populate the
     * ticketpricemap with the default values.
     */
    public TicketPrice_Controller() {
        populatePriceMap(TicketPriceMap);
    }

    /**
     * This method is used to populate the ticket price map with default values.
     *
     * @param TicketPriceMap HashMap holds the ticket price.
     */
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

    /**
     * This method returns the TicketPriceMap
     *
     * @return HashMap<PriceAdjust, Double> TicketPriceMap
     */
    public HashMap<PriceAdjust, Double> getTicketPriceMap() {
        return TicketPriceMap;
    }

    /**
     * This method sets the TicketPriceMap to a new HashMap
     *
     * @param ticketPriceMap is the new HashMap
     */
    public void setTicketPriceMap(HashMap<PriceAdjust, Double> ticketPriceMap) {
        TicketPriceMap = ticketPriceMap;
    }

    /**
     * This method is used to get the price of a specific priceAdjust object in the
     * TicketPriceMap
     *
     * @param key the specific priceAdjust object.
     * @return Double value of the priceAdjust key,
     *         if the key doesn't exist return
     *         0.00
     */
    public double getPrice(PriceAdjust key) {
        return this.TicketPriceMap.getOrDefault(key, 0.00);
    }

    /**
     * This method is used to add a new key value pair to the TicketPriceMap.
     *
     * @param newKey The new priceAdjust key to be added into the hash table.
     * @param price  The value of the priceAdjust key to be added into the hash
     *               table.
     */
    public void addTicketPriceItem(PriceAdjust newKey, double price) {
        this.TicketPriceMap.put(newKey, price);
    }

    /**
     * This method is used to update the ticket price.
     *
     * @param key      The priceAdjust key that is to be updated.
     * @param newprice The value of the price that is to be updated into the hash
     *                 table.
     */
    public void updateTicketPriceItem(PriceAdjust key, double newprice) {
        if (this.TicketPriceMap.containsKey(key)) {
            this.TicketPriceMap.replace(key, newprice);
        }
    }

    /**
     * This method is used to remove a PriceAdjust key from the hashmap.
     *
     * @param key priceAdjust object.
     */
    public void removeTicketPriceItem(PriceAdjust key) {
        this.TicketPriceMap.remove(key);
    }

    /**
     * This method calculates the total price of the ticket based on the TicketType
     * and Movie Type.
     *
     * @param movieType  The Type of the movie.
     * @param ticketType The type of the ticket.
     * @param cinemaTyp  The type of the cinema.
     * @return the calculated price of the movie.
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
