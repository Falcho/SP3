import util.TextUI;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

abstract public class Media implements IMedia{
    private String title;
    private int releaseYear;
    private String genre;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return"title='" + title + '\'';
    }

    public void display() {
        //this.mediaDialog();
    }

    public void mediaDialog(User currentUser) {
        ArrayList<String> actions = new ArrayList<>();
        actions.add("Afspil Film");
        actions.add("Tilføj til favoritter");
        actions.add("Fjern fra favoritter");
        actions.add("Tilbage");

        int choice = 0;
        while (choice < 3) {
            ui.displayMsg("Du har valgt: " + this.getTitle());
            choice = ui.promptChoice(actions, "");
            switch (choice) {
                case 1:
                    this.play();
                    currentUser.addHistory(this);
                    break;
                case 2:
                    currentUser.addFavorite(this);
                    break;
                case 3:
                    currentUser.removeFavorite(this);
                    break;

            }
        }
    }
    @Override
    public void play() {
        ui.displayMsg("--------------------");
        ui.displayMsg("Afspiller nu " + this.getTitle());
        ui.displayMsg("--------------------");
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




}
