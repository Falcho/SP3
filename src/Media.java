import util.TextUI;

abstract public class Media implements IMedia {
    private final String title;
    private final int releaseYear;
    private final String genre;
    private float rating;

    TextUI ui = new TextUI();



    Media(String title, int releaseYear, String genre, float rating) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.rating = rating;

    }

    public String getTitle() {
        return title;
    }
    public int getReleaseYear() {
        return releaseYear;
    }
    public String getGenre() {
        return genre;
    }
    public float getRating() {
        return rating;
    }

    @Override
    public void play() {
        ui.displayMsg("--------------------");
        ui.displayMsg("Afspiller nu " + this.getTitle());
        ui.displayMsg("--------------------");
    }
    @Override
    public void playPrevious() {
        this.play();
    }

    @Override
    public void playNext() {
        this.play();
    }

    @Override
    public String toString() {
        return "title='" + title + '\'';
    }

}
