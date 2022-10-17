package MOBLIMA;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Duration;

/**
 * Represents an instance of a movie used in the system.
 */

public class Movie {

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
    private Constants.Classification AgeRating;

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
    private LocalDate ReleaseDate;

    /**
     * The showing status of the movie
     */
    private String ShowingStatus;

    /**
     * The duration of the movie
     */
    private Duration MovieDuration;

    /**
     * Constructor for the Table class, with additional parameters.
     * 
     * @param Title         title of the movie
     * @param Description   description of the movie
     * @param actorList     list of actors in the movie
     * @param directorList  list of directors who worked on the movie
     * @param genreList     list of genres that the movie falls into
     * @param reviewList    list of reviews and ratings that the movie have
     * @param ReleaseDate   release date of the movie
     * @param MovieDuration how long the movie lasts in minutes???
     */
    public Movie(String Title, String Description, ArrayList<String> actorList,
            ArrayList<String> directorList,
            ArrayList<String> genreList, ArrayList<Review_Ratings> reviewList, LocalDate ReleaseDate,
            Duration MovieDuration) {

        this.Title = Title;
        this.Description = Description;
        this.AgeRating = Constants.Classification.G;
        this.actorList = actorList;
        this.directorList = directorList;
        this.review_rating_List = reviewList;
        this.ReleaseDate = ReleaseDate;
        this.MovieDuration = MovieDuration;

        /**
         * This calculates the showing status of the movie when the class is created
         */
        LocalDate today = LocalDate.now();

        /**
         * releaseCheck is the number of dates difference between today and the movie's
         * release date
         */
        long releaseCheck = Duration.between(this.ReleaseDate.atStartOfDay(), today.atStartOfDay()).toDays();
        /**
         * If the movie is out for more than 30 days ~ a months -> End of showings
         * movie is out from between 0 and 30 days -> Now Showing
         * The movie is 2 days till release -> preview
         * Anything more than 2 days till release -> Coming soon
         */
        if (releaseCheck > 30) {
            this.ShowingStatus = "End of Showing";
        } else if (releaseCheck > 0) {
            this.ShowingStatus = "Now Showing";
        } else if (releaseCheck >= -2) {
            this.ShowingStatus = "Preview";
        } else {
            this.ShowingStatus = "Coming Soon";
        }
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
     * This method sets a new description for the movie
     * 
     * @param description is the new description for the movie
     */
    public void setDescription(String description) {
        this.Description = description;
    }

    /**
     * This method sets a new age rating for the movie
     * 
     * @param classification is the new age rating for the movie
     */
    public void setAgeRating(Constants.Classification classification) {
        this.AgeRating = classification;
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
     * This method is called to update the ShowingStatus of the movie
     * It functions similarly to the one in the constructor method.
     */
    public void setShowingStatus() {
        LocalDate today = LocalDate.now();
        long releaseCheck = Duration.between(this.ReleaseDate.atStartOfDay(), today.atStartOfDay()).toDays();
        if (releaseCheck > 30) {
            this.ShowingStatus = "End of Showing";
        } else if (releaseCheck > 0) {
            this.ShowingStatus = "Now Showing";
        } else if (releaseCheck <= -2) {
            this.ShowingStatus = "Preview";
        } else {
            this.ShowingStatus = "Coming Soon";
        }
    }

    /**
     * This method changes the release date of the movie a new value and updates the
     * showing status as well to reflect this new change
     * 
     * @param newDate is the new release date of the movie
     */
    public void setReleaseDate(LocalDate newDate) {
        this.ReleaseDate = newDate;
        setShowingStatus();
    }

    /**
     * This method changes the duration of the movie to a new value
     * 
     * @param newTime is the new duration of the movie
     */
    public void setDuration(Duration newTime) {
        this.MovieDuration = newTime;
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
     * This method returns the description of the movie
     * 
     * @return the description of the movie
     */
    public String getDescription() {
        return this.Description;
    }

    /**
     * This method returns the age rating of the movie
     * 
     * @return the age rating of the movie
     */
    public Constants.Classification getAgeRating() {
        return this.AgeRating;
    }

    /**
     * This method returns the list of actors of the movie
     * 
     * @return the actor list of the movie
     */
    public ArrayList<String> getActors() {
        return this.actorList;
    }

    public ArrayList<String> getDirectors() {
        return this.directorList;
    }

    public ArrayList<String> getGenre() {
        return this.genreList;
    }

    public ArrayList<Review_Ratings> getReviewList() {
        return this.review_rating_List;
    }

    public double getOverallRating() {
        double total = 0;
        for (int i = 0; i < this.review_rating_List.size(); i++) {
            total = total + this.review_rating_List.get(i).getRating();
        }

        return total / review_rating_List.size();
    }

    public LocalDate getReleaseDate() {
        return this.ReleaseDate;
    }

    public String getShowingStatus() {
        return this.ShowingStatus;
    }

    public Duration getDuration() {
        return this.MovieDuration;
    }
}
