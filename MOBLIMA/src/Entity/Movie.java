package Entity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Duration;

/**
 * Represents an instance of a movie used in the system.
 */

public class Movie implements Serializable {

    /**
	 * The ID of the movie.
	 */
    private int id;
    
    /**
     * Title of the movie.
     */
    private String Title;

    /**
     * Description or synopsis of the movie.
     */
    private String Description;

    /**
     * Describes the age rating of the movie accroding to the classifcation above.
     */
    private Constants.AGE_CLASSIFICATION AgeRating;

    /**
     * The list of actors who are in the movie.
     */
    private ArrayList<String> actorList;

    /**
     * The list of director(s) who are in the movie.
     */
    private ArrayList<String> directorList;

    /**
     * The list of genres of this movie.
     */
    private ArrayList<String> genreList;

    /**
     * A list to store all the reviews and ratings for this movie.
     */
    private ArrayList<Review_Ratings> review_rating_List;

    /**
     * The release date of this movie
     */
    private LocalDate OpeningDate;

    /**
     * The when the movie is not showing anymore
     */
    private LocalDate ClosingDate;

    /**
     * The showing status of the movie
     */
    private Constants.SHOWING_STATUS ShowingStatus;

    /**
     * The duration of the movie
     */
    private Duration MovieDuration;

    /**
     * Constructor for the Movie class, with additional parameters.
     * 
     * @param Title         title of the movie
     * @param Description   description of the movie
     * @param actorList     list of actors in the movie
     * @param directorList  list of directors who worked on the movie
     * @param genreList     list of genres that the movie falls into
     * @param reviewList    list of reviews and ratings that the movie have
     * @param OpeningDate   Opening date of the movie
     * @param ClosingDate   closing date of the movie
     * @param MovieDuration how long the movie lasts in minutes???
     */
    public Movie(int id, String Title, String Description, Constants.AGE_CLASSIFICATION AgeRating,
            ArrayList<String> actorList,
            ArrayList<String> directorList,
            ArrayList<String> genreList, ArrayList<Review_Ratings> reviewList, LocalDate OpeningDate,
            LocalDate ClosingDate,
            Duration MovieDuration) {
        this.id = id;
        this.Title = Title;
        this.Description = Description;
        this.AgeRating = AgeRating;
        this.actorList = actorList;
        this.directorList = directorList;
        this.genreList = genreList;
        this.review_rating_List = reviewList;
        this.OpeningDate = OpeningDate;
        this.ClosingDate = ClosingDate;
        this.MovieDuration = MovieDuration;

        /**
         * This calculates the showing status of the movie when the class is created
         */
        LocalDate today = LocalDate.now();

        /**
         * If the movie is out for more than 30 days ~ a months -> End of showings
         * movie is out from between 0 and 30 days -> Now Showing
         * The movie is 2 days till release -> preview
         * Anything more than 2 days till release -> Coming soon
         */

        if (today.isAfter(this.ClosingDate)) {
            this.ShowingStatus = Constants.SHOWING_STATUS.EOS;
        } else if (today.isEqual(this.ClosingDate) || today.isEqual(this.OpeningDate)
                || (today.isBefore(this.ClosingDate) && today.isAfter(this.OpeningDate))) {
            this.ShowingStatus = Constants.SHOWING_STATUS.NS;
        } else if (Duration.between(this.OpeningDate.atStartOfDay(), today.atStartOfDay()).toDays() >= -2) {
            this.ShowingStatus = Constants.SHOWING_STATUS.P;
        } else {
            this.ShowingStatus = Constants.SHOWING_STATUS.CS;
        }
    }

    /**
     * This method returns the id
     * 
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * This method sets the id
     * 
     * @param id is the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method returns the title of the movie
     * 
     * @return the title of the movie
     */
    public String getTitle() {
        return this.Title;
    }

    /**
     * This method sets a new title for the movie
     * 
     * @param title is the new title for the movie
     */
    public void setTitle(String title) {
        this.Title = title;
    }

    /**
     * This method returns the description of the movie
     * 
     * @return the description of the movie
     */
    public String getDescription() {
        return this.Description;
    }

    /**
     * This method sets a new description for the movie
     * 
     * @param description is the new description for the movie
     */
    public void setDescription(String description) {
        this.Description = description;
    }

    /**
     * This method returns the age rating of the movie
     * 
     * @return the age rating of the movie
     */
    public Constants.AGE_CLASSIFICATION getAgeRating() {
        return this.AgeRating;
    }

    /**
     * This method sets a new age rating for the movie
     * 
     * @param classification is the new age rating for the movie
     */
    public void setAgeRating(Constants.AGE_CLASSIFICATION classification) {
        this.AgeRating = classification;
    }

    /**
     * This method returns the list of actors of the movie
     * 
     * @return the actor list of the movie
     */
    public ArrayList<String> getActors() {
        return this.actorList;
    }

    /**
     * This method sets the list of actors of the movie
     * 
     * @param actorList is the new actor list of the movie
     */
    public void setActor(ArrayList<String> actorList) {
        this.actorList = actorList;
    }

    /**
     * This method adds a new actor to the ArrayList
     * Firstly it does a search on the current actorList to find if they exist in
     * the list
     * If they do, the method will terminate.
     * Else, the actor is added into actorList.
     * 
     * @param actor is the new actor to be added.
     */
    public void addActor(String actor) {
        for (int i = 0; i < this.actorList.size(); i++) {
            if (this.actorList.get(i).toLowerCase() == actor.toLowerCase()) {
                System.out.println("This actor already exist in the list.");
                return;
            }
        }
        this.actorList.add(actor);
    }

    /**
     * This method removes an actor from the actorList
     * Firstly it does a search on the current actorList to find if they exist in
     * the list
     * If they do, they will be removed from the actorList.
     * Else, the method terminates.
     * 
     * @param actor is the actor to remove.
     */
    public void removeActor(String actor) {
        int index = 0;
        for (int i = 0; i < this.actorList.size(); i++) {
            if (this.actorList.get(i).toLowerCase() == actor.toLowerCase()) {
                this.actorList.remove(index);
                return;
            }
        }
        System.out.println("This actor does not exist in the list.");
    }

    /**
     * This method returns the list of directors of the movie
     * 
     * @return the director list of the movie
     */
    public ArrayList<String> getDirectors() {
        return this.directorList;
    }

   /**
     * This method sets a list of directors for the movie
     * 
     * @param classification is the new list of directors for the movie
     */
    public void setDirector(ArrayList<String> directorList) {
        this.directorList = directorList;
    }

    /**
     * This method adds a new director to the directorList
     * Firstly it does a search on the current directorList to find if they exist in
     * the list
     * If they do, the method will terminate.
     * Else, the actor is added into actorList.
     * 
     * @param Director is the new Director to be added.
     */
    public void addDirector(String Director) {
        for (int i = 0; i < this.directorList.size(); i++) {
            if (this.directorList.get(i).toLowerCase() == Director.toLowerCase()) {
                System.out.println("This actor already exist in the list.");
                return;
            }
        }
        this.directorList.add(Director);
    }

    /**
     * This method removes a director from the directorList
     * Firstly it does a search on the current directorList to find if they exist in
     * the list
     * If they do, the director will be removed.
     * Else, the method will terminate.
     * 
     * @param Director is the director to be removed
     */
    public void removeDirector(String Director) {
        int index = 0;
        for (int i = 0; i < this.directorList.size(); i++) {
            if (this.directorList.get(i).toLowerCase() == Director.toLowerCase()) {
                this.directorList.remove(index);
                return;
            }
        }
        System.out.println("This actor does not exist in the list.");
    }

     /**
     * This method returns the list of genres of the movie
     * 
     * @return the genre list of the movie
     */
    public ArrayList<String> getGenre() {
        return this.genreList;
    }

    /**
     * This method sets the list of genres of the movie
     * 
     * @param genreList is the new genre list of the movie
     */
    public void setGenre(ArrayList<String> genreList) {
        this.genreList = genreList;
    }

    /**
     * This method adds a new genre to the genreList
     * Firstly it does a search on the current genreList to find if they exist in
     * the list
     * If they do, the method will terminate.
     * Else, the actor is added into actorList.
     * 
     * @param genre is the new genre to be added.
     */
    public void addGenre(String genre) {
        for (int i = 0; i < this.genreList.size(); i++) {
            if (this.genreList.get(i).toLowerCase() == genre.toLowerCase()) {
                System.out.println("This actor already exist in the list.");
                return;
            }
        }
        this.actorList.add(genre);
    }

    /**
     * This method adds a new genre to the genreList
     * Firstly it does a search on the current genreList to find if they exist in
     * the list
     * If they do, the method will terminate.
     * Else, the actor is added into actorList.
     * 
     * @param genre is the new genre to be added.
     */
    public void removeGenre(String genre) {
        int index = 0;
        for (int i = 0; i < this.genreList.size(); i++) {
            if (this.genreList.get(i).toLowerCase() == genre.toLowerCase()) {
                this.genreList.remove(index);
                return;
            }
        }
        System.out.println("This actor does not exist in the list.");
    }

    /**
     * This method returns the list of review ratings of the movie
     * 
     * @return the review rating list of the movie
     */
    public ArrayList<Review_Ratings> getReviewList() {
        return this.review_rating_List;
    }

    /**
     * This method sets the list of review ratings of the movie
     * 
     * @param genreList is the new review rating list of the movie
     */
    public void setReview(ArrayList<Review_Ratings> review_rating_List) {
        this.review_rating_List = review_rating_List;
    }

    /**
     * This method returns the overall rating of the movie
     * 
     * @return the overall rating of the movie
     */
    public String getOverallRating() {
        if (this.review_rating_List.size() == 0)
            return "N/A";

        double total = 0;

        for (int i = 0; i < this.review_rating_List.size(); i++) {
            total = total + this.review_rating_List.get(i).getRating();
        }
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(total / review_rating_List.size());
    }

    /**
     * This method adds a new review to the review_rating_List
     * 
     * @param new_review is the new review to be added
     */
    public void addReview(Review_Ratings new_review) {
        this.review_rating_List.add(new_review);
    }

    /**
     * This method removes a reivew at a certain index
     * 
     * @param index is the index of the review to be removed from the
     *              review_rating_List
     */
    public void removeReview(int index) {
        this.review_rating_List.remove(index);
    }

    /**
     * This method returns the showing status of the movie
     * 
     * @return the showing status of the movie
     */
    public Constants.SHOWING_STATUS getShowingStatus() {
        return this.ShowingStatus;
    }

    /**
     * This method is called to update the ShowingStatus of the movie
     * It functions similarly to the one in the constructor method.
     */
    public void setShowingStatus() {
        LocalDate today = LocalDate.now();
        if (today.isAfter(this.ClosingDate)) {
            this.ShowingStatus = Constants.SHOWING_STATUS.EOS;
        } else if (today.isEqual(this.ClosingDate) || today.isEqual(this.OpeningDate)
                || (today.isBefore(this.ClosingDate) && today.isAfter(this.OpeningDate))) {
            this.ShowingStatus = Constants.SHOWING_STATUS.NS;
        } else if (Duration.between(this.OpeningDate.atStartOfDay(), today.atStartOfDay()).toDays() >= -2) {
            this.ShowingStatus = Constants.SHOWING_STATUS.P;
        } else {
            this.ShowingStatus = Constants.SHOWING_STATUS.CS;
        }
    }

    /**
     * This method returns the opening date of the movie
     * 
     * @return the opening date of the movie
     */
    public LocalDate getOpeningDate() {
        return this.OpeningDate;
    }

    /**
     * This method changes the release date of the movie a new value and updates the
     * showing status as well to reflect this new change
     * 
     * @param newDate is the new release date of the movie
     */
    public void setOpeningDate(LocalDate newDate) {
        this.OpeningDate = newDate;
        setShowingStatus();
    }

    /**
     * This method returns the closing date of the movie
     * 
     * @return the closing date of the movie
     */
    public LocalDate getClosingDate() {
        return ClosingDate;
    }

    /**
     * This method sets the closting date of the movie
     * 
     * @param genreList is the new closing date of the movie
     */
    public void setClosingDate(LocalDate closingDate) {
        ClosingDate = closingDate;
    }

    /**
     * This method returns the duration of the movie
     * 
     * @return the duration of the movie
     */
    public Duration getDuration() {
        return this.MovieDuration;
    }

    /**
     * This method changes the duration of the movie to a new value
     * 
     * @param newTime is the new duration of the movie
     */
    public void setDuration(Duration newTime) {
        this.MovieDuration = newTime;
    }

}
