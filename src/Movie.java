import java.util.Objects;

public class Movie extends Media {

    int duration;

    public Movie(String title, int releaseYear, String genre, float rating, int duration) {
        super(title, releaseYear, genre, rating);
        this.duration = duration;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Media media = (Media) o;
        return this.getReleaseYear() == media.getReleaseYear() && Objects.equals(this.getTitle(), media.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getTitle(), this.getReleaseYear());
    }


}

