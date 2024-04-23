import java.util.*;

public class Serie extends Media {
    Map<String, Map<String, Media>> seasonMap;



    Serie(String title, int releaseYear, String genre, float rating) {
        super(title, releaseYear, genre, rating);
        this.seasonMap = new TreeMap<>();
    }
    public Map<String, Map<String, Media>> getSeasonMap() {
        return seasonMap;
    }

    @Override
    public void playPrevious() {
        System.out.println("play previous");
    }

    @Override
    public void playNext() {
        System.out.println("play next");
    }



}
