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
    
    /**
     * idk
     */
    private HashMap<PriceAdjust, Double> TicketPriceMap = new HashMap<>();

    /**
     * Creates a new TicketPrice controller.
     */
    public TicketPrice_Controller() {
        populatePriceMap(TicketPriceMap);
    }

    /**
     * Function to set the different prices out on the ticket price map.
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
     * idk
     *
     * @return HashMap<PriceAdjust, Double>
     */
    public HashMap<PriceAdjust, Double> getTicketPriceMap() {
        return TicketPriceMap;
    }

    
    /** 
     * idk
     *
     * @param ticketPriceMap
     */
    public void setTicketPriceMap(HashMap<PriceAdjust, Double> ticketPriceMap) {
        TicketPriceMap = ticketPriceMap;
    }

    /**
     * Function to get price of the movie. 
     *
     * @param key Movie object.
     * @return Double value of the ticket price.
     */
    public double getPrice(PriceAdjust key) {
        return this.TicketPriceMap.getOrDefault(key, 0.00);
    }

    /**
     * Function to add the ticket price.
     *
     * @param newKey The new movie to be added into the hash table.
     * @param price  The price of the new movie to be added into the hash table.
     */
    public void addTicketPriceItem(PriceAdjust newKey, double price) {
        this.TicketPriceMap.put(newKey, price);
    }

    /**
     * Function to update the ticket price.
     *
     * @param key      The movie that is to be updated.
     * @param newprice The new price that is to be updated into the hash table.
     */
    public void updateTicketPriceItem(PriceAdjust key, double newprice) {
        if (this.TicketPriceMap.containsKey(key)) {
            this.TicketPriceMap.replace(key, newprice);
        }
    }
    
    /**
     * Function to remove the ticket price.
     *
     * @param key Movie object.
     * @return the string of the next input.
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
