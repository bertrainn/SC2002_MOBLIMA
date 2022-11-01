package MOBLIMA.Entity;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Represents an instance of a movie goer used in the system.
 */
public class MovieGoer extends User {
    /**
     * Name of movie goer.
     */
    private String Name;
    
    /**
     * Email of movie goer.
     */
    private String Email;
    
    /**
     * Phone number of movie goer.
     */
    private String Phone;
    
    /**
     * List of reviews posted.
     */
    private HashMap<Movie, Review_Ratings> PostedReviewsList = new HashMap<Movie, Review_Ratings>();
    
    /**
     * List of bookings.
     */
    private ArrayList<Booking> BookingList = new ArrayList<Booking>();

    
    /**
     * Constructor for the Table class, with additional parameters.
     * 
     * @param Username          username of movie goer
     * @param Password          password for movie goer
     * @param Name              name of movie goer
     * @param Email             email of movie goer
     * @param Phone             phone number of movie goer
     * @param PostedReviewsList list of reviews posted
     * @param BookingList       list of bookings
     */
    public MovieGoer(String Username, String Password, String Name, String Email, String Phone,
            HashMap<Movie, Review_Ratings> PostedReviewsList,
            ArrayList<Booking> BookingList) {
        super(Username, Password);
        this.Name = Name;
        this.Email = Email;
        this.Phone = Phone;
        this.BookingList = BookingList;
        this.PostedReviewsList = PostedReviewsList;
    }

    /**
     * This method returns the name of movie goer
     * 
     * @return the name of movie goer
     */
    public String getName() {
        return this.Name;
    }

    /**
     * This method sets the name of movie goer
     * 
     * @param id is the new name of movie goer
     */
    public void setName(String name) {
        this.Name = name;
    }

    /**
     * This method returns the email of movie goer
     * 
     * @return the email of movie goer
     */
    public String getEmail() {
        return this.Email;
    }

    /**
     * This method sets the email of movie goer
     * 
     * @param id is the new email of movie goer
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * This method returns the phone number of movie goer
     * 
     * @return the phone number of movie goer
     */
    public String getPhone() {
        return this.Phone;
    }

    /**
     * This method sets the phone number of movie goer
     * 
     * @param id is the new phone number of movie goer
     */
    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    /**
     * This method returns the list of bookings
     * 
     * @return the list of bookings
     */
    public ArrayList<Booking> getBookingList() {
        return this.BookingList;
    }

    /**
     * This method sets the list of bookings
     * 
     * @param id is the new list of bookings
     */
    public void setBookingList(ArrayList<Booking> BookingList) {
        this.BookingList = BookingList;
    }

    /**
     * This method adds a new booking to the BookingList
     * Firstly it does a search on the current BookingList to find if they exist in
     * the list
     * If they do, the method will terminate.
     * Else, the booking is added into BookingList.
     * 
     * @param newBooking is the new booking to be added.
     */
    public void addBooking(Booking newBooking) {
        if (this.BookingList.contains(newBooking)) {
            return;
        } else {
            this.BookingList.add(newBooking);
        }
    }

    /**
     * This method removes a booking from the BookingList
     * Firstly it does a search on the current BookingList to find if they exist in
     * the list
     * If they do, they will be removed from the BookingList.
     * Else, the method terminates.
     * 
     * @param newBooking is the booking to remove.
     */
    public void removeBooking(Booking newBooking) {
        if (this.BookingList.contains(newBooking)) {
            this.BookingList.remove(newBooking);
        }
    }

    /**
     * This method returns the list of posted reviews
     * 
     * @return the list of posted reviews
     */
    public HashMap<Movie, Review_Ratings> getPostedReviews() {
        return this.PostedReviewsList;
    }

    /**
     * This method sets the list of posted reviews
     * 
     * @param id is the new list of posted reviews
     */
    public void setPostedReviews(HashMap<Movie, Review_Ratings> PostedReviewsList) {
        this.PostedReviewsList = PostedReviewsList;
    }

    /**
     * This method adds a new review to the PostedReviewsList
     * Firstly it does a search on the current PostedReviewsList to find if they exist in
     * the list
     * If they do, the method will terminate.
     * Else, the newReview is added into PostedReviewsList.
     * 
     * @param newReview is the new review to be added.
     */
    public void addReview(Movie movie, Review_Ratings newReview) {
        if (!this.PostedReviewsList.containsKey(movie)) {
            this.PostedReviewsList.put(movie, newReview);
        }
    }
    
    /**
     * This method checks if user has already reviewed a movie
     * Firstly it does a search on the current PostedReviewsList to find if they exist in
     * the list
     * If they do, the method will return 1.
     * Else, the method returns 0.
     * 
     * @param newReview is the new review to be added.
     */
    public int checkIfReviewed(Movie movie) {
    	if(!this.PostedReviewsList.containsKey(movie)) {
    		return 0;
    	}
    	return 1;
    }

    /**
     * This method removes a review from the PostedReviewsList
     * Firstly it does a search on the current PostedReviewsList to find if they exist in
     * the list
     * If they do, they will be removed from the PostedReviewsList.
     * Else, the method terminates.
     * 
     * @param movie is the review to remove.
     */
    public void removeReview(Movie movie) {
        if (this.PostedReviewsList.containsKey(movie)) {
            this.PostedReviewsList.remove(movie);
        }
    }

    /**
     * This method edits a review from the PostedReviewsList
     * Firstly it does a search on the current PostedReviewsList to find if they exist in
     * the list
     * If they do, they will be replaced from the PostedReviewsList.
     * Else, the method terminates.
     * 
     * @param newReview is the review to replace.
     */
    public void editReview(Movie movie, Review_Ratings newReview) {
        if (this.PostedReviewsList.containsKey(movie)) {
            this.PostedReviewsList.replace(movie, newReview);
        }
    }
}
