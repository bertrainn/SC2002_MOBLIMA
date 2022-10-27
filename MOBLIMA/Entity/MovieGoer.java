package MOBLIMA.Entity;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieGoer extends User {
    private String Name;
    private String Email;
    private String Phone;
    private HashMap<Movie, Review_Ratings> PostedReviewsList = new HashMap<Movie, Review_Ratings>();
    private ArrayList<Booking> BookingList = new ArrayList<Booking>();

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

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhone() {
        return this.Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public ArrayList<Booking> getBookingList() {
        return this.BookingList;
    }

    public void setBookingList(ArrayList<Booking> BookingList) {
        this.BookingList = BookingList;
    }

    public void addBooking(Booking newBooking) {
        if (this.BookingList.contains(newBooking)) {
            return;
        } else {
            this.BookingList.add(newBooking);
        }
    }

    public void removeBooking(Booking newBooking) {
        if (this.BookingList.contains(newBooking)) {
            this.BookingList.remove(newBooking);
        }
    }

    public HashMap<Movie, Review_Ratings> getPostedReviews() {
        return this.PostedReviewsList;
    }

    public void setPostedReviews(HashMap<Movie, Review_Ratings> PostedReviewsList) {
        this.PostedReviewsList = PostedReviewsList;
    }

    public void addReview(Movie movie, Review_Ratings newReview) {
        if (!this.PostedReviewsList.containsKey(movie)) {
            this.PostedReviewsList.put(movie, newReview);
        }
    }

    public void removeReview(Movie movie) {
        if (this.PostedReviewsList.containsKey(movie)) {
            this.PostedReviewsList.remove(movie);
        }
    }

    public void editReview(Movie movie, Review_Ratings newReview) {
        if (this.PostedReviewsList.containsKey(movie)) {
            this.PostedReviewsList.replace(movie, newReview);
        }
    }
}
