import java.util.Objects;

public class Movie extends Media {

    int duration;

    public Movie(String title, int releaseYear, String genre, float rating, int duration) {
        super(title, releaseYear, genre, rating);
        this.duration = duration;
    }

    @Override
    public void play() {
        System.out.println("Afspil" + this.getTitle());

    }

    @Override
    public void stop() {
        System.out.println(this.getTitle() +" Has been stopped.");
    }

    @Override
    public void pause() {
        System.out.println(this.getTitle()+" has been paused.");
    }

    @Override
    public void restart() {
        System.out.println("restarting "+this.getTitle());

    }

    @Override
    public void skipIntro() {
        System.out.println("Do you wanna skip " + this.getTitle()+"?");
    }

    @Override
    public void playPrevious() {
        this.restart();
    }

    @Override
    public void playNext() {
    this.restart();
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

