package MOBLIMA;

public class Review_Ratings {
    private double Rating;
    private String Review;

    public Review_Ratings(double Rating, String Review) {
        this.Rating = Rating;
        this.Review = Review;
    }

    public double getRating() {
        return this.Rating;
    }

    public String getReview() {
        return this.Review;
    }

    public void setRating(double Rating) {
        this.Rating = Rating;
    }

    public void setReview(String Review) {
        this.Review = Review;
    }
}
