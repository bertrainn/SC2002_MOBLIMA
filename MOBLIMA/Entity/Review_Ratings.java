package MOBLIMA.Entity;

public class Review_Ratings {
    private double Rating;
    private String Review;

    
    /**
     * Constructor for the Review Rating class
     */
    public Review_Ratings(double Rating, String Review) {
        this.Rating = Rating;
        this.Review = Review;
    }

    
    /**
     * This method returns the rating of the movie
     * 
     * @return the rating of the movie
     */
    public double getRating() {
        return this.Rating;
    }

    
    /**
     * This method returns the review of the movie
     * 
     * @return the review of the movie
     */
    public String getReview() {
        return this.Review;
    }

    
    /**
     * This method changes the rating of the movie to a new value
     * 
     * @param Rating is the new rating of the movie
     */
    public void setRating(double Rating) {
        this.Rating = Rating;
    }

    
    /**
     * This method changes the review of the movie to a new string
     * 
     * @param Review is the new review of the movie
     */
    public void setReview(String Review) {
        this.Review = Review;
    }
}
