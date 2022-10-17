package MOBLIMA;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Duration;

public class Movie {
    private String Title;
    private String Description;
    private ArrayList<String> actorList;
    private ArrayList<String> directorList;
    private ArrayList<String> genreList;
    private ArrayList<Review_Ratings> review_rating_List;
    private LocalDateTime ReleaseDate;
    private String ShowingStatus;
    private Duration MovieDuration;

    public Movie(String Title, String Description, ArrayList<String> actorList, ArrayList<String> directorList,
            ArrayList<String> genreList, LocalDateTime ReleaseDate, Duration MovieDuration) {
        this.Title = Title;
        this.Description = Description;
        this.actorList = actorList;
        this.directorList = directorList;
        this.review_rating_List = new ArrayList<Review_Ratings>();
        this.ReleaseDate = ReleaseDate;
        this.MovieDuration = MovieDuration;

        LocalDateTime today = LocalDateTime.now();

        Duration releaseCheck = Duration.between(today, this.ReleaseDate);
        // if movie is older a month stop showing
        if (releaseCheck.toDays() <= -30) {
            this.ShowingStatus = "End Of Showing";
        } else if (releaseCheck.toDays() > -30) {
            this.ShowingStatus = "Now Showing";
        } else if (releaseCheck.toDays() <= 2) {
            this.ShowingStatus = "Preview";
        } else {
            this.ShowingStatus = "Coming Soon";
        }
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public void addActor(String actor) {
        for (int i = 0; i < this.actorList.size(); i++) {
            if (this.actorList.get(i).toLowerCase() == actor.toLowerCase()) {
                System.out.println("This actor already exist in the list.");
                return;
            }
        }
        this.actorList.add(actor);
    }

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

    public void addDirector(String Director) {
        for (int i = 0; i < this.directorList.size(); i++) {
            if (this.directorList.get(i).toLowerCase() == Director.toLowerCase()) {
                System.out.println("This actor already exist in the list.");
                return;
            }
        }
        this.directorList.add(Director);
    }

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

    public void addGenre(String genre) {
        for (int i = 0; i < this.genreList.size(); i++) {
            if (this.genreList.get(i).toLowerCase() == genre.toLowerCase()) {
                System.out.println("This actor already exist in the list.");
                return;
            }
        }
        this.actorList.add(genre);
    }

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

    public void addReview(Review_Ratings new_review) {
        this.review_rating_List.add(new_review);
    }

    public void removeReview(int index) {
        this.review_rating_List.remove(index);
    }

    public void setShowingStatus() {
        Duration releaseCheck = Duration.between(LocalDate.now(), this.ReleaseDate);
        // if movie is older a month stop showing
        if (releaseCheck.toDays() <= -30) {
            this.ShowingStatus = "End Of Showing";
        } else if (releaseCheck.toDays() > -30) {
            this.ShowingStatus = "Now Showing";
        } else if (releaseCheck.toDays() <= 2) {
            this.ShowingStatus = "Preview";
        } else {
            this.ShowingStatus = "Coming Soon";
        }
    }

    public void setReleaseDate(LocalDateTime newDate) {
        this.ReleaseDate = newDate;
        setShowingStatus();
    }

    public void setDuration(Duration newTime) {
        this.MovieDuration = newTime;
    }

    public String getTitle() {
        return this.Title;
    }

    public String getDescription() {
        return this.Description;
    }

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
            total += review_rating_List.get(i).getRating();
        }

        return total / review_rating_List.size();
    }

    public LocalDateTime getReleaseDate() {
        return this.ReleaseDate;
    }

    public String getShowingStatus() {
        return this.ShowingStatus;
    }

    public Duration getDuration() {
        return this.MovieDuration;
    }
}
