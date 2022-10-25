package MOBLIMA.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MovieSession implements Serializable {

    private int sessionId;
    private Movie shownMovie;
    private LocalDateTime showDateTime;
    private SeatLayout seatPlan;

    public MovieSession(int sessionId, Movie shownMovie, LocalDateTime showDateTime, SeatLayout seatPlan) {
        this.sessionId = sessionId;
        this.shownMovie = shownMovie;
        this.showDateTime = showDateTime;
        this.seatPlan = seatPlan;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public Movie getShownMovie() {
        return shownMovie;
    }

    public void setShownMovie(Movie shownMovie) {
        this.shownMovie = shownMovie;
    }

    public String getShowDateTime() {
        return showDateTime.format(Constants.datetimeFormat);
    }

    public void setShowDateTime(LocalDateTime showDateTime) {
        this.showDateTime = showDateTime;
    }

    public SeatLayout getSeatPlan() {
        return seatPlan;
    }

    public void setSeatPlan(SeatLayout seatPlan) {
        this.seatPlan = seatPlan;
    }

}
